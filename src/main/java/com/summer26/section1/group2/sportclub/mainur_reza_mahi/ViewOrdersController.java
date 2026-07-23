package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ViewOrdersController
{
    @javafx.fxml.FXML
    private TableColumn orderTimeTC;
    @javafx.fxml.FXML
    private DatePicker orderDateDP;
    @javafx.fxml.FXML
    private TableColumn customerTypeTC;
    @javafx.fxml.FXML
    private TableView itemSellingListTC;
    @javafx.fxml.FXML
    private TableColumn statusTC;
    @javafx.fxml.FXML
    private TableColumn totalPriceTC;
    @javafx.fxml.FXML
    private TableColumn orderIdTC;
    @javafx.fxml.FXML
    private TableColumn quantityTC;
    @javafx.fxml.FXML
    private TableView viewOrdersTC;
    @javafx.fxml.FXML
    private TableColumn customerTC;
    @javafx.fxml.FXML
    private TableColumn itemNameTC;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void loadOrdersButtonOA(ActionEvent actionEvent) {
    }
}