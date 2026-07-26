package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class SubmitFitnessReportController {

    private static final String FILE_NAME = "FitnessReport.bin";

    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField bodyWeightField;
    @FXML
    private ComboBox<Integer> fatigueLevelCombo;
    @FXML
    private ComboBox<String> painAreaCombo;
    @FXML
    private Label statusLabel;

    private ArrayList<FitnessReport> reportData = new ArrayList<>();

    @FXML
    private void initialize() {
        // event-4: date (auto-filled)
        datePicker.setValue(LocalDate.now());

        // event-6: fatigue level 1-10 dropdown
        fatigueLevelCombo.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        // event-4: pain area dropdown (none/leg/back/shoulder/other)
        painAreaCombo.setItems(FXCollections.observableArrayList("None", "Leg", "Back", "Shoulder", "Other"));
        painAreaCombo.getSelectionModel().selectFirst();
    }

    @FXML
    private void submitFitnessReport() {

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

        // event-8: verify that player has not already submitted a fitness report for today
        if (!isFitnessReportAlreadySubmittedToday()) {
            // event-9: save fitness report to the player's medical record file with timestamp
            saveFitnessReport(datePicker.getValue(), bodyWeight, fatigueLevel, painArea);

            // event-10: display success message

            statusLabel.setText("Your fitness report has been submitted successfully");
        } else {
            statusLabel.setText("You have already submitted a fitness report for today.");
        }
    }

    /*
     * event-8: Verify that the player has not already submitted a fitness report for today.
     */
    private boolean isFitnessReportAlreadySubmittedToday() {
        ArrayList<FitnessReport> currentReports = readReportsFromFile();
        LocalDate today = datePicker.getValue();

        for (FitnessReport report : currentReports) {
            if (report.getDate().equals(today)) {
                return true;
            }
        }

        return false;
    }

    /*
     * event-9: Save the fitness report to the player's medical record file with a timestamp.
     * The new report is appended to the list already stored in the bin file,
     * then the whole list is written back to the file.
     */
    private void saveFitnessReport(LocalDate date, double bodyWeight, int fatigueLevel, String painArea) {
        ArrayList<FitnessReport> currentReports = readReportsFromFile();
        currentReports.add(new FitnessReport(date, bodyWeight, fatigueLevel, painArea));

        try (FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject(currentReports);

        } catch (IOException e) {
            e.printStackTrace();
        }

        reportData = currentReports;
    }

    private ArrayList<FitnessReport> readReportsFromFile() {
        ArrayList<FitnessReport> reports = new ArrayList<>();

        try (FileInputStream fileIn = new FileInputStream(FILE_NAME);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            reports = (ArrayList<FitnessReport>) objectIn.readObject();

        } catch (EOFException e) {
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        return reports;
    }

    /*
     * Simple representation of one fitness report entry
     * (date, body weight, fatigue level, pain area).
     */
    public static class FitnessReport implements Serializable {
        private LocalDate date;
        private double bodyWeight;
        private int fatigueLevel;
        private String painArea;

        public FitnessReport() {
        }

        public FitnessReport(LocalDate date, double bodyWeight, int fatigueLevel, String painArea) {
            this.date = date;
            this.bodyWeight = bodyWeight;
            this.fatigueLevel = fatigueLevel;
            this.painArea = painArea;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public double getBodyWeight() {
            return bodyWeight;
        }

        public void setBodyWeight(double bodyWeight) {
            this.bodyWeight = bodyWeight;
        }

        public int getFatigueLevel() {
            return fatigueLevel;
        }

        public void setFatigueLevel(int fatigueLevel) {
            this.fatigueLevel = fatigueLevel;
        }

        public String getPainArea() {
            return painArea;
        }

        public void setPainArea(String painArea) {
            this.painArea = painArea;
        }
    }
}