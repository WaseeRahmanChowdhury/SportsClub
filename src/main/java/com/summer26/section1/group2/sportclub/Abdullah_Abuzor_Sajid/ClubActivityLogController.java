package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ClubActivityLogController {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    @FXML
    private ComboBox<String> activityTypeCombo;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<ActivityLog> activityTable;
    @FXML
    private TableColumn<ActivityLog, String> dateColumn;
    @FXML
    private TableColumn<ActivityLog, String> typeColumn;
    @FXML
    private TableColumn<ActivityLog, String> titleColumn;
    @FXML
    private TableColumn<ActivityLog, String> createdByColumn;

    private final ObservableList<ActivityLog> activityRows = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // event-4: filter options for activity type
        activityTypeCombo.setItems(FXCollections.observableArrayList(
                ActivityLog.TYPE_ALL,
                ActivityLog.TYPE_MATCH,
                ActivityLog.TYPE_TRAINING,
                ActivityLog.TYPE_STAFF,
                ActivityLog.TYPE_ANNOUNCEMENT,
                ActivityLog.TYPE_SPONSORSHIP
        ));
        activityTypeCombo.setValue(ActivityLog.TYPE_ALL);

        dateColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getActivityDate().format(DATE_FORMAT)));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("activityType"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("activityTitle"));
        createdByColumn.setCellValueFactory(new PropertyValueFactory<>("createdBy"));

        activityTable.setItems(activityRows);

        loadActivities();
    }

    // event-5: Admin selects an activity type from the dropdown list
    @FXML
    private void onFilterChanged() {
        loadActivities();
    }

    // event-8: search by activity title or created-by name
    @FXML
    private void onSearch() {
        loadActivities();
    }

    private void loadActivities() {
        List<ActivityLog> filtered = ActivityLog.getActivities(activityTypeCombo.getValue());
        filtered = ActivityLog.search(filtered, searchField.getText());
        activityRows.setAll(filtered);
    }
}
