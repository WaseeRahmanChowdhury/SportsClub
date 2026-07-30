package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MarkExitController
{
    @javafx.fxml.FXML
    private TableColumn<Visitor,String> visitorIdTC;
    @javafx.fxml.FXML
    private TableColumn<Visitor,String> hostStaffTC;
    @javafx.fxml.FXML
    private TextField visitorIdTF;
    @javafx.fxml.FXML
    private TableView<Visitor> markExitTC;
    @javafx.fxml.FXML
    private TableColumn<Visitor,String> fullNameTC;
    @javafx.fxml.FXML
    private TableColumn<Visitor,String> entryTimeTC;
    @javafx.fxml.FXML
    private TableColumn<Visitor,String> purposeTC;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void confirmExitButtonOA(ActionEvent actionEvent) {
    }
}