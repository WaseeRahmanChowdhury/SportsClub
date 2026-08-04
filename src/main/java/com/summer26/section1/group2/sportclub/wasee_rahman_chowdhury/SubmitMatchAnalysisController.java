package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;

public class SubmitMatchAnalysisController {

    @FXML
    private ComboBox<String> completedMatchCombo;
    @FXML
    private TextField overallRatingField;
    @FXML
    private Label statusLabel;

    private final ArrayList<MatchAnalysis> matchAnalysisList = new ArrayList<>();

    private static final String FILE_NAME = "MatchAnalysis.bin";

    @FXML
    private void initialize() {
        loadMatchAnalysisListFromFile();

        // event-4: display list of recently completed matches
        loadCompletedMatches();
    }

    /*
     * event-4: Load list of recently completed matches.
     */
    private void loadCompletedMatches() {
        ArrayList<String> completedMatches = new ArrayList<>();
        completedMatches.add("Home vs Dhaka FC");
        completedMatches.add("Away vs Union City");
        completedMatches.add("Home vs Brothers United");

        completedMatchCombo.getItems().addAll(completedMatches);
    }

    @FXML
    private void submitMatchAnalysis() {
        statusLabel.setText("");

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
        statusLabel.setText("Post-match analysis submitted successfully");

        overallRatingField.clear();
        completedMatchCombo.setValue(null);
    }

    /*
     * event-5: Verify the selected match status is 'Completed'.
     */
    private boolean isMatchCompleted(String selectedMatch) {
        return completedMatchCombo.getItems().contains(selectedMatch);
    }

    /*
     * event-8: Save the analysis report to the match record file.
     */
    private void saveMatchAnalysis(String selectedMatch, int overallRating) {
        MatchAnalysis analysis = new MatchAnalysis(selectedMatch, overallRating);
        matchAnalysisList.add(analysis);
        saveMatchAnalysisListToFile();
    }

    private void saveMatchAnalysisListToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(matchAnalysisList);
        } catch (IOException e) {
            statusLabel.setText("ERROR: Could not save the match analysis to file.");
        }
    }

    @SuppressWarnings("unchecked")
    private void loadMatchAnalysisListFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            ArrayList<MatchAnalysis> loadedList = (ArrayList<MatchAnalysis>) in.readObject();
            matchAnalysisList.addAll(loadedList);
        } catch (IOException | ClassNotFoundException e) {
            statusLabel.setText("ERROR: Could not load match analysis records from file.");
        }
    }

    /*
     * Simple representation of one submitted post-match analysis report.
     */
    public static class MatchAnalysis implements Serializable {
        private String matchName;
        private int overallRating;

        public MatchAnalysis() {
        }

        public MatchAnalysis(String matchName, int overallRating) {
            this.matchName = matchName;
            this.overallRating = overallRating;
        }

        public String getMatchName() {
            return matchName;
        }

        public void setMatchName(String matchName) {
            this.matchName = matchName;
        }

        public int getOverallRating() {
            return overallRating;
        }

        public void setOverallRating(int overallRating) {
            this.overallRating = overallRating;
        }
    }
}
