package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PlayerAvailabilityManagerController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField positionField;
    @FXML
    private ComboBox<String> statusCombo;

    @FXML
    private TableView<PlayerAvailabilityRow> playerTable;

    @FXML
    private TableColumn<PlayerAvailabilityRow, String> colName;
    @FXML
    private TableColumn<PlayerAvailabilityRow, String> colPosition;
    @FXML
    private TableColumn<PlayerAvailabilityRow, String> colStatus;
    @FXML
    private TableColumn<PlayerAvailabilityRow, String> colLastUpdated;

    @FXML
    private ComboBox<String> updateStatusCombo;

    @FXML
    private Label statusLabel;

    private final ArrayList<PlayerAvailabilityRow> playerData = new ArrayList<>();

    private final ArrayList<String> allowedStatuses = new ArrayList<>();

    private static final String FILE_NAME = "PlayerAvailability.bin";

    @FXML
    private void initialize() {
        allowedStatuses.add("Available");
        allowedStatuses.add("Injured");
        allowedStatuses.add("Suspended");
        allowedStatuses.add("Resting");

        statusCombo.getItems().addAll(allowedStatuses);
        updateStatusCombo.getItems().addAll(allowedStatuses);

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colLastUpdated.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedDate"));

        // event-4: display player list in a table
        loadPlayerAvailabilityFromFile();

        playerTable.getItems().addAll(playerData);
    }

    @FXML
    private void onAddPlayer() {
        statusLabel.setText("");

        String name = nameField.getText();
        if (name == null || name.trim().isEmpty()) {
            statusLabel.setText("Player Name must not be empty.");
            return;
        }

        String position = positionField.getText();
        if (position == null || position.trim().isEmpty()) {
            statusLabel.setText("Position must not be empty.");
            return;
        }

        String status = statusCombo.getValue();
        if (status == null) {
            statusLabel.setText("Please select a Status.");
            return;
        }

        PlayerAvailabilityRow row = new PlayerAvailabilityRow(name.trim(), position.trim(), status, LocalDate.now().toString());

        playerData.add(row);
        playerTable.getItems().add(row);

        savePlayerAvailabilityToFile();

        statusLabel.setText("Player added: " + name);

        nameField.clear();
        positionField.clear();
        statusCombo.setValue(null);
    }

    /*
     * event-6/event-7: Coach selects a player row, then chooses a new status from the
     * dropdown; validate that selected status is one of the four allowed values
     * (enforced by the ComboBox itself), then persist the change.
     */
    @FXML
    private void onUpdateStatus() {
        statusLabel.setText("");

        PlayerAvailabilityRow selectedRow = playerTable.getSelectionModel().getSelectedItem();
        if (selectedRow == null) {
            statusLabel.setText("Please select a player from the table first.");
            return;
        }

        String newStatus = updateStatusCombo.getValue();
        if (newStatus == null) {
            statusLabel.setText("Please select a new Status.");
            return;
        }

        // event-7: validate that selected status is one of the four allowed values
        if (!allowedStatuses.contains(newStatus)) {
            statusLabel.setText("Status must be one of: Available, Injured, Suspended, Resting.");
            return;
        }

        selectedRow.setStatus(newStatus);
        selectedRow.setLastUpdatedDate(LocalDate.now().toString());

        playerTable.refresh();

        // persist the updated status to the roster data file
        savePlayerAvailabilityToFile();

        // event-8: display confirmation
        statusLabel.setText("Status updated for " + selectedRow.getName() + ": " + newStatus);

        updateStatusCombo.setValue(null);
    }

    private void savePlayerAvailabilityToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(playerData);
        } catch (IOException e) {
            statusLabel.setText("ERROR: Could not save player availability data to file.");
        }
    }

    @SuppressWarnings("unchecked")
    private void loadPlayerAvailabilityFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            ArrayList<PlayerAvailabilityRow> loadedData = (ArrayList<PlayerAvailabilityRow>) in.readObject();
            playerData.addAll(loadedData);
        } catch (IOException | ClassNotFoundException e) {
            statusLabel.setText("ERROR: Could not load player availability data from file.");
        }
    }

    /*
     * Simple representation of one row of the player availability table.
     */
    public static class PlayerAvailabilityRow implements Serializable {
        private String name;
        private String position;
        private String status;
        private String lastUpdatedDate;

        public PlayerAvailabilityRow() {
        }

        public PlayerAvailabilityRow(String name, String position, String status, String lastUpdatedDate) {
            this.name = name;
            this.position = position;
            this.status = status;
            this.lastUpdatedDate = lastUpdatedDate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLastUpdatedDate() {
            return lastUpdatedDate;
        }

        public void setLastUpdatedDate(String lastUpdatedDate) {
            this.lastUpdatedDate = lastUpdatedDate;
        }
    }
}
