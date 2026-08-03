package com.summer26.section1.group2.sportclub.Mahidul;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class InjuryReportController {
    @javafx.fxml.FXML
    private DatePicker end_date;
    @javafx.fxml.FXML
    private TableColumn<Medical, String> statistics_table;
    @javafx.fxml.FXML
    private TableColumn<Medical, Integer> value_table;
    @javafx.fxml.FXML
    private TableView<Medical> table;
    @javafx.fxml.FXML
    private DatePicker start_date;

    ArrayList<Medical> medicalList = new ArrayList<>();
    ArrayList<Medical> filtered = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        statistics_table.setCellValueFactory(
                new PropertyValueFactory<>("statistic"));

        value_table.setCellValueFactory(
                new PropertyValueFactory<>("value"));
        medicalList.add(new Medical(
                "P001",
                "Rahim",
                "Hamstring",
                "Minor",
                LocalDate.of(2026, 7, 1),
                10,
                "D001",
                "Unavailable"
        ));
        medicalList.add(new Medical(
                "P002",
                "Karim",
                "Knee",
                "Severe",
                LocalDate.of(2026, 7, 5),
                30,
                "D002",
                "Unavailable"
        ));

        medicalList.add(new Medical(
                "P003",
                "Sakib",
                "Ankle",
                "Moderate",
                LocalDate.of(2026, 7, 8),
                20,
                "D001",
                "Unavailable"
        ));

        medicalList.add(new Medical(
                "P004",
                "Tamim",
                "Hamstring",
                "Minor",
                LocalDate.of(2026, 7, 10),
                12,
                "D003",
                "Unavailable"
        ));

        medicalList.add(new Medical(
                "P005",
                "Riyad",
                "Shoulder",
                "Severe",
                LocalDate.of(2026, 7, 15),
                40,
                "D002",
                "Unavailable"
        ));


    }

    @javafx.fxml.FXML
    public void clear(ActionEvent actionEvent) {
        start_date.setValue(null);
        end_date.setValue(null);
        table.getItems().clear();
    }

    @javafx.fxml.FXML
    public void back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin1_dashboard.fxml"));
        Stage stage = (Stage) table.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @javafx.fxml.FXML
    public void injuryStatistics(ActionEvent actionEvent) {
        LocalDate start = start_date.getValue();
        LocalDate end = end_date.getValue();

        if (start == null || end == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please select both dates.");
            alert.showAndWait();
            return;
        }

        if (end.isBefore(start)) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("End date must be after Start date.");
            alert.showAndWait();
            return;
        }

        int total = 0;
        int minor = 0;
        int moderate = 0;
        int severe = 0;

        table.getItems().clear();

        for (Medical m : medicalList) {

            if ((m.getAssessmentDate().isEqual(start) ||
                    m.getAssessmentDate().isAfter(start))
                    &&
                    (m.getAssessmentDate().isEqual(end) ||
                            m.getAssessmentDate().isBefore(end))) {

                total++;

                if (m.getSeverity().equals("Minor")) {
                    minor++;
                }

                if (m.getSeverity().equals("Moderate")) {
                    moderate++;
                }

                if (m.getSeverity().equals("Severe")) {
                    severe++;
                }

            }

        }

        table.getItems().add(new Medical("Total Injuries", "Sakib Al Hasan", "Hamstring", "Moderate", LocalDate.now(), total, "D001", "Recovering"));
        table.getItems().add(new Medical("Minor", "Sakib Al Hasan", "Hamstring", "Moderate", LocalDate.now(), minor, "D001", "Recovering"));
        table.getItems().add(new Medical("Moderate", "Sakib Al Hasan", "Hamstring", "Moderate", LocalDate.now(), moderate, "D001", "Recovering"));
        table.getItems().add(new Medical("Severe", "Sakib Al Hasan", "Hamstring", "Moderate", LocalDate.now(), severe, "D001", "Recovering"));

    }
}

