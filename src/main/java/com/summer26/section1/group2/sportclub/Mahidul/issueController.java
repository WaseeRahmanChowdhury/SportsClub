package com.summer26.section1.group2.sportclub.Mahidul;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class issueController
{
    @javafx.fxml.FXML
    private TableView <Medical>TABLE;
    @javafx.fxml.FXML
    private TextField ClearanceNotes;
    @javafx.fxml.FXML
    private TextField DoctorName;
    @javafx.fxml.FXML
    private TableColumn <Medical,String>NAMETABLE;
    @javafx.fxml.FXML
    private TableColumn <Medical,String>SeverityTABLE;
    @javafx.fxml.FXML
    private TextField PlayerNAME;
    @javafx.fxml.FXML
    private TableColumn<Medical,String> INJURYTABLE;
    @javafx.fxml.FXML
    private TableColumn <Medical,String>Recovarytable;
    @javafx.fxml.FXML
    private TableColumn<Medical,String> IDTABLE;
    @javafx.fxml.FXML
    private DatePicker Date;
    @javafx.fxml.FXML
    private TextArea resultLabel;
    ArrayList<Medical> medicalList = new ArrayList<>();
    @javafx.fxml.FXML
    public void initialize() {
        IDTABLE.setCellValueFactory(new PropertyValueFactory<>("playerId"));
        NAMETABLE.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        INJURYTABLE.setCellValueFactory(new PropertyValueFactory<>("injuryType"));
        SeverityTABLE.setCellValueFactory(new PropertyValueFactory<>("severity"));
        Recovarytable.setCellValueFactory(new PropertyValueFactory<>("availabilityStatus"));

        medicalList.add(new Medical(
                "P001",
                "Rahim",
                "Hamstring",
                "Moderate",
                LocalDate.of(2026,7,1),
                14,
                "D001",
                "Recovered"
        ));

        medicalList.add(new Medical(
                "P002",
                "Karim",
                "Ankle",
                "Minor",
                LocalDate.of(2026,7,5),
                10,
                "D002",
                "Under Treatment"
        ));

        medicalList.add(new Medical(
                "P003",
                "Sakib",
                "Muscle",
                "Severe",
                LocalDate.of(2026,6,25),
                30,
                "D003",
                "Recovered"
        ));
    }

    @javafx.fxml.FXML
    public void NEXT(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PostMatchHealthCheack.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @javafx.fxml.FXML
    public void bACK(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(".fxml"));

        Stage stage = (Stage) TABLE.getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }

    @javafx.fxml.FXML
    public void SAVE(ActionEvent actionEvent) {
        Medical m = TABLE.getSelectionModel().getSelectedItem();

        if(m == null){
            resultLabel.setText("Select a player.");
            return;
        }

        if(DoctorName.getText().isEmpty()){
            resultLabel.setText("Enter doctor name.");
            return;
        }

        if(Date.getValue() == null){
            resultLabel.setText("Select clearance date.");
            return;
        }

        if(ClearanceNotes.getText().isEmpty()){
            resultLabel.setText("Enter clearance notes.");
            return;
        }

        m.setAvailabilityStatus("Cleared");

        TABLE.refresh();

        resultLabel.setText(
                "Medical clearance issued for "
                        + m.getPlayerName()
                        + "\nCleared on: "
                        + Date.getValue()
                        + "\nCleared by: "
                        + DoctorName.getText()
        );
    }

    @javafx.fxml.FXML
    public void CLEAR(ActionEvent actionEvent) {
        PlayerNAME.clear();
        DoctorName.clear();
        ClearanceNotes.clear();
        Date.setValue(null);

        TABLE.getItems().clear();

        resultLabel.clear();
    }
}