package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AddMenuItemController
{
    @javafx.fxml.FXML
    private TableColumn<MenuItem,Double> priceTC;
    @javafx.fxml.FXML
    private TextField itemNameTF;
    @javafx.fxml.FXML
    private TextField priceTF;
    @javafx.fxml.FXML
    private TableColumn<MenuItem,String> itemIdTC;
    @javafx.fxml.FXML
    private TableColumn<MenuItem,String> statusTC;
    @javafx.fxml.FXML
    private TableColumn<MenuItem,String> categoryTC;
    @javafx.fxml.FXML
    private TableView<MenuItem> currentMenuItemTC;
    @javafx.fxml.FXML
    private TableColumn<MenuItem,Integer> stockTC;
    @javafx.fxml.FXML
    private Button addMenuItemButtonOA;
    @javafx.fxml.FXML
    private TableColumn<MenuItem,String> itemNameTC;
    @javafx.fxml.FXML
    private TextField stockQuantityTF;
    @javafx.fxml.FXML
    private ComboBox<String> selectCategoryCB;

    @javafx.fxml.FXML
    public void initialize() {
        selectCategoryCB.getItems().addAll("Breakfast","Launch","Snack","Beverage");

        itemIdTC.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        itemNameTC.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        categoryTC.setCellValueFactory(new PropertyValueFactory<>("category"));
        priceTC.setCellValueFactory(new PropertyValueFactory<>("price"));
        stockTC.setCellValueFactory(new PropertyValueFactory<>("stockQty"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("availability"));
    }
}