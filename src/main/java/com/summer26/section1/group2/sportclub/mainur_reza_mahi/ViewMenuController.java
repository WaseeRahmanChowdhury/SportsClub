package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import javafx.event.ActionEvent;
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

        // Step 1: fill the category filter dropdown
        filterByCategoryCB.getItems().addAll("All", "Breakfast", "Launch", "Snack", "Beverage");

        // Step 2: tell each column which field of MenuItem to show
        itemIdTC.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        itemNameTC.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        categoryTC.setCellValueFactory(new PropertyValueFactory<>("category"));
        priceTC.setCellValueFactory(new PropertyValueFactory<>("price"));
        stockTC.setCellValueFactory(new PropertyValueFactory<>("stockQty"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("availability"));

        // Step 3: load existing menu items from file, if it exists
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

        // Step 4: show all items in the table right away
        viewMenuTC.getItems().clear();
        viewMenuTC.getItems().addAll(menuItemList);
    }

    @javafx.fxml.FXML
    public void filterItemButtonOA(ActionEvent actionEvent) {

        // Step 1: get the selected category
        String selectedCategory = filterByCategoryCB.getValue();

        if (selectedCategory == null) {
            return;
        }

        // Step 2: if "All" is selected, just show everything
        if (selectedCategory.equals("All")) {
            viewMenuTC.getItems().clear();
            viewMenuTC.getItems().addAll(menuItemList);
            return;
        }

        // Step 3: otherwise, filter the list by matching category
        ArrayList<MenuItem> filteredList = new ArrayList<>();
        for (MenuItem item : menuItemList) {
            if (item.getCategory().equals(selectedCategory)) {
                filteredList.add(item);
            }
        }

        // Step 4: show the filtered list in the table
        viewMenuTC.getItems().clear();
        viewMenuTC.getItems().addAll(filteredList);
    }
}