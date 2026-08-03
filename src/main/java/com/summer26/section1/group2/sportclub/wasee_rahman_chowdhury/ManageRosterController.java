package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Random;

public class ManageRosterController {

    private static final String FILE_NAME = "PlayerRoster.bin";

    @FXML
    private TextField fullNameField;
    @FXML
    private DatePicker dateOfBirthField;
    @FXML
    private ComboBox<String> playingPositionCombo;
    @FXML
    private Label statusLabel;

    private ArrayList<PlayerRecord> rosterData = new ArrayList<>();

    @FXML
    private void initialize() {
        // event-4: playing position dropdown (GK/DEF/MID/FWD)
        ArrayList<String> positions = new ArrayList<>();
        positions.add("GK");
        positions.add("DEF");
        positions.add("MID");
        positions.add("FWD");

        playingPositionCombo.getItems().addAll(positions);
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

        String playingPosition = playingPositionCombo.getValue();
        if (playingPosition == null) {
            statusLabel.setText("Please select a playing position.");
            return;
        }

        // event-8: assign a unique player ID
        String playerId = generatePlayerId();

        // persist the new player record
        savePlayer(fullName, dateOfBirth, playingPosition, playerId);

        // event-9: display success message with the generated player ID
        statusLabel.setTextFill(Color.GREEN);
        statusLabel.setText("Player registered successfully. Player ID: " + playerId);
    }

    /*
     * event-8: Assign a unique player ID in the format PLY-XXXXXX.
     */
    private String generatePlayerId() {
        int randomNumber = new Random().nextInt(999999);
        return String.format("PLY-%06d", randomNumber);
    }

    /*
     * Persist the newly registered player to the roster data file.
     * The new player appended to the list already stored in the bin file,
     * then the whole list is written back to the file.
     */
    private void savePlayer(String fullName, LocalDate dateOfBirth, String playingPosition, String playerId) {
        ArrayList<PlayerRecord> currentRoster = readRosterFromFile();
        currentRoster.add(new PlayerRecord(playerId, fullName, dateOfBirth, playingPosition));

        try (FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject(currentRoster);

        } catch (IOException e) {
            e.printStackTrace();
        }

        rosterData = currentRoster;
    }

    private ArrayList<PlayerRecord> readRosterFromFile() {
        ArrayList<PlayerRecord> roster = new ArrayList<>();

        try (FileInputStream fileIn = new FileInputStream(FILE_NAME);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            roster = (ArrayList<PlayerRecord>) objectIn.readObject();

        } catch (EOFException e) {
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        return roster;
    }

    /*
     * Simple representation of one player roster entry
     * (player ID, full name, date of birth, playing position).
     */
    public static class PlayerRecord implements Serializable {
        private String playerId;
        private String fullName;
        private LocalDate dateOfBirth;
        private String playingPosition;

        public PlayerRecord() {
        }

        public PlayerRecord(String playerId, String fullName, LocalDate dateOfBirth, String playingPosition) {
            this.playerId = playerId;
            this.fullName = fullName;
            this.dateOfBirth = dateOfBirth;
            this.playingPosition = playingPosition;
        }

        public String getPlayerId() {
            return playerId;
        }

        public void setPlayerId(String playerId) {
            this.playerId = playerId;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public LocalDate getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public String getPlayingPosition() {
            return playingPosition;
        }

        public void setPlayingPosition(String playingPosition) {
            this.playingPosition = playingPosition;
        }
    }
}
