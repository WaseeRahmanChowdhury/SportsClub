package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.time.LocalDate;

public class InitiateTransferController {

    @FXML
    private TextField playerIdField;
    @FXML
    private ComboBox<String> directionCombo;
    @FXML
    private TextField counterpartClubField;
    @FXML
    private TextField transferFeeField;
    @FXML
    private DatePicker effectiveDateField;
    @FXML
    private Label statusLabel;

    @FXML
    private void initialize() {
        directionCombo.getItems().addAll("Incoming", "Outgoing");
    }

    @FXML
    private void initiateTransfer() {
        statusLabel.setTextFill(Color.RED);

        if (playerIdField.getText().isBlank() || directionCombo.getValue() == null
                || counterpartClubField.getText().isBlank() || transferFeeField.getText().isBlank()
                || effectiveDateField.getValue() == null) {
            statusLabel.setText("Please fill in all fields.");
            return;
        }

        double transferFee;
        try {
            transferFee = Double.parseDouble(transferFeeField.getText().trim());
        } catch (NumberFormatException e) {
            statusLabel.setText("Transfer fee must be a numeric value.");
            return;
        }

        // event-6: validate transfer fee is non-negative
        if (transferFee < 0) {
            statusLabel.setText("Transfer fee must be a non-negative numeric value.");
            return;
        }

        LocalDate effectiveDate = effectiveDateField.getValue();
        // event-7: validate effective date is a future date
        if (!effectiveDate.isAfter(LocalDate.now())) {
            statusLabel.setText("Effective date must be a future date.");
            return;
        }

        String transferId = Transfer.initiateTransfer(
                playerIdField.getText().trim(),
                directionCombo.getValue(),
                counterpartClubField.getText().trim(),
                transferFee,
                effectiveDate
        );

        statusLabel.setTextFill(Color.GREEN);
        statusLabel.setText("Transfer initiated. Transfer ID: " + transferId + ". Finance Officer has been notified.");
    }
}
