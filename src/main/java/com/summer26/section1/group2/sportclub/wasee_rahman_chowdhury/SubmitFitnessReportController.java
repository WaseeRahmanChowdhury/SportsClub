package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.time.LocalDate;

public class SubmitFitnessReportController {

    @FXML
    private DatePicker dateField;
    @FXML
    private TextField bodyWeightField;
    @FXML
    private ComboBox<Integer> fatigueLevelCombo;
    @FXML
    private ComboBox<String> painAreaCombo;
    @FXML
    private Label statusLabel;

    @FXML
    private void initialize() {
        // event-4: date (auto-filled)
        dateField.setValue(LocalDate.now());

        // event-6: fatigue level 1-10 dropdown
        fatigueLevelCombo.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        // event-4: pain area dropdown (none/leg/back/shoulder/other)
        painAreaCombo.setItems(FXCollections.observableArrayList("None", "Leg", "Back", "Shoulder", "Other"));
        painAreaCombo.getSelectionModel().selectFirst();
    }

    @FXML
    private void submitFitnessReport() {
        statusLabel.setTextFill(Color.RED);

        // event-5: validate body weight - must be numeric and between 40-150 kg
        String weightText = bodyWeightField.getText();
        double bodyWeight;
        try {
            bodyWeight = Double.parseDouble(weightText);
        } catch (NumberFormatException e) {
            statusLabel.setText("Body weight must be a number.");
            return;
        }

        if (bodyWeight < 40 || bodyWeight > 150) {
            statusLabel.setText("Body weight must be between 40 and 150 kg.");
            return;
        }

        // event-6: fatigue level selected
        Integer fatigueLevel = fatigueLevelCombo.getValue();
        if (fatigueLevel == null) {
            statusLabel.setText("Please select a fatigue level.");
            return;
        }

        // event-7: pain area described
        String painArea = painAreaCombo.getValue();

        // event-8: verify that the player has not already submitted a fitness report for today
        if (!isFitnessReportAlreadySubmittedToday()) {
            // event-9: save the fitness report to the player's medical record file with a timestamp
            saveFitnessReport(dateField.getValue(), bodyWeight, fatigueLevel, painArea);

            // event-10: display success message
            statusLabel.setTextFill(Color.GREEN);
            statusLabel.setText("Your fitness report has been submitted successfully");
        } else {
            statusLabel.setText("You have already submitted a fitness report for today.");
        }
    }

    /*
     * event-8: Verify that the player has not already submitted a fitness report for today.
     * Replace with a real check against the medical record file/database.
     */
    private boolean isFitnessReportAlreadySubmittedToday() {
        return false;
    }

    /*
     * event-9: Save the fitness report to the player's medical record file with a timestamp.
     * Replace with real persistence logic.
     */
    private void saveFitnessReport(LocalDate date, double bodyWeight, int fatigueLevel, String painArea) {
        //  persist fitness report
    }
}
