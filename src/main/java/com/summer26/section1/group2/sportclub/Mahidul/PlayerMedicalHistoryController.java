package com.summer26.section1.group2.sportclub.Mahidul;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PlayerMedicalHistoryController
{
    @javafx.fxml.FXML
    private Label result;
    @javafx.fxml.FXML
    private TextField namefx;
    @javafx.fxml.FXML
    private TableColumn<Medical,String> injurytypetable;
    @javafx.fxml.FXML
    private TableColumn<Medical,String> playernametable;
    @javafx.fxml.FXML
    private TableColumn <Medical,String>playeridtable;
    @javafx.fxml.FXML
    private TableColumn <Medical,String>severitytable;
    @javafx.fxml.FXML
    private TableColumn <Medical, LocalDate>datetable;
    @javafx.fxml.FXML
    private TextField idfx;
    @javafx.fxml.FXML
    private TableView<Medical> table;
    ArrayList<Medical> medicalList = new ArrayList<>();
    @javafx.fxml.FXML
    public void initialize() {
        playeridtable.setCellValueFactory(new PropertyValueFactory<>("playerId"));
        playernametable.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        injurytypetable.setCellValueFactory(new PropertyValueFactory<>("injuryType"));
        severitytable.setCellValueFactory(new PropertyValueFactory<>("severity"));
        datetable.setCellValueFactory(new PropertyValueFactory<>("assessmentDate"));

        medicalList.add(new Medical(
                "P001",
                "Mahidul Islam",
                "Muscle Tear",
                "Moderate",
                LocalDate.of(2026, 6, 20),
                21,
                "D001",
                "Recovering"
        ));

        medicalList.add(new Medical(
                "P002",
                "Rahim",
                "Ligament Injury",
                "Minor",
                LocalDate.of(2026, 6, 15),
                14,
                "D002",
                "Available"
        ));

        medicalList.add(new Medical(
                "P003",
                "Karim",
                "Fracture",
                "Severe",
                LocalDate.of(2026, 5, 30),
                60,
                "D003",
                "Unavailable"
        ));

        table.getItems().addAll(medicalList);

    }

    @javafx.fxml.FXML
    public void Next(ActionEvent actionEvent) {
        Medical selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            result.setText("Please select a player.");
            return;
        }
        result.setText("Selected: " + selected.getPlayerName());

    }

    @javafx.fxml.FXML
    public void clear(ActionEvent actionEvent) {
        idfx.clear();
        namefx.clear();
        result.setText("");
        table.getItems().clear();
        table.getItems().addAll(medicalList);
    }

    @Deprecated
    public void back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin1_dashboard.fxml"));

        Stage stage = (Stage) table.getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }

    @javafx.fxml.FXML
    public void search(ActionEvent actionEvent) {
        Medical selected = table.getSelectionModel().getSelectedItem();

        if (selected == null) {
            result.setText("Please select a player.");
            return;
        }

        result.setText("Selected: " + selected.getPlayerName());

    }
}