package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MyPerformanceStatsController {

    @FXML
    private ComboBox<String> timeRangeCombo;

    @FXML
    private TableView<PerformanceRow> statsTable;

    @FXML
    private TableColumn<PerformanceRow, Number> colAppearances;
    @FXML
    private TableColumn<PerformanceRow, Number> colGoals;
    @FXML
    private TableColumn<PerformanceRow, Number> colAssists;
    @FXML
    private TableColumn<PerformanceRow, Number> colYellowCards;
    @FXML
    private TableColumn<PerformanceRow, Number> colRedCards;
    @FXML
    private TableColumn<PerformanceRow, Number> colMinutes;

    private final ObservableList<PerformanceRow> statsData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // event-4: time range filter options
        timeRangeCombo.setItems(FXCollections.observableArrayList("Last Match", "This Season", "All Time"));
        timeRangeCombo.getSelectionModel().selectFirst();

        colAppearances.setCellValueFactory(new PropertyValueFactory<>("appearances"));
        colGoals.setCellValueFactory(new PropertyValueFactory<>("goals"));
        colAssists.setCellValueFactory(new PropertyValueFactory<>("assists"));
        colYellowCards.setCellValueFactory(new PropertyValueFactory<>("yellowCards"));
        colRedCards.setCellValueFactory(new PropertyValueFactory<>("redCards"));
        colMinutes.setCellValueFactory(new PropertyValueFactory<>("minutesPlayed"));

        statsTable.setItems(statsData);

        loadPerformanceStats(timeRangeCombo.getValue());
    }

    @FXML
    private void onTimeRangeSelected() {
        loadPerformanceStats(timeRangeCombo.getValue());
    }

    /*
     * event-5: Display performance statistics in a table based on the selected time range filter.
     * Replace with a real data source filtered by the logged-in player and selected range.
     */
    private void loadPerformanceStats(String timeRange) {
        statsData.clear();
        // populate statsData based on timeRange ("Last Match", "This Season", "All Time")
    }

    /*
     * Simplerepresentation of one row of performance statistics.
     */
    public static class PerformanceRow {
        private int appearances;
        private int goals;
        private int assists;
        private int yellowCards;
        private int redCards;
        private int minutesPlayed;

        public PerformanceRow() {
        }

        public PerformanceRow(int appearances, int goals, int assists,
                               int yellowCards, int redCards, int minutesPlayed) {
            this.appearances = appearances;
            this.goals = goals;
            this.assists = assists;
            this.yellowCards = yellowCards;
            this.redCards = redCards;
            this.minutesPlayed = minutesPlayed;
        }

        public int getAppearances() {
            return appearances;
        }

        public void setAppearances(int appearances) {
            this.appearances = appearances;
        }

        public int getGoals() {
            return goals;
        }

        public void setGoals(int goals) {
            this.goals = goals;
        }

        public int getAssists() {
            return assists;
        }

        public void setAssists(int assists) {
            this.assists = assists;
        }

        public int getYellowCards() {
            return yellowCards;
        }

        public void setYellowCards(int yellowCards) {
            this.yellowCards = yellowCards;
        }

        public int getRedCards() {
            return redCards;
        }

        public void setRedCards(int redCards) {
            this.redCards = redCards;
        }

        public int getMinutesPlayed() {
            return minutesPlayed;
        }

        public void setMinutesPlayed(int minutesPlayed) {
            this.minutesPlayed = minutesPlayed;
        }
    }
}
