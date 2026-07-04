package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class SubmitMatchAnalysisController {

    @FXML
    private ComboBox<String> completedMatchCombo;
    @FXML
    private TextField overallRatingField;
    @FXML
    private Label statusLabel;

    @FXML
    private void initialize() {
        // event-4: display list of recently completed matches
        loadCompletedMatches();
    }

    /*
     * event-4: Load list of recently completed matches.
     * Replace with real data source lookup.
     */
    private void loadCompletedMatches() {
        completedMatchCombo.setItems(FXCollections.observableArrayList());
        // populate completedMatchCombo with recently completed matches
    }

    @FXML
    private void submitMatchAnalysis() {
        statusLabel.setTextFill(Color.RED);

        String selectedMatch = completedMatchCombo.getValue();
        if (selectedMatch == null) {
            statusLabel.setText("Please select a match.");
            return;
        }

        // event-5: verify the selected match status is 'Completed'
        if (!isMatchCompleted(selectedMatch)) {
            statusLabel.setText("Match analysis can only be submitted for a completed match.");
            return;
        }

        // event-7: validate all numeric ratings are integers between 1 and 10
        int overallRating;
        try {
            overallRating = Integer.parseInt(overallRatingField.getText());
        } catch (NumberFormatException e) {
            statusLabel.setText("Overall team performance rating must be an integer between 1 and 10.");
            return;
        }

        if (overallRating < 1 || overallRating > 10) {
            statusLabel.setText("Overall team performance rating must be an integer between 1 and 10.");
            return;
        }

        // event-8: save the analysis report to the match record file
        saveMatchAnalysis(selectedMatch, overallRating);

        // event-9: display submission confirmation
        statusLabel.setTextFill(Color.GREEN);
        statusLabel.setText("Post-match analysis submitted successfully");
    }

    /*
     * event-5: Verify the selected match status is 'Completed'.
     * Replace with a real check against the fixture data file.
     */
    private boolean isMatchCompleted(String selectedMatch) {
        return true;
    }

    /*
     * event-8: Save the analysis report to the match record file.
     * Replace with real persistence logic.
     */
    private void saveMatchAnalysis(String selectedMatch, int overallRating) {
        // persist the post-match analysis report
    }
}
