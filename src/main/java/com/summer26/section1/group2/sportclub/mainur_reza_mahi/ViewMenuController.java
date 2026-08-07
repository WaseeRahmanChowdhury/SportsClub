package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.util.ArrayList;

public class ViewMenuController
{
    @javafx.fxml.FXML
    private TableColumn<MenuItem,Double> priceTC;
    @javafx.fxml.FXML
    private TableView<MenuItem> viewMenuTC;
    @javafx.fxml.FXML
    private ComboBox<String> filterByCategoryCB;
    @javafx.fxml.FXML
    private TableColumn<MenuItem,String> itemIdTC;
    @javafx.fxml.FXML
    private TableColumn<MenuItem,String> statusTC;
    @javafx.fxml.FXML
    private TableColumn<MenuItem,String> categoryTC;
    @javafx.fxml.FXML
    private TableColumn<MenuItem,Integer> stockTC;
    @javafx.fxml.FXML
    private TableColumn<MenuItem,String> itemNameTC;

    private static final String FILE_NAME = "menu_items.bin";
    private ArrayList<MenuItem> menuItemList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {

        filterByCategoryCB.getItems().addAll("All", "Breakfast", "Launch", "Snack", "Beverage");

        itemIdTC.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        itemNameTC.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        categoryTC.setCellValueFactory(new PropertyValueFactory<>("category"));
        priceTC.setCellValueFactory(new PropertyValueFactory<>("price"));
        stockTC.setCellValueFactory(new PropertyValueFactory<>("stockQty"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("availability"));


        File file = new File(FILE_NAME);
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(FILE_NAME);
                ObjectInputStream ois = new ObjectInputStream(fis);
                menuItemList = (ArrayList<MenuItem>) ois.readObject();
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        viewMenuTC.getItems().clear();
        viewMenuTC.getItems().addAll(menuItemList);
    }

    @javafx.fxml.FXML
    public void filterItemButtonOA(ActionEvent actionEvent) {

        if (filterByCategoryCB.getValue() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please select a category");
            a.showAndWait();
            return;
        }

        if (filterByCategoryCB.getValue().equals("All")) {
            viewMenuTC.getItems().clear();
            viewMenuTC.getItems().addAll(menuItemList);
            return;
        }


        ArrayList<MenuItem> filteredList = new ArrayList<>();
        for (MenuItem item : menuItemList) {
            if (item.getCategory().equals(filterByCategoryCB.getValue())) {
                filteredList.add(item);
            }
        }

        viewMenuTC.getItems().clear();
        viewMenuTC.getItems().addAll(filteredList);
    }
}