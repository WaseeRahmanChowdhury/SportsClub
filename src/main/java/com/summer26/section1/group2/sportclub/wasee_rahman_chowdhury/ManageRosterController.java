package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

public class ManageRosterController {

    @FXML
    private TextField fullNameField;
    @FXML
    private DatePicker dateOfBirthField;
    @FXML
    private TextField nationalityField;
    @FXML
    private ComboBox<String> playingPositionCombo;
    @FXML
    private TextField squadNumberField;
    @FXML
    private Label statusLabel;

    @FXML
    private void initialize() {
        // event-4: playing position dropdown (GK/DEF/MID/FWD)
        playingPositionCombo.setItems(FXCollections.observableArrayList("GK", "DEF", "MID", "FWD"));
    }

    @FXML
    private void addNewPlayer() {
        statusLabel.setTextFill(Color.RED);

        // event-5: full name must not be empty
        String fullName = fullNameField.getText();
        if (fullName == null || fullName.trim().isEmpty()) {
            statusLabel.setText("Full name must not be empty.");
            return;
        }

        // event-5: validate date of birth so that player age is between 15 and 45 years
        LocalDate dateOfBirth = dateOfBirthField.getValue();
        if (dateOfBirth == null) {
            statusLabel.setText("Please select a date of birth.");
            return;
        }

        int age = Period.between(dateOfBirth, LocalDate.now()).getYears();
        if (age < 15 || age > 45) {
            statusLabel.setText("Player age must be between 15 and 45 years.");
            return;
        }

        String nationality = nationalityField.getText();
        if (nationality == null || nationality.trim().isEmpty()) {
            statusLabel.setText("Nationality must not be empty.");
            return;
        }

        String playingPosition = playingPositionCombo.getValue();
        if (playingPosition == null) {
            statusLabel.setText("Please select a playing position.");
            return;
        }

        // event-6: validate squad number - must be an integer between 1 and 99
        int squadNumber;
        try {
            squadNumber = Integer.parseInt(squadNumberField.getText());
        } catch (NumberFormatException e) {
            statusLabel.setText("Squad number must be an integer between 1 and 99.");
            return;
        }

        if (squadNumber < 1 || squadNumber > 99) {
            statusLabel.setText("Squad number must be an integer between 1 and 99.");
            return;
        }

        // event-7: verify that the chosen squad number is not already assigned to another active player
        if (isSquadNumberTaken(squadNumber)) {
            statusLabel.setText("Squad number " + squadNumber + " is already assigned to another active player.");
            return;
        }

        // event-8: assign a unique player ID
        String playerId = generatePlayerId();

        // persist the new player record
        savePlayer(fullName, dateOfBirth, nationality, playingPosition, squadNumber, playerId);

        // event-9: display success message with the generated player ID
        statusLabel.setTextFill(Color.GREEN);
        statusLabel.setText("Player registered successfully. Player ID: " + playerId);
    }

    /*
     * event-7: Verify that the chosen squad number is not already assigned to another active player.
     * Replace with a real check against the roster data file.
     */
    private boolean isSquadNumberTaken(int squadNumber) {
        return false;
    }

    /*
     * event-8: Assign a unique player ID in the format PLY-XXXXXX.
     * Replace with real ID-generation logic tied to persisted data.
     */
    private String generatePlayerId() {
        int randomNumber = new Random().nextInt(999999);
        return String.format("PLY-%06d", randomNumber);
    }

    /*
     * Persist the newly registered player to the roster data file.
     * Replace with real persistence logic.
     */
    private void savePlayer(String fullName, LocalDate dateOfBirth, String nationality,
                             String playingPosition, int squadNumber, String playerId) {
        // persist new player record
    }
}
