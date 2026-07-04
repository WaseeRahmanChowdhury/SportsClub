package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.Random;

public class RequestEquipmentController {

    @FXML
    private TextField itemNameField;
    @FXML
    private TextField quantityNeededField;
    @FXML
    private TextArea intendedPurposeField;
    @FXML
    private ComboBox<String> urgencyLevelCombo;
    @FXML
    private Label statusLabel;

    @FXML
    private void initialize() {
        // event-4: urgency level dropdown (Normal/High/Critical)
        urgencyLevelCombo.setItems(FXCollections.observableArrayList("Normal", "High", "Critical"));
        urgencyLevelCombo.getSelectionModel().selectFirst();
    }

    @FXML
    private void submitEquipmentRequest() {
        statusLabel.setTextFill(Color.RED);

        // event-6: validate item name is not empty
        String itemName = itemNameField.getText();
        if (itemName == null || itemName.trim().isEmpty()) {
            statusLabel.setText("Item name must not be empty.");
            return;
        }

        // event-5: validate quantity must be a positive integer
        int quantityNeeded;
        try {
            quantityNeeded = Integer.parseInt(quantityNeededField.getText());
        } catch (NumberFormatException e) {
            statusLabel.setText("Quantity needed must be a positive integer.");
            return;
        }

        if (quantityNeeded <= 0) {
            statusLabel.setText("Quantity needed must be a positive integer.");
            return;
        }

        String intendedPurpose = intendedPurposeField.getText();
        String urgencyLevel = urgencyLevelCombo.getValue();

        // event-7: save the equipment request to the requests file, assign a request ID
        String requestId = saveEquipmentRequest(itemName, quantityNeeded, intendedPurpose, urgencyLevel);

        // event-9: display confirmation
        statusLabel.setTextFill(Color.GREEN);
        statusLabel.setText("Request submitted. Request ID: " + requestId + ". Equipment Manager has been notified.");
    }

    /*
     * event-7: Save the equipment request to the requests file, assign a request ID
     * in the format EQR-XXXX.
     * Replace with real persistence logic.
     */
    private String saveEquipmentRequest(String itemName, int quantityNeeded, String intendedPurpose, String urgencyLevel) {
        int randomNumber = new Random().nextInt(9999);
        return String.format("EQR-%04d", randomNumber);
    }
}
