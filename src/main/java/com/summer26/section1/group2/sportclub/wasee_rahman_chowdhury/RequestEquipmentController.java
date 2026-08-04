package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.ArrayList;
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

    private final ArrayList<EquipmentRequest> equipmentRequests = new ArrayList<>();

    private static final String FILE_NAME = "EquipmentRequest.bin";

    @FXML
    private void initialize() {
        // event-4: urgency level dropdown (Normal/High/Critical)
        ArrayList<String> urgencyLevels = new ArrayList<>();
        urgencyLevels.add("Normal");
        urgencyLevels.add("High");
        urgencyLevels.add("Critical");

        urgencyLevelCombo.getItems().addAll(urgencyLevels);
        urgencyLevelCombo.getSelectionModel().selectFirst();

        loadEquipmentRequestsFromFile();
    }

    @FXML
    private void submitEquipmentRequest() {
        statusLabel.setText("");

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
        statusLabel.setText("Request submitted. Request ID: " + requestId + ". Equipment Manager has been notified.");

        itemNameField.clear();
        quantityNeededField.clear();
        intendedPurposeField.clear();
        urgencyLevelCombo.getSelectionModel().selectFirst();
    }

    /*
     * event-7: Save the equipment request to the requests file, assign a request ID
     * in the format EQR-XXXX.
     */
    private String saveEquipmentRequest(String itemName, int quantityNeeded, String intendedPurpose, String urgencyLevel) {
        int randomNumber = new Random().nextInt(9999);
        String requestId = String.format("EQR-%04d", randomNumber);

        EquipmentRequest request = new EquipmentRequest(requestId, itemName, quantityNeeded, intendedPurpose, urgencyLevel);

        equipmentRequests.add(request);
        saveEquipmentRequestsToFile();

        return requestId;
    }

    private void saveEquipmentRequestsToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(equipmentRequests);
        } catch (IOException e) {
            statusLabel.setText("ERROR: Could not save the equipment request to file.");
        }
    }

    @SuppressWarnings("unchecked")
    private void loadEquipmentRequestsFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            ArrayList<EquipmentRequest> loadedRequests = (ArrayList<EquipmentRequest>) in.readObject();
            equipmentRequests.addAll(loadedRequests);
        } catch (IOException | ClassNotFoundException e) {
            statusLabel.setText("ERROR: Could not load equipment requests from file.");
        }
    }

    /*
     * Simple representation of one submitted equipment request.
     */
    public static class EquipmentRequest implements Serializable {
        private String requestId;
        private String itemName;
        private int quantityNeeded;
        private String intendedPurpose;
        private String urgencyLevel;

        public EquipmentRequest() {
        }

        public EquipmentRequest(String requestId, String itemName, int quantityNeeded, String intendedPurpose, String urgencyLevel) {
            this.requestId = requestId;
            this.itemName = itemName;
            this.quantityNeeded = quantityNeeded;
            this.intendedPurpose = intendedPurpose;
            this.urgencyLevel = urgencyLevel;
        }

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public int getQuantityNeeded() {
            return quantityNeeded;
        }

        public void setQuantityNeeded(int quantityNeeded) {
            this.quantityNeeded = quantityNeeded;
        }

        public String getIntendedPurpose() {
            return intendedPurpose;
        }

        public void setIntendedPurpose(String intendedPurpose) {
            this.intendedPurpose = intendedPurpose;
        }

        public String getUrgencyLevel() {
            return urgencyLevel;
        }

        public void setUrgencyLevel(String urgencyLevel) {
            this.urgencyLevel = urgencyLevel;
        }
    }
}
