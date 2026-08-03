package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.util.ArrayList;

public class SearchVisitorController
{
    @javafx.fxml.FXML
    private TableColumn<Visitor,String> hostStaffTC;
    @javafx.fxml.FXML
    private TableColumn<Visitor,String> fullNameTC;
    @javafx.fxml.FXML
    private TableColumn<Visitor,String> visitDateTC;
    @javafx.fxml.FXML
    private TableColumn<Visitor,String> visitorIdTC;
    @javafx.fxml.FXML
    private TableColumn<Visitor,String> contactNumberTC;
    @javafx.fxml.FXML
    private TextField nameOrIdFilterTF;
    @javafx.fxml.FXML
    private TableView<Visitor> searchVisitorTC;
    @javafx.fxml.FXML
    private Label contactNoLabel;
    @javafx.fxml.FXML
    private Label visitDateLabel;
    @javafx.fxml.FXML
    private TableColumn<Visitor,String> purposeTC;
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
        contactNumberTC.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        purposeTC.setCellValueFactory(new PropertyValueFactory<>("purpose"));
        hostStaffTC.setCellValueFactory(new PropertyValueFactory<>("hostStaff"));
        visitDateTC.setCellValueFactory(new PropertyValueFactory<>("visitDate"));


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

        searchVisitorTC.getSelectionModel().selectedItemProperty().addListener((obs, oldVisitor, newVisitor) -> {
            if (newVisitor != null) {
                visitorIdLabel.setText(newVisitor.getVisitorId());
                fullNameLabel.setText(newVisitor.getFullName());
                contactNoLabel.setText(newVisitor.getContactNo());
                purposeLabel.setText(newVisitor.getPurpose());
                hostStaffLabel.setText(newVisitor.getHostStaff());
                visitDateLabel.setText(newVisitor.getVisitDate());
            }
        });
    }

    @javafx.fxml.FXML
    public void searchVisitorButtonOA(ActionEvent actionEvent) {

        String search = nameOrIdFilterTF.getText().trim().toLowerCase();

        if (search.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please select a date");
            a.showAndWait();
        }

        ArrayList<Visitor> filteredList = new ArrayList<>();
        for (Visitor v : visitorList) {
            if (v.getVisitorId().toLowerCase().equals(search) || v.getFullName().toLowerCase().equals(search)) {
                filteredList.add(v);
            }
        }

        searchVisitorTC.getItems().clear();
        searchVisitorTC.getItems().addAll(filteredList);
    }

    @javafx.fxml.FXML
    public void clearTableButtonOA(ActionEvent actionEvent) {


        nameOrIdFilterTF.clear();
        searchVisitorTC.getItems().clear();


        visitorIdLabel.setText("");
        fullNameLabel.setText("");
        contactNoLabel.setText("");
        purposeLabel.setText("");
        hostStaffLabel.setText("");
        visitDateLabel.setText("");
    }
}