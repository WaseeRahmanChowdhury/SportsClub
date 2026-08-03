package com.summer26.section1.group2.sportclub.Mahidul;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MedicalStaffRecordAnInjuryAssessmentController
{
    @javafx.fxml.FXML
    private TableColumn <Medical,String> start_date_table;
    @javafx.fxml.FXML
    private ComboBox<String> endTimeCB;
    @javafx.fxml.FXML
    private TextField playerIdTF;
    @javafx.fxml.FXML
    private TableColumn <Medical,Integer>player_idt;
    @javafx.fxml.FXML
    private Label Session_type_tablw;
    @javafx.fxml.FXML
    private TableColumn<Medical,String> Physiotherapist_table;
    @javafx.fxml.FXML
    private ComboBox<String> sessionTypeCB;
    @javafx.fxml.FXML
    private Label messageLabel;
    @javafx.fxml.FXML
    private TableColumn <Medical, LocalDate>session_date_table;
    @javafx.fxml.FXML
    private ComboBox<String> startTimeCB;
    @javafx.fxml.FXML
    private Label Physiotherapist;
    @javafx.fxml.FXML
    private DatePicker sessionDateDP;
    @javafx.fxml.FXML
    private TableColumn <Medical,String> end_date_table;
    @javafx.fxml.FXML
    private TextField physioNameTF;
    @javafx.fxml.FXML
    private TableView <Medical>table;
    ArrayList<Medical> list = new ArrayList<>();
    @javafx.fxml.FXML
    private TableColumn <Medical,String>Session_type_table;

    @javafx.fxml.FXML
    public void initialize() {
        sessionTypeCB.getItems().addAll(
                "Massage",
                "Exercise",
                "Ultrasound",
                "Ice Therapy"
        );
        startTimeCB.getItems().addAll(
                "08:00",
                "09:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00"
        );
        endTimeCB.getItems().addAll(
                "08:30",
                "09:30",
                "10:30",
                "11:30",
                "12:30",
                "13:30",
                "14:30",
                "15:30",
                "16:30"
        );

        player_idt.setCellValueFactory(new PropertyValueFactory<>("playerId"));
        session_date_table.setCellValueFactory(new PropertyValueFactory<>("sessionDate"));
        start_date_table.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        end_date_table.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        Physiotherapist_table.setCellValueFactory(new PropertyValueFactory<>("physiotherapist"));
        Session_type_table.setCellValueFactory(new PropertyValueFactory<>("sessionType"));


    }

    @javafx.fxml.FXML
    public void viewBtn(ActionEvent actionEvent) {
        table.getItems().clear();

        for(Medical m : list){
            table.getItems().add(m);
        }
    }

    @javafx.fxml.FXML
    public void scheduleBtn(ActionEvent actionEvent) {
        int playerId =Integer.parseInt(playerIdTF.getText()) ;
        LocalDate sessionDate = sessionDateDP.getValue();
        String startTime = startTimeCB.getValue();
        String endTime = endTimeCB.getValue();
        String physiotherapist = physioNameTF.getText();
        String sessionType = sessionTypeCB.getValue();

        if(sessionDate == null || startTime == null
                || endTime == null || physiotherapist.isEmpty()
                || sessionType == null){

            messageLabel.setText("Please fill all fields.");
            return;
        }

        Medical obj = new Medical(String.valueOf(playerId),sessionDate,startTime,endTime,physiotherapist,sessionType);

        list.add(obj);

        messageLabel.setText("Session Scheduled Successfully");

        playerIdTF.clear();
        sessionDateDP.setValue(null);
        startTimeCB.setValue(null);
        endTimeCB.setValue(null);
        physioNameTF.clear();
        sessionTypeCB.setValue(null);
    }

    @javafx.fxml.FXML
    public void back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin1_dashboard.fxml"));

        Stage stage = (Stage) table.getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();


    }

    @FXML
    public void Next(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("medical.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }
}