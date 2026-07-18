package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.time.LocalDate;

public class ScheduleMatchController {

    @FXML
    private DatePicker matchDateField;
    @FXML
    private TextField opponentClubNameField;
    @FXML
    private TextField venueNameField;
    @FXML
    private Label statusLabel;

    @FXML
    private void scheduleMatch() {
        statusLabel.setTextFill(Color.RED);


        LocalDate matchDate = matchDateField.getValue();
        if (matchDate == null || opponentClubNameField.getText().isBlank() || venueNameField.getText().isBlank()) {
            statusLabel.setText("Please fill in all fields.");
            return;
        }

        if (!matchDate.isAfter(LocalDate.now())) {
            statusLabel.setText("Match date must be a future date.");
            return;
        }

        String matchId = Match.scheduleMatch(
                matchDate,
                opponentClubNameField.getText().trim(),
                venueNameField.getText().trim()
        );

        ActivityLog.log(ActivityLog.TYPE_MATCH,
                "Scheduled match vs " + opponentClubNameField.getText().trim() + " (" + matchId + ")", "Admin");

        statusLabel.setTextFill(Color.GREEN);
        statusLabel.setText("Match scheduled. Match ID: " + matchId);
    }
}
