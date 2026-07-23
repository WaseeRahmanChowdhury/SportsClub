package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MyMatchScheduleController {

    @FXML
    private TableView<MatchRow> matchTable;

    @FXML
    private TableColumn<MatchRow, String> colDate;
    @FXML
    private TableColumn<MatchRow, String> colVenue;

    private final ObservableList<MatchRow> matchData = FXCollections.observableArrayList();
    @FXML
    private TableColumn colOpponent;

    @FXML
    private void initialize() {
        colDate.setCellValueFactory(new PropertyValueFactory<>("matchDate"));
        colOpponent.setCellValueFactory(new PropertyValueFactory<>("opponentClub"));
        colVenue.setCellValueFactory(new PropertyValueFactory<>("venue"));

        matchTable.setItems(matchData);

    }

    /*
     * event-4: Load all scheduled matches assigned to the player.
     *  Replace with real data source (file lookup
     * filtered by the logged-in player's ID).
     */
//    private void loadScheduledMatches() {
//        matchData.clear();
//        // fill matchData with the player's scheduled matches
//    }

//    @FXML
//    private void refreshSchedule() {
//        loadScheduledMatches();
//    }

    @FXML
    public void loadMatchSchedule(ActionEvent actionEvent) {
    }

    /*
     * Simple reprresentation of one row of the match schedule table
     * (event-5: match date, day, kickoff time, opponent club, venue, competition name).
     */
    public static class MatchRow {
        private String matchDate;
        private String day;
        private String kickoffTime;
        private String opponentClub;
        private String venue;
        private String competitionName;

        public MatchRow() {
        }

        public MatchRow(String matchDate, String day, String kickoffTime,
                         String opponentClub, String venue, String competitionName) {
            this.matchDate = matchDate;
            this.day = day;
            this.kickoffTime = kickoffTime;
            this.opponentClub = opponentClub;
            this.venue = venue;
            this.competitionName = competitionName;
        }

        public String getMatchDate() {
            return matchDate;
        }

        public void setMatchDate(String matchDate) {
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
