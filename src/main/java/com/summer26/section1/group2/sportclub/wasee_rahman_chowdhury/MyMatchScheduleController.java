package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class MyMatchScheduleController {

    private static final String FILE_NAME = "MatchSchedule.bin";

    @FXML
    private TableView<MatchRow> matchTable;

    @FXML
    private TableColumn<MatchRow, LocalDate> colDate;
    @FXML
    private TableColumn<MatchRow, String> colVenue;

    @FXML
    private TableColumn<MatchRow, String> colOpponent;

    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField opponentField;
    @FXML
    private TextField venueField;

    private ArrayList<MatchRow> matchData = new ArrayList<>();

    @FXML
    private void initialize() {

        colDate.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getMatchDate()));

        colOpponent.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getOpponentClub()));

        colVenue.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getVenue()));

        loadMatchSchedule(null);
    }

    /*
     * event-3: Player enters match details and saves it.
     * The match is appended to the list already stored in the bin file,
     * then the whole list is written back to the file.
     */
    @FXML
    public void saveMatch(ActionEvent actionEvent) {
        LocalDate date = datePicker.getValue();
        String opponent = opponentField.getText();
        String venue = venueField.getText();

        if (date == null || opponent.isEmpty() || venue.isEmpty()) {
            return;
        }

        ArrayList<MatchRow> currentMatches = readMatchesFromFile();
        currentMatches.add(new MatchRow(date, opponent, venue));

        try (FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject(currentMatches);

        } catch (IOException e) {
            e.printStackTrace();
            // displays a user-facing error message (e.g. via an alert or status label)
        }

        datePicker.setValue(null);
        opponentField.clear();
        venueField.clear();

        loadMatchSchedule(null);
    }

    /*
     * event-4: Load all scheduled matches assigned to the player
     * by reading them back from the bin file and showing them in the table.
     */
    @FXML
    public void loadMatchSchedule(ActionEvent actionEvent) {
        matchData = readMatchesFromFile();
        matchTable.getItems().setAll(matchData);
    }

    private ArrayList<MatchRow> readMatchesFromFile() {
        ArrayList<MatchRow> matches = new ArrayList<>();

        try (FileInputStream fileIn = new FileInputStream(FILE_NAME);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            matches = (ArrayList<MatchRow>) objectIn.readObject();

        } catch (EOFException e) {
            // file exists but is empty, so there is nothing to read yet
        } catch (IOException e) {
            // file does not exist yet, so there is nothing to read yet
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        return matches;
    }

    /*
     * Simple representation of one row of the match schedule table
     * (event-5: match date, opponent club, venue).
     */
    public static class MatchRow implements Serializable {
        private LocalDate matchDate;
        private String opponentClub;
        private String venue;

        public MatchRow() {
        }

        public MatchRow(LocalDate matchDate, String opponentClub, String venue) {
            this.matchDate = matchDate;
            this.opponentClub = opponentClub;
            this.venue = venue;
        }

        public LocalDate getMatchDate() {
            return matchDate;
        }

        public void setMatchDate(LocalDate matchDate) {
            this.matchDate = matchDate;
        }

        public String getOpponentClub() {
            return opponentClub;
        }

        public void setOpponentClub(String opponentClub) {
            this.opponentClub = opponentClub;
        }

        public String getVenue() {
            return venue;
        }

        public void setVenue(String venue) {
            this.venue = venue;
        }
    }
}
