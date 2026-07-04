package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FixtureListController {

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

    private final ObservableList<FixtureRow> fixtureData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // event-5: filter by competition type (BPL/Federation Cup/Friendly)
        competitionFilterCombo.setItems(FXCollections.observableArrayList(
                "All", "BPL", "Federation Cup", "Friendly"));
        competitionFilterCombo.getSelectionModel().selectFirst();

        colMatchDate.setCellValueFactory(new PropertyValueFactory<>("matchDate"));
        colOpponent.setCellValueFactory(new PropertyValueFactory<>("opponent"));
        colVenue.setCellValueFactory(new PropertyValueFactory<>("venue"));
        colCompetition.setCellValueFactory(new PropertyValueFactory<>("competitionName"));
        colHomeAway.setCellValueFactory(new PropertyValueFactory<>("homeOrAway"));
        colLineupStatus.setCellValueFactory(new PropertyValueFactory<>("lineupStatus"));

        fixtureTable.setItems(fixtureData);

        // event-4: display fixture list in chronological order
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
     * Replace with real data source lookup.
     */
    private void loadFixtures(String competitionFilter) {
        fixtureData.clear();
        // populate fixtureData with upcoming fixtures filtered by competitionFilter
    }

    /*
     * Simple representation of one row of the fixture list table.
     */
    public static class FixtureRow {
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
