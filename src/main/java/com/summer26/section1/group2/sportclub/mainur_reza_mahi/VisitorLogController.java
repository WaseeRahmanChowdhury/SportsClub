package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class VisitorLogController
{

    @javafx.fxml.FXML
    private TableView<Visitor> visitorLogTC;
    @javafx.fxml.FXML
    private TableColumn<Visitor,String> fullNameTC;
    @javafx.fxml.FXML
    private TextField idSelectionTF;
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

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void showVisitorDetailsButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void loadTableFilterButtonOA(ActionEvent actionEvent) {
    }
}
