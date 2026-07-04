package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class PlayerReportsController {

    @FXML
    private ComboBox<String> playerCombo;
    @FXML
    private ComboBox<String> reportPeriodCombo;

    @FXML
    private Label matchesPlayedLabel;
    @FXML
    private Label goalsLabel;
    @FXML
    private Label assistsLabel;
    @FXML
    private Label passAccuracyLabel;
    @FXML
    private Label minutesPlayedLabel;
    @FXML
    private Label shotsOnTargetLabel;
    @FXML
    private Label yellowCardsLabel;
    @FXML
    private Label redCardsLabel;

    @FXML
    private void initialize() {
        // event-4: display player selection dropdown listing all squad members
        loadSquadMembers();

        // event-5: report period dropdown (Last Match/Current Season/Previous Season)
        reportPeriodCombo.setItems(FXCollections.observableArrayList(
                "Last Match", "Current Season", "Previous Season"));

        clearReport();
    }

    /*
     * event-4: Load all squad members into the player selection dropdown.
     * Replace with real data source lookup.
     */
    private void loadSquadMembers() {
        playerCombo.setItems(FXCollections.observableArrayList());
        // populate playerCombo with all squad members
    }

    @FXML
    private void onGenerateReport() {
        String player = playerCombo.getValue();
        String period = reportPeriodCombo.getValue();

        if (player == null || period == null) {
            clearReport();
            return;
        }

        // event-6: fetch all match participation records for the selected player and period
        // from the match data file; calculate the performance metrics
        loadPerformanceReport(player, period);
    }

    /*
     * event-6: Fetch all match participation records for the selected player and period
     * from the match data file; calculate: matches played, goals, assists, pass accuracy %,
     * minutes played, shots on target, yellow cards, red cards.
     * event-7: Display the performance report in a formatted table.
     * Replace with real data source lookup.
     */
    private void loadPerformanceReport(String player, String period) {
        clearReport();
        // populate the report labels from the calculated performance data
    }

    private void clearReport() {
        matchesPlayedLabel.setText("-");
        goalsLabel.setText("-");
        assistsLabel.setText("-");
        passAccuracyLabel.setText("-");
        minutesPlayedLabel.setText("-");
        shotsOnTargetLabel.setText("-");
        yellowCardsLabel.setText("-");
        redCardsLabel.setText("-");
    }
}
