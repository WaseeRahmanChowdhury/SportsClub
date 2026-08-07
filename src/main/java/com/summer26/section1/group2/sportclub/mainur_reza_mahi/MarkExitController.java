package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class MarkExitController
{
    @javafx.fxml.FXML
    private TableColumn<Visitor,String> visitorIdTC;
    @javafx.fxml.FXML
    private TableColumn<Visitor,String> hostStaffTC;
    @javafx.fxml.FXML
    private TableView<Visitor> markExitTC;
    @javafx.fxml.FXML
    private TableColumn<Visitor,String> fullNameTC;
    @javafx.fxml.FXML
    private TableColumn<Visitor,String> entryTimeTC;
    @javafx.fxml.FXML
    private TableColumn<Visitor,String> purposeTC;

    private static final String FILE_NAME = "visitors.bin";
    private ArrayList<Visitor> visitorList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {

        visitorIdTC.setCellValueFactory(new PropertyValueFactory<>("visitorId"));
        fullNameTC.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        purposeTC.setCellValueFactory(new PropertyValueFactory<>("purpose"));
        hostStaffTC.setCellValueFactory(new PropertyValueFactory<>("hostStaff"));
        entryTimeTC.setCellValueFactory(new PropertyValueFactory<>("entryTime"));

        loadInsideVisitorsToday();
    }

    private void loadInsideVisitorsToday() {
            visitorList.clear();

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

            ArrayList<Visitor> insideVisitors = new ArrayList<>();

            for (Visitor v : visitorList) {
                if (v.getStatus().equals("inside")) {
                    insideVisitors.add(v);
                }
            }

            markExitTC.getItems().clear();
            markExitTC.getItems().addAll(insideVisitors);
    }

    @javafx.fxml.FXML
    public void confirmExitButtonOA(ActionEvent actionEvent) {


        Visitor selectedVisitor = markExitTC.getSelectionModel().getSelectedItem();

        if (selectedVisitor == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please select a visitor");
            a.showAndWait();
            return;
        }

        String exitTime = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss"));
        selectedVisitor.setExitTime(exitTime);
        selectedVisitor.setStatus("left");

        try {
            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(visitorList);
            oos.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        loadInsideVisitorsToday();
    }
}