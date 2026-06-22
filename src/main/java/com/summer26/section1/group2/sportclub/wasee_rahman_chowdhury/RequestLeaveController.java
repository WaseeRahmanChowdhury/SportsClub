package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Random;

public class RequestLeaveController {

    @FXML
    private ComboBox<String> leaveTypeCombo;
    @FXML
    private TextField startDateField;
    @FXML
    private TextField endDateField;
    @FXML
    private Label statusLabel;

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @FXML
    private void initialize() {
        // event-4: leave type dropdown (Personal/Medical)
        leaveTypeCombo.setItems(FXCollections.observableArrayList("Personal", "Medical"));
        leaveTypeCombo.getSelectionModel().selectFirst();
    }

    @FXML
    private void submitLeaveRequest() {
        statusLabel.setTextFill(Color.RED);

        String leaveType = leaveTypeCombo.getValue();

        // event-5: validate start date - must be a future date in DD-MM-YYYY format
        LocalDate startDate;
        try {
            startDate = LocalDate.parse(startDateField.getText(), DATE_FORMAT);
        } catch (DateTimeParseException e) {
            statusLabel.setText("Start date must be in DD-MM-YYYY format.");
            return;
        }

        if (!startDate.isAfter(LocalDate.now())) {
            statusLabel.setText("Start date must be a future date.");
            return;
        }

        // event-6: validate end date - must be equal to or after the start date
        LocalDate endDate;
        try {
            endDate = LocalDate.parse(endDateField.getText(), DATE_FORMAT);
        } catch (DateTimeParseException e) {
            statusLabel.setText("End date must be in DD-MM-YYYY format.");
            return;
        }

        if (endDate.isBefore(startDate)) {
            statusLabel.setText("End date must be equal to or after the start date.");
            return;
        }

        // event-7: save the leave request to the leave request file and notify the coach
        String requestId = saveLeaveRequest(leaveType, startDate, endDate);

        // event-8: display confirmation with request ID
        statusLabel.setTextFill(Color.GREEN);
        statusLabel.setText("Your leave request has been submitted. Request ID: " + requestId);
    }

    /*
     * event-7: Save the leave request to the leave request file and notify the coach.
     * Replace with real persistence + coach notification logic.
     *
     * return a generated request ID in the format LRQ-XXXXXX
     */
    private String saveLeaveRequest(String leaveType, LocalDate startDate, LocalDate endDate) {
        // persist leave request and notify coach
        int randomNumber = new Random().nextInt(999999);
        return String.format("LRQ-%06d", randomNumber);
    }
}
