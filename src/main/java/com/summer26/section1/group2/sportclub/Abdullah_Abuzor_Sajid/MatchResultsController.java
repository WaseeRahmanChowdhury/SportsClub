package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MatchResultsController {

    private static final String ALL_COMPETITIONS = "All Competitions";

    @FXML
    private ComboBox<String> competitionCombo;
    @FXML
    private ListView<MatchResult> resultsListView;

    @FXML
    private Label detailHeaderLabel;
    @FXML
    private Label venueLabel;
    @FXML
    private Label refereeLabel;
    @FXML
    private Label attendanceLabel;
    @FXML
    private ListView<String> highlightsListView;

    private final ObservableList<MatchResult> resultRows = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        competitionCombo.getItems().addAll(
                ALL_COMPETITIONS,
                MatchResult.COMPETITION_BPL,
                MatchResult.COMPETITION_FEDERATION_CUP,
                MatchResult.COMPETITION_SUPER_CUP
        );
        competitionCombo.setValue(ALL_COMPETITIONS);

        resultsListView.setItems(resultRows);
        resultsListView.setCellFactory(list -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(MatchResult result, boolean empty) {
                super.updateItem(result, empty);
                if (empty || result == null) {
                    setText(null);
                } else {
                    setText(result.getMatchDate() + "  " + result.getHomeTeam() + " " + result.getScoreLine()
                            + " " + result.getAwayTeam() + "  [" + result.getCompetition() + "]");
                }
            }
        });

        resultsListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDetails(newValue)
        );

        loadResults();
    }

    // event-4: fan optionally selects a competition filter
    @FXML
    private void onCompetitionChanged() {
        loadResults();
    }

    // event-5/6: fetch and display completed match results in reverse chronological order
    private void loadResults() {
        String selected = competitionCombo.getValue();
        String competitionFilter = ALL_COMPETITIONS.equals(selected) ? null : selected;

        List<MatchResult> results = new ArrayList<>(MatchResult.getResults(competitionFilter));
        results.sort(Comparator.comparing(MatchResult::getMatchDate).reversed());
        resultRows.setAll(results);

        detailHeaderLabel.setText("");
        venueLabel.setText("");
        refereeLabel.setText("");
        attendanceLabel.setText("");
        highlightsListView.getItems().clear();
    }

    // event-7/8/9: fan clicks a match result, display full match detail with highlights by minute
    private void showDetails(MatchResult result) {
        if (result == null) {
            detailHeaderLabel.setText("");
            venueLabel.setText("");
            refereeLabel.setText("");
            attendanceLabel.setText("");
            highlightsListView.getItems().clear();
            return;
        }

        detailHeaderLabel.setText(result.getHomeTeam() + " " + result.getScoreLine() + " " + result.getAwayTeam()
                + "  (" + result.getMatchDate() + ")");
        venueLabel.setText("Venue: " + result.getVenue());
        refereeLabel.setText("Referee: " + result.getReferee());
        attendanceLabel.setText("Attendance: " + result.getAttendance());

        List<MatchEvent> events = new ArrayList<>(result.getEvents());
        events.sort(Comparator.comparingInt(MatchEvent::getMinute));

        highlightsListView.getItems().clear();
        for (MatchEvent event : events) {
            highlightsListView.getItems().add(event.toString());
        }
    }
}
