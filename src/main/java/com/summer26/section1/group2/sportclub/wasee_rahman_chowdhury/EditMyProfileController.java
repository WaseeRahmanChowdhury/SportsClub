package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class EditMyProfileController {

    private static final String FILE_NAME = "Player.bin";

    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField jerseyNumberField;
    @FXML
    private ComboBox<String> preferredFootCombo;
    @FXML
    private Label statusLabel;

    private ArrayList<PlayerProfile> profileData = new ArrayList<>();

    @FXML
    private void initialize() {
        // event-4: Preferred foot dropdown (Right/Left/Both)
        ArrayList<String> preferredFootOptions = new ArrayList<>();
        preferredFootOptions.add("Right");
        preferredFootOptions.add("Left");
        preferredFootOptions.add("Both");

        preferredFootCombo.getItems().addAll(preferredFootOptions);

        loadCurrentProfileData();
    }

    /*
      event-4: Load and display current profile data in an editable form
      (phone number, jersey number, preferred foot).
     */
    private void loadCurrentProfileData() {
        phoneNumberField.setText("");
        jerseyNumberField.setText("");
        preferredFootCombo.getSelectionModel().clearSelection();

        ArrayList<PlayerProfile> currentProfiles = readProfileFromFile();

        if (!currentProfiles.isEmpty()) {
            PlayerProfile latestProfile = currentProfiles.get(currentProfiles.size() - 1);

            phoneNumberField.setText(latestProfile.getPhoneNumber());
            jerseyNumberField.setText(latestProfile.getJerseyNumber());
            preferredFootCombo.setValue(latestProfile.getPreferredFoot());
        }

        profileData = currentProfiles;
    }

    @FXML
    private void saveProfile() {

        // event-5: validate phone number - must be 11 digits starting with 01
        String phoneNumber = phoneNumberField.getText();
        if (!isValidBangladeshiPhoneNumber(phoneNumber)) {
            statusLabel.setText("Phone number must be 11 digits starting with 01.");
            return;
        }

        String jerseyNumber = jerseyNumberField.getText();
        String preferredFoot = preferredFootCombo.getValue();

        // event-8: save updated profile data to the player profile file
        saveProfileData(phoneNumber, jerseyNumber, preferredFoot);

        // event-9: display success message
        statusLabel.setText("Your profile has been updated successfully");
    }

    /*
     Validates that a phone number is exactly 11 digits and starts with "01".
     */
    private boolean isValidBangladeshiPhoneNumber(String number) {
        if (number == null) {
            return false;
        }

        if (number.length() != 11) {
            return false;
        }

        if (!number.startsWith("01")) {
            return false;
        }

        for (int i = 0; i < number.length(); i++) {
            char currentChar = number.charAt(i);

            if (currentChar < '0' || currentChar > '9') {
                return false;
            }
        }

        return true;
    }

    /*
      event-8: Save the updated profile data to the player profile file.
      The new profile is appended to the list already stored in the bin file, then the whole list is written back to the file.
     */
    private void saveProfileData(String phoneNumber, String jerseyNumber, String preferredFoot) {
        ArrayList<PlayerProfile> currentProfiles = readProfileFromFile();
        currentProfiles.add(new PlayerProfile(phoneNumber, jerseyNumber, preferredFoot));

        try (FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject(currentProfiles);

        } catch (IOException e) {
            e.printStackTrace();
        }

        profileData = currentProfiles;
    }

    private ArrayList<PlayerProfile> readProfileFromFile() {
        ArrayList<PlayerProfile> profiles = new ArrayList<>();

        try (FileInputStream fileIn = new FileInputStream(FILE_NAME);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            profiles = (ArrayList<PlayerProfile>) objectIn.readObject();

        } catch (EOFException e) {
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        return profiles;
    }

    /*
      Simple representation of one player profile entry
     (phone number, jersey number, preferred foot).
     */
    public static class PlayerProfile implements Serializable {
        private String phoneNumber;
        private String jerseyNumber;
        private String preferredFoot;

        public PlayerProfile() {
        }

        public PlayerProfile(String phoneNumber, String jerseyNumber, String preferredFoot) {
            this.phoneNumber = phoneNumber;
            this.jerseyNumber = jerseyNumber;
            this.preferredFoot = preferredFoot;
        }

        public String getPhoneNumber() {

            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {

            this.phoneNumber = phoneNumber;
        }

        public String getJerseyNumber() {

            return jerseyNumber;
        }

        public void setJerseyNumber(String jerseyNumber) {

            this.jerseyNumber = jerseyNumber;
        }

        public String getPreferredFoot() {

            return preferredFoot;
        }

        public void setPreferredFoot(String preferredFoot) {

            this.preferredFoot = preferredFoot;
        }
    }
}
