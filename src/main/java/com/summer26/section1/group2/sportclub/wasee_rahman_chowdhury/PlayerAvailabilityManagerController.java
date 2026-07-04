package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

import java.time.LocalDate;

public class PlayerAvailabilityManagerController {

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
    private Label statusLabel;

    private final ObservableList<PlayerAvailabilityRow> playerData = FXCollections.observableArrayList();

    private static final ObservableList<String> ALLOWED_STATUSES =
            FXCollections.observableArrayList("Available", "Injured", "Suspended", "Resting");

    @FXML
    private void initialize() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));

        // event-6: coach selects new status from dropdown
        colStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        colStatus.setCellFactory(ComboBoxTableCell.forTableColumn(ALLOWED_STATUSES));
        colStatus.setOnEditCommit(event -> onStatusEdited(event.getRowValue(), event.getNewValue()));

        colLastUpdated.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedDate"));

        playerTable.setItems(playerData);
        playerTable.setEditable(true);

        // event-4: display player list in a table
        loadPlayerAvailability();
    }

    /*
     * event-4: Display player list in a table: name, position, current status
     * (Available/Injured/Suspended/Resting), last updated date.
     * Replace with real data source lookup.
     */
    private void loadPlayerAvailability() {
        playerData.clear();
        // populate playerData with all squad players and their current status
    }

    /*
     * event-6/event-7: Coach selects new status from dropdown; validate that selected
     * status is one of the four allowed values (enforced by the ComboBox cell itself),
     * then persist the change.
     */
    private void onStatusEdited(PlayerAvailabilityRow row, String newStatus) {
        statusLabel.setTextFill(Color.RED);

        // event-7: validate that selected status is one of the four allowed values
        if (!ALLOWED_STATUSES.contains(newStatus)) {
            statusLabel.setText("Status must be one of: Available, Injured, Suspended, Resting.");
            return;
        }

        row.setStatus(newStatus);
        row.setLastUpdatedDate(LocalDate.now().toString());

        // persist the updated status to the roster data file
        saveStatusUpdate(row.getName(), newStatus);

        // event-8: display confirmation
        statusLabel.setTextFill(Color.GREEN);
        statusLabel.setText("Status updated for " + row.getName() + ": " + newStatus);
    }

    /*
     * Persist the updated availability status for the given player.
     * Replace with real persistence logic.
     */
    private void saveStatusUpdate(String playerName, String newStatus) {
        // persist status update
    }

    /*
     * Simple representation of one row of the player availability table.
     */
    public static class PlayerAvailabilityRow {
        private final javafx.beans.property.SimpleStringProperty name = new javafx.beans.property.SimpleStringProperty();
        private final javafx.beans.property.SimpleStringProperty position = new javafx.beans.property.SimpleStringProperty();
        private final javafx.beans.property.SimpleStringProperty status = new javafx.beans.property.SimpleStringProperty();
        private final javafx.beans.property.SimpleStringProperty lastUpdatedDate = new javafx.beans.property.SimpleStringProperty();

        public PlayerAvailabilityRow() {
        }

        public PlayerAvailabilityRow(String name, String position, String status, String lastUpdatedDate) {
            this.name.set(name);
            this.position.set(position);
            this.status.set(status);
            this.lastUpdatedDate.set(lastUpdatedDate);
        }

        public String getName() {
            return name.get();
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public String getPosition() {
            return position.get();
        }

        public void setPosition(String position) {
            this.position.set(position);
        }

        public String getStatus() {
            return status.get();
        }

        public void setStatus(String status) {
            this.status.set(status);
        }

        public javafx.beans.property.SimpleStringProperty statusProperty() {
            return status;
        }

        public String getLastUpdatedDate() {
            return lastUpdatedDate.get();
        }

        public void setLastUpdatedDate(String lastUpdatedDate) {
            this.lastUpdatedDate.set(lastUpdatedDate);
        }
    }
}
