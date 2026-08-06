package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class FixtureListController {

    private static final String FILE_NAME = "Fixture.bin";

    @FXML
    private DatePicker matchDatePicker;
    @FXML
    private TextField opponentField;
    @FXML
    private TextField venueField;
    @FXML
    private ComboBox<String> competitionCombo;
    @FXML
    private ComboBox<String> homeAwayCombo;
    @FXML
    private ComboBox<String> lineupStatusCombo;
    @FXML
    private Label statusLabel;

    @FXML
    private ComboBox<String> competitionFilterCombo;

    @FXML
    private TableView<FixtureRow> fixtureTable;

    @FXML
    private TableColumn<FixtureRow, String> colMatchDate;
    @FXML
    private TableColumn<FixtureRow, String> colOpponent;
    @FXML
    private TableColumn<FixtureRow, String> colVenue;
    @FXML
    private TableColumn<FixtureRow, String> colCompetition;
    @FXML
    private TableColumn<FixtureRow, String> colHomeAway;
    @FXML
    private TableColumn<FixtureRow, String> colLineupStatus;

    private ArrayList<FixtureRow> fixtureData = new ArrayList<>();

    @FXML
    private void initialize() {
        // event-5: filter by competition type (BPL/Federation Cup/Friendly)
        ArrayList<String> competitionFilters = new ArrayList<>();
        competitionFilters.add("All");
        competitionFilters.add("BPL");
        competitionFilters.add("Federation Cup");
        competitionFilters.add("Friendly");
        competitionFilterCombo.getItems().addAll(competitionFilters);
        competitionFilterCombo.getSelectionModel().selectFirst();

        ArrayList<String> competitions = new ArrayList<>();
        competitions.add("BPL");
        competitions.add("Federation Cup");
        competitions.add("Friendly");
        competitionCombo.getItems().addAll(competitions);

        ArrayList<String> homeAwayOptions = new ArrayList<>();
        homeAwayOptions.add("Home");
        homeAwayOptions.add("Away");
        homeAwayCombo.getItems().addAll(homeAwayOptions);

        ArrayList<String> lineupStatusOptions = new ArrayList<>();
        lineupStatusOptions.add("Not Set");
        lineupStatusOptions.add("Draft Saved");
        lineupStatusOptions.add("Finalized");
        lineupStatusCombo.getItems().addAll(lineupStatusOptions);

        colMatchDate.setCellValueFactory(new PropertyValueFactory<>("matchDate"));
        colOpponent.setCellValueFactory(new PropertyValueFactory<>("opponent"));
        colVenue.setCellValueFactory(new PropertyValueFactory<>("venue"));
        colCompetition.setCellValueFactory(new PropertyValueFactory<>("competitionName"));
        colHomeAway.setCellValueFactory(new PropertyValueFactory<>("homeOrAway"));
        colLineupStatus.setCellValueFactory(new PropertyValueFactory<>("lineupStatus"));

        // event-4: display fixture list in chronological order
        loadFixtures(competitionFilterCombo.getValue());
    }

    /*
     * Coach enters a new fixture and saves it.
     * The new fixture is appended to the list already stored in the bin file,
     * then the whole list is written back to the file.
     */
    @FXML
    private void addFixture() {
        LocalDate matchDate = matchDatePicker.getValue();
        String opponent = opponentField.getText();
        String venue = venueField.getText();
        String competitionName = competitionCombo.getValue();
        String homeOrAway = homeAwayCombo.getValue();
        String lineupStatus = lineupStatusCombo.getValue();

        if (matchDate == null) {
            statusLabel.setText("Please select a match date.");
            return;
        }

        if (opponent == null || opponent.trim().isEmpty()) {
            statusLabel.setText("Opponent must not be empty.");
            return;
        }

        if (venue == null || venue.trim().isEmpty()) {
            statusLabel.setText("Venue must not be empty.");
            return;
        }

        if (competitionName == null) {
            statusLabel.setText("Please select a competition.");
            return;
        }

        if (homeOrAway == null) {
            statusLabel.setText("Please select Home or Away.");
            return;
        }

        if (lineupStatus == null) {
            statusLabel.setText("Please select a lineup status.");
            return;
        }

        ArrayList<FixtureRow> currentFixtures = readFixturesFromFile();
        currentFixtures.add(new FixtureRow(matchDate.toString(), opponent, venue,
                competitionName, homeOrAway, lineupStatus));

        try (FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject(currentFixtures);

        } catch (IOException e) {
            e.printStackTrace();
        }

        matchDatePicker.setValue(null);
        opponentField.clear();
        venueField.clear();
        competitionCombo.setValue(null);
        homeAwayCombo.setValue(null);
        lineupStatusCombo.setValue(null);

        statusLabel.setText("Fixture added successfully.");

        loadFixtures(competitionFilterCombo.getValue());
    }

    @FXML
    private void onCompetitionFilterSelected() {
        loadFixtures(competitionFilterCombo.getValue());
    }

    /*
     * event-4: Display fixture list in chronological order (match date, opponent, venue,
     * competition name, home/away, current lineup status).
     * event-5: Filter by the selected competition type.
     */
    private void loadFixtures(String competitionFilter) {
        ArrayList<FixtureRow> allFixtures = readFixturesFromFile();
        ArrayList<FixtureRow> filteredFixtures = new ArrayList<>();

        for (FixtureRow fixture : allFixtures) {
            if (competitionFilter.equals("All") || fixture.getCompetitionName().equals(competitionFilter)) {
                filteredFixtures.add(fixture);
            }
        }

        fixtureData = filteredFixtures;
        fixtureTable.getItems().setAll(fixtureData);
    }

    private ArrayList<FixtureRow> readFixturesFromFile() {
        ArrayList<FixtureRow> fixtures = new ArrayList<>();

        try (FileInputStream fileIn = new FileInputStream(FILE_NAME);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            fixtures = (ArrayList<FixtureRow>) objectIn.readObject();

        } catch (EOFException e) {
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        return fixtures;
    }

    /*
     * Simple representation of one row of the fixture list table.
     */
    public static class FixtureRow implements Serializable {
        private String matchDate;
        private String opponent;
        private String venue;
        private String competitionName;
        private String homeOrAway;
        private String lineupStatus;

        public FixtureRow() {
        }

        public FixtureRow(String matchDate, String opponent, String venue,
                           String competitionName, String homeOrAway, String lineupStatus) {
            this.matchDate = matchDate;
            this.opponent = opponent;
            this.venue = venue;
            this.competitionName = competitionName;
            this.homeOrAway = homeOrAway;
            this.lineupStatus = lineupStatus;
        }

        public String getMatchDate() {
            return matchDate;
        }

        public void setMatchDate(String matchDate) {
            this.matchDate = matchDate;
        }

        public String getOpponent() {
            return opponent;
        }

        public void setOpponent(String opponent) {
            this.opponent = opponent;
        }

        public String getVenue() {
            return venue;
        }

        public void setVenue(String venue) {
            this.venue = venue;
        }

        public String getCompetitionName() {
            return competitionName;
        }

        public void setCompetitionName(String competitionName) {
            this.competitionName = competitionName;
        }

        public String getHomeOrAway() {
            return homeOrAway;
        }

        public void setHomeOrAway(String homeOrAway) {
            this.homeOrAway = homeOrAway;
        }

        public String getLineupStatus() {
            return lineupStatus;
        }

        public void setLineupStatus(String lineupStatus) {
            this.lineupStatus = lineupStatus;
        }
    }
}
