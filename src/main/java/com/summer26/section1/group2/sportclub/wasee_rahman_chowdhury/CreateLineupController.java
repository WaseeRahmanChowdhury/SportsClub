package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class CreateLineupController {

    @FXML
    private ComboBox<String> fixtureCombo;

    @FXML
    private TableView<PlayerSelectionRow> squadTable;

    @FXML
    private TableColumn<PlayerSelectionRow, Boolean> colSelected;
    @FXML
    private TableColumn<PlayerSelectionRow, String> colName;
    @FXML
    private TableColumn<PlayerSelectionRow, String> colSquadNumber;
    @FXML
    private TableColumn<PlayerSelectionRow, String> colPosition;

    @FXML
    private Label statusLabel;

    private final ObservableList<PlayerSelectionRow> squadData = FXCollections.observableArrayList();

    private static final ObservableList<String> FIELD_POSITIONS =
            FXCollections.observableArrayList("GK", "DEF", "MID", "FWD");

    @FXML
    private void initialize() {
        // event-4: display list of upcoming fixtures
        loadUpcomingFixtures();

        colSelected.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
        colSelected.setCellFactory(CheckBoxTableCell.forTableColumn(colSelected));

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSquadNumber.setCellValueFactory(new PropertyValueFactory<>("squadNumber"));

        colPosition.setCellValueFactory(cellData -> cellData.getValue().assignedPositionProperty());
        colPosition.setCellFactory(ComboBoxTableCell.forTableColumn(FIELD_POSITIONS));

        squadTable.setItems(squadData);
        squadTable.setEditable(true);
    }

    /*
     * event-4: Load list of upcoming fixtures.
     * Replace with real data source lookup.
     */
    private void loadUpcomingFixtures() {
        fixtureCombo.setItems(FXCollections.observableArrayList());
        // populate fixtureCombo with upcoming fixtures
    }

    @FXML
    private void onFixtureSelected() {
        String selectedFixture = fixtureCombo.getValue();
        if (selectedFixture == null) {
            return;
        }
        // event-5: load the full squad details from the file
        loadFullSquad(selectedFixture);
    }

    /*
     * event-5: Load the full squad details from the file.
     * event-6: Display the list of available players for selection.
     * Replace with real data source lookup.
     */
    private void loadFullSquad(String fixtureName) {
        squadData.clear();
        // populate squadData with the full squad's available players
    }

    @FXML
    private void saveLineup() {
        statusLabel.setTextFill(Color.RED);

        String selectedFixture = fixtureCombo.getValue();
        if (selectedFixture == null) {
            statusLabel.setText("Please select a fixture first.");
            return;
        }

        // event-7: coach selects 11 starting players and assigns a field position to each
        long selectedCount = squadData.stream().filter(PlayerSelectionRow::isSelected).count();
        long goalkeeperCount = squadData.stream()
                .filter(PlayerSelectionRow::isSelected)
                .filter(row -> "GK".equals(row.getAssignedPosition()))
                .count();

        // event-8: validate that exactly 11 starting players are selected and exactly 1 is assigned as goalkeeper
        if (selectedCount != 11) {
            statusLabel.setText("You must select exactly 11 starting players. Currently selected: " + selectedCount);
            return;
        }

        if (goalkeeperCount != 1) {
            statusLabel.setText("You must assign exactly 1 goalkeeper. Currently assigned: " + goalkeeperCount);
            return;
        }

        // event-9: save the lineup associated with the selected fixture
        saveLineupToFile(selectedFixture);

        // event-10: display lineup confirmation
        statusLabel.setTextFill(Color.GREEN);
        statusLabel.setText("Fixture Successful");
    }

    /*
     * event-9: Save the lineup associated with the selected fixture.
     * Replace with real persistence logic.
     */
    private void saveLineupToFile(String fixtureName) {
        // persist lineup for the selected fixture
    }

    /*
     * Simple representation of one row of the squad selection table.
     */
    public static class PlayerSelectionRow {
        private final SimpleBooleanProperty selected = new SimpleBooleanProperty(false);
        private final SimpleStringProperty name = new SimpleStringProperty();
        private final SimpleStringProperty squadNumber = new SimpleStringProperty();
        private final SimpleStringProperty assignedPosition = new SimpleStringProperty();

        public PlayerSelectionRow() {
        }

        public PlayerSelectionRow(String name, String squadNumber) {
            this.name.set(name);
            this.squadNumber.set(squadNumber);
        }

        public boolean isSelected() {
            return selected.get();
        }

        public SimpleBooleanProperty selectedProperty() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected.set(selected);
        }

        public String getName() {
            return name.get();
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public String getSquadNumber() {
            return squadNumber.get();
        }

        public void setSquadNumber(String squadNumber) {
            this.squadNumber.set(squadNumber);
        }

        public String getAssignedPosition() {
            return assignedPosition.get();
        }

        public SimpleStringProperty assignedPositionProperty() {
            return assignedPosition;
        }

        public void setAssignedPosition(String assignedPosition) {
            this.assignedPosition.set(assignedPosition);
        }
    }
}
