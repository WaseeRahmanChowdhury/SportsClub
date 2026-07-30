package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void clearTableButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void searchVisitorButtonOA(ActionEvent actionEvent) {
    }
}