package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class VisitorLogController
{
    @javafx.fxml.FXML
    private TableView<Visitor> visitorLogTC;
    @javafx.fxml.FXML
    private TableColumn<Visitor,String> fullNameTC;
    @javafx.fxml.FXML
    private Label statusLabel;
    @javafx.fxml.FXML
    private DatePicker visitingDateFilterDP;
    @javafx.fxml.FXML
    private TableColumn<Visitor,String> visitorIdTC;
    @javafx.fxml.FXML
    private Label exitTimeLabel;
    @javafx.fxml.FXML
    private Label entryTimeLabel;
    @javafx.fxml.FXML
    private TableColumn<Visitor,String> exitTimeTC;
    @javafx.fxml.FXML
    private TableColumn<Visitor,String> statusTC;
    @javafx.fxml.FXML
    private TableColumn<Visitor,String> entryTimeTC;
    @javafx.fxml.FXML
    private TableColumn<Visitor,String> purposeTC;
    @javafx.fxml.FXML
    private TableColumn<Visitor,String> hostTC;
    @javafx.fxml.FXML
    private Label purposeLabel;
    @javafx.fxml.FXML
    private Label hostStaffLabel;
    @javafx.fxml.FXML
    private Label visitorIdLabel;
    @javafx.fxml.FXML
    private Label fullNameLabel;

    private static final String FILE_NAME = "visitors.bin";
    private ArrayList<Visitor> visitorList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {

        visitorIdTC.setCellValueFactory(new PropertyValueFactory<>("visitorId"));
        fullNameTC.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        purposeTC.setCellValueFactory(new PropertyValueFactory<>("purpose"));
        hostTC.setCellValueFactory(new PropertyValueFactory<>("hostStaff"));
        entryTimeTC.setCellValueFactory(new PropertyValueFactory<>("entryTime"));
        exitTimeTC.setCellValueFactory(new PropertyValueFactory<>("exitTime"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("status"));


        File file = new File(FILE_NAME);
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(FILE_NAME);
                ObjectInputStream ois = new ObjectInputStream(fis);
                visitorList = (ArrayList<Visitor>) ois.readObject();
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        visitorLogTC.getSelectionModel().selectedItemProperty().addListener((obs, oldVisitor, newVisitor) -> {
            if (newVisitor != null) {
                visitorIdLabel.setText(newVisitor.getVisitorId());
                fullNameLabel.setText(newVisitor.getFullName());
                purposeLabel.setText(newVisitor.getPurpose());
                hostStaffLabel.setText(newVisitor.getHostStaff());
                entryTimeLabel.setText(newVisitor.getEntryTime());
                exitTimeLabel.setText(newVisitor.getExitTime());
                statusLabel.setText(newVisitor.getStatus());
            }
        });
    }

    @javafx.fxml.FXML
    public void loadTableFilterButtonOA(ActionEvent actionEvent) {

        if (visitingDateFilterDP.getValue() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please select a date");
            a.showAndWait();
        }

        String selectedDate = visitingDateFilterDP.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        if (visitingDateFilterDP.getValue().isAfter(LocalDate.now())){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please select a valid date");
            a.showAndWait();
        }
        else{
        ArrayList<Visitor> filteredList = new ArrayList<>();
        for (Visitor v : visitorList) {
            if (v.getVisitDate().equals(selectedDate)) {
                filteredList.add(v);
            }
        }
        visitorLogTC.getItems().clear();
        visitorLogTC.getItems().addAll(filteredList);


        visitorIdLabel.setText("");
        fullNameLabel.setText("");
        purposeLabel.setText("");
        hostStaffLabel.setText("");
        entryTimeLabel.setText("");
        exitTimeLabel.setText("");
        statusLabel.setText("");
        }
    }
}