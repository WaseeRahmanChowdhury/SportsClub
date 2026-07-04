package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.util.Random;

public class ScheduleTrainingSessionController {

    @FXML
    private DatePicker dateField;
    @FXML
    private TextField startTimeField;
    @FXML
    private TextField endTimeField;
    @FXML
    private TextField venueField;
    @FXML
    private ComboBox<String> sessionTypeCombo;
    @FXML
    private ComboBox<String> targetGroupCombo;
    @FXML
    private Label statusLabel;

    @FXML
    private void initialize() {
        // event-4: session type dropdown (Fitness/Technical/Tactical)
        sessionTypeCombo.setItems(FXCollections.observableArrayList("Fitness", "Technical", "Tactical"));

        // event-4: target group dropdown (Full Squad/Forwards/Midfielders/Defenders/Goalkeepers)
        targetGroupCombo.setItems(FXCollections.observableArrayList(
                "Full Squad", "Forwards", "Midfielders", "Defenders", "Goalkeepers"));
    }

    @FXML
    private void scheduleTrainingSession() {
        statusLabel.setTextFill(Color.RED);

        // event-5: validate date - must be today or a future date
        LocalDate date = dateField.getValue();
        if (date == null) {
            statusLabel.setText("Please select a date.");
            return;
        }

        if (date.isBefore(LocalDate.now())) {
            statusLabel.setText("Date must be today or a future date.");
            return;
        }

        String startTime = startTimeField.getText();
        if (startTime == null || startTime.trim().isEmpty()) {
            statusLabel.setText("Start time must not be empty.");
            return;
        }

        String endTime = endTimeField.getText();
        if (endTime == null || endTime.trim().isEmpty()) {
            statusLabel.setText("End time must not be empty.");
            return;
        }

        String venue = venueField.getText();
        if (venue == null || venue.trim().isEmpty()) {
            statusLabel.setText("Venue must not be empty.");
            return;
        }

        String sessionType = sessionTypeCombo.getValue();
        if (sessionType == null) {
            statusLabel.setText("Please select a session type.");
            return;
        }

        String targetGroup = targetGroupCombo.getValue();
        if (targetGroup == null) {
            statusLabel.setText("Please select a target group.");
            return;
        }

        // event-6: verify that the selected venue is not already booked for another session
        // on the same date and overlapping time
        if (isVenueAlreadyBooked(venue, date, startTime, endTime)) {
            statusLabel.setText("The selected venue is already booked for an overlapping session on this date.");
            return;
        }

        // event-7: save the training session to the training schedule file and generate a session ID
        String sessionId = saveTrainingSession(date, startTime, endTime, venue, sessionType, targetGroup);

        // event-9: display confirmation
        statusLabel.setTextFill(Color.GREEN);
        statusLabel.setText("Training session scheduled. Session ID: " + sessionId + ". All targeted players have been notified.");
    }

    /*
     * event-6: Verify that the selected venue is not already booked for another session
     * on the same date and overlapping time.
     * Replace with a real check against the training schedule file.
     */
    private boolean isVenueAlreadyBooked(String venue, LocalDate date, String startTime, String endTime) {
        return false;
    }

    /*
     * event-7: Save the training session to the training schedule file and generate a session ID
     * in the format TRN-XXXX.
     * Replace with real persistence logic.
     */
    private String saveTrainingSession(LocalDate date, String startTime, String endTime,
                                        String venue, String sessionType, String targetGroup) {
        int randomNumber = new Random().nextInt(9999);
        return String.format("TRN-%04d", randomNumber);
    }
}
