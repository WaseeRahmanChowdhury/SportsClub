package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.time.LocalDate;

public class AddSponsorController {

    @FXML
    private TextField companyNameField;
    @FXML
    private TextField contactPersonField;
    @FXML
    private TextField contactNumberField;
    @FXML
    private TextField annualAmountField;
    @FXML
    private DatePicker contractStartField;
    @FXML
    private DatePicker contractEndField;
    @FXML
    private Label statusLabel;

    @FXML
    private void addSponsor() {
        statusLabel.setTextFill(Color.RED);

        if (companyNameField.getText().isBlank() || contactPersonField.getText().isBlank()
                || contactNumberField.getText().isBlank() || annualAmountField.getText().isBlank()
                || contractStartField.getValue() == null || contractEndField.getValue() == null) {
            statusLabel.setText("Please fill in all fields.");
            return;
        }

        double annualAmount;
        try {
            annualAmount = Double.parseDouble(annualAmountField.getText().trim());
        } catch (NumberFormatException e) {
            statusLabel.setText("Sponsorship amount must be numeric.");
            return;
        }

        // event-7: validate sponsorship amount is a positive number
        if (annualAmount <= 0) {
            statusLabel.setText("Sponsorship amount must be a positive number.");
            return;
        }

        LocalDate startDate = contractStartField.getValue();
        LocalDate endDate = contractEndField.getValue();
        if (!endDate.isAfter(startDate)) {
            statusLabel.setText("Contract end date must be after the start date.");
            return;
        }

        String sponsorId = Sponsor.addSponsor(
                companyNameField.getText().trim(),
                contactPersonField.getText().trim(),
                contactNumberField.getText().trim(),
                annualAmount,
                startDate,
                endDate
        );

        ActivityLog.log(ActivityLog.TYPE_SPONSORSHIP,
                "Added sponsor " + companyNameField.getText().trim() + " (" + sponsorId + ")", "Admin");

        statusLabel.setTextFill(Color.GREEN);
        statusLabel.setText("Sponsor added. Sponsor ID: " + sponsorId);
    }
}
