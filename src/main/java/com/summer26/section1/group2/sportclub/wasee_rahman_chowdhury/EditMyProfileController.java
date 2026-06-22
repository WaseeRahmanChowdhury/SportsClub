package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class EditMyProfileController {

    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField emergencyContactField;
    @FXML
    private TextField jerseyNumberField;
    @FXML
    private TextField addressField;
    @FXML
    private ComboBox<String> preferredFootCombo;
    @FXML
    private Label statusLabel;

    @FXML
    private void initialize() {
        // event-4: Preferred foot dropdown (Right/Left/Both)
        preferredFootCombo.setItems(FXCollections.observableArrayList("Right", "Left", "Both"));

        loadCurrentProfileData();
    }

    /**
     * event-4: Load and display current profile data in an editable form
     * (phone number, emergency contact number, jersey number, address, preferred foot).
     * Replace with real data source lookup for the logged-in player.
     */
    private void loadCurrentProfileData() {
        phoneNumberField.setText("");
        emergencyContactField.setText("");
        jerseyNumberField.setText("");
        addressField.setText("");
        preferredFootCombo.getSelectionModel().clearSelection();
        // fill fields from the player's existing profile data
    }

    @FXML
    private void saveProfile() {
        statusLabel.setTextFill(Color.RED);

        // event-5: validate phone number - must be 11 digits starting with 01
        String phoneNumber = phoneNumberField.getText();
        if (!isValidBangladeshiPhoneNumber(phoneNumber)) {
            statusLabel.setText("Phone number must be 11 digits starting with 01.");
            return;
        }

        // event-6: validate emergency contact number - must be 11 digits starting with 01
        String emergencyContact = emergencyContactField.getText();
        if (!isValidBangladeshiPhoneNumber(emergencyContact)) {
            statusLabel.setText("Emergency contact number must be 11 digits starting with 01.");
            return;
        }

        // event-7: validate home address - must not be empty
        String address = addressField.getText();
        if (address == null || address.trim().isEmpty()) {
            statusLabel.setText("Home address must not be empty.");
            return;
        }

        String jerseyNumber = jerseyNumberField.getText();
        String preferredFoot = preferredFootCombo.getValue();

        // event-8: save the updated profile data to the player profile file
        saveProfileData(phoneNumber, emergencyContact, jerseyNumber, address, preferredFoot);

        // event-9: display success message
        statusLabel.setTextFill(Color.GREEN);
        statusLabel.setText("Your profile has been updated successfully");
    }

    /*
     Validates that a phone number is exactly 11 digits and starts with "01".
     */
    private boolean isValidBangladeshiPhoneNumber(String number) {
        return number != null && number.matches("01\\d{9}");
    }

    /*
     * event-8: Save the updated profile data to the player profile file.
     * Replace with real persistence logic.
     */
    private void saveProfileData(String phoneNumber, String emergencyContact,
                                  String jerseyNumber, String address, String preferredFoot) {
        //  persist updated profile data
    }
}
