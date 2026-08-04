package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;

public class PlayerReportsController {

    @FXML
    private TextField playerNameTF;
    @FXML
    private ComboBox<String> recordPeriodCombo;
    @FXML
    private TextField appearancesTF;
    @FXML
    private TextField minutesPlayedTF;
    @FXML
    private TextField goalsTF;
    @FXML
    private Label saveStatusLabel;

    @FXML
    private ComboBox<String> playerCombo;
    @FXML
    private ComboBox<String> reportPeriodCombo;

    @FXML
    private Label appearancesLabel;
    @FXML
    private Label minutesPlayedLabel;
    @FXML
    private Label goalsLabel;

    private final ArrayList<PerformanceRecord> performanceRecords = new ArrayList<>();

    private static final String FILE_NAME = "CoachPlayerStat.bin";

    @FXML
    private void initialize() {
        // event-5: report period dropdown (Last Match/Current Season/Previous Season)
        ArrayList<String> periods = new ArrayList<>();
        periods.add("Last Match");
        periods.add("Current Season");
        periods.add("Previous Season");

        recordPeriodCombo.getItems().addAll(periods);
        reportPeriodCombo.getItems().addAll(periods);

        loadPerformanceRecordsFromFile();

        // event-4: display player selection dropdown listing all squad members
        loadSquadMembers();

        clearReport();
    }

    /*
     * event-4: Load all squad members into the player selection dropdown,
     * based on the players already saved in the performance records.
     */
    private void loadSquadMembers() {
        ArrayList<String> playerNames = new ArrayList<>();
        for (PerformanceRecord record : performanceRecords) {
            if (!playerNames.contains(record.getPlayerName())) {
                playerNames.add(record.getPlayerName());
            }
        }
        playerCombo.getItems().clear();
        playerCombo.getItems().addAll(playerNames);
    }

    @FXML
    private void onSavePerformanceRecord() {
        saveStatusLabel.setText("");

        String playerName = playerNameTF.getText();
        if (playerName == null || playerName.trim().isEmpty()) {
            saveStatusLabel.setText("Player Name must not be empty.");
            return;
        }

        String period = recordPeriodCombo.getValue();
        if (period == null) {
            saveStatusLabel.setText("Please select a Report Period.");
            return;
        }

        int appearances;
        int minutesPlayed;
        int goals;
        try {
            appearances = Integer.parseInt(appearancesTF.getText());
            minutesPlayed = Integer.parseInt(minutesPlayedTF.getText());
            goals = Integer.parseInt(goalsTF.getText());
        } catch (NumberFormatException e) {
            saveStatusLabel.setText("Appearances, Minutes Played and Goals must be valid numbers.");
            return;
        }

        if (appearances < 0 || minutesPlayed < 0 || goals < 0) {
            saveStatusLabel.setText("Appearances, Minutes Played and Goals cannot be negative.");
            return;
        }

        PerformanceRecord record = new PerformanceRecord(playerName.trim(), period, appearances, minutesPlayed, goals);
        performanceRecords.add(record);
        savePerformanceRecordsToFile();

        loadSquadMembers();

        saveStatusLabel.setText("Performance record saved.");

        playerNameTF.clear();
        recordPeriodCombo.setValue(null);
        appearancesTF.clear();
        minutesPlayedTF.clear();
        goalsTF.clear();
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
     * event-6: Fetch the performance record for the selected player and period.
     * event-7: Display the performance report in a formatted table.
     */
    private void loadPerformanceReport(String player, String period) {
        for (PerformanceRecord record : performanceRecords) {
            if (record.getPlayerName().equals(player) && record.getPeriod().equals(period)) {
                appearancesLabel.setText(String.valueOf(record.getAppearances()));
                minutesPlayedLabel.setText(String.valueOf(record.getMinutesPlayed()));
                goalsLabel.setText(String.valueOf(record.getGoals()));
                return;
            }
        }

        clearReport();
    }

    private void clearReport() {
        appearancesLabel.setText("-");
        minutesPlayedLabel.setText("-");
        goalsLabel.setText("-");
    }

    private void savePerformanceRecordsToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(performanceRecords);
        } catch (IOException e) {
            saveStatusLabel.setText("ERROR: Could not save the performance record to file.");
        }
    }

    @SuppressWarnings("unchecked")
    private void loadPerformanceRecordsFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            ArrayList<PerformanceRecord> loadedRecords = (ArrayList<PerformanceRecord>) in.readObject();
            performanceRecords.addAll(loadedRecords);
        } catch (IOException | ClassNotFoundException e) {
            saveStatusLabel.setText("ERROR: Could not load performance records from file.");
        }
    }

    /*
     * Simple representation of one player's performance record for a report period.
     */
    public static class PerformanceRecord implements Serializable {
        private String playerName;
        private String period;
        private int appearances;
        private int minutesPlayed;
        private int goals;

        public PerformanceRecord() {
        }

        public PerformanceRecord(String playerName, String period, int appearances, int minutesPlayed, int goals) {
            this.playerName = playerName;
            this.period = period;
            this.appearances = appearances;
            this.minutesPlayed = minutesPlayed;
            this.goals = goals;
        }

        public String getPlayerName() {
            return playerName;
        }

        public void setPlayerName(String playerName) {
            this.playerName = playerName;
        }

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public int getAppearances() {
            return appearances;
        }

        public void setAppearances(int appearances) {
            this.appearances = appearances;
        }

        public int getMinutesPlayed() {
            return minutesPlayed;
        }

        public void setMinutesPlayed(int minutesPlayed) {
            this.minutesPlayed = minutesPlayed;
        }

        public int getGoals() {
            return goals;
        }

        public void setGoals(int goals) {
            this.goals = goals;
        }
    }
}
