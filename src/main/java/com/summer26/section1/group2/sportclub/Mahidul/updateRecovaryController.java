package com.summer26.section1.group2.sportclub.Mahidul;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class updateRecovaryController
{
    @javafx.fxml.FXML
    private TableColumn<Medical,String> injuryTypeTable;
    @javafx.fxml.FXML
    private TableColumn<Medical,String> nameTable;
    @javafx.fxml.FXML
    private TableColumn<Medical,String> SeverityTable;
    @javafx.fxml.FXML
    private TableColumn <Medical,String>StatusTable;
    @javafx.fxml.FXML
    private TableColumn<Medical,LocalDate> timestamptable;
    @javafx.fxml.FXML
    private TableView<Medical> table;
    @javafx.fxml.FXML
    private TableColumn<Medical,Integer> Recovary_Datetable;
    @javafx.fxml.FXML
    private TableColumn <Medical, LocalDate>InjuryDateTable;

    @javafx.fxml.FXML
    public void initialize() {
        nameTable.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        injuryTypeTable.setCellValueFactory(new PropertyValueFactory<>("injuryType"));
        SeverityTable.setCellValueFactory(new PropertyValueFactory<>("severity"));
        StatusTable.setCellValueFactory(new PropertyValueFactory<>("availabilityStatus"));
        Recovary_Datetable.setCellValueFactory(new PropertyValueFactory<>("recoveryDays"));
        InjuryDateTable.setCellValueFactory(new PropertyValueFactory<>("assessmentDate"));
        timestamptable.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        table.getItems().add(new Medical(
                "P001",
                "Mahidul Islam",
                "Muscle",
                "Moderate",
                LocalDate.of(2026, 6, 20),
                21,
                "D001",
                "Unavailable"
        ));
        table.getItems().add(new Medical(
                "P002",
                "Rahim",
                "Ligament",
                "Minor",
                LocalDate.of(2026, 6, 22),
                14,
                "D002",
                "Recovering"
        ));
        table.getItems().add(new Medical(
                "P003",
                "Karim",
                "Bone",
                "Severe",
                LocalDate.of(2026, 6, 15),
                45,
                "D003",
                "Unavailable"
        ));


    }

    @javafx.fxml.FXML
    public void Add(ActionEvent actionEvent) {

        Medical m = new Medical(
                "P004",                  // playerId
                "Sakib Al Hasan",        // playerName
                "Hamstring",             // injuryType
                "Moderate",              // severity
                LocalDate.now(),         // assessmentDate
                21,                      // recoveryDays
                "D001",                  // doctorId
                "Recovering"             // availabilityStatus
        );
        table.getItems().add(m);
    }

    @javafx.fxml.FXML
    public void Back_button(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin1_dashboard.fxml"));

        Stage stage = (Stage) table.getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }
}