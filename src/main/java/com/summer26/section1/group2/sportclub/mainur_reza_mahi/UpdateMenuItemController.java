package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.util.ArrayList;

public class UpdateMenuItemController
{
    @javafx.fxml.FXML
    private TableColumn<MenuItem,Double> priceTC;
    @javafx.fxml.FXML
    private TextField priceTF;
    @javafx.fxml.FXML
    private TableColumn<MenuItem,String> categoryTC;
    @javafx.fxml.FXML
    private TextField itemIdTF;
    @javafx.fxml.FXML
    private TableView<MenuItem> currentMenuItemTC;
    @javafx.fxml.FXML
    private TableColumn<MenuItem,Integer> stockTC;
    @javafx.fxml.FXML
    private TableColumn<MenuItem,String> itemNameTC;
    @javafx.fxml.FXML
    private TextField itemNameTF;
    @javafx.fxml.FXML
    private TableColumn<MenuItem,String> itemIdTC;
    @javafx.fxml.FXML
    private TableColumn<MenuItem,String> statusTC;
    @javafx.fxml.FXML
    private ComboBox<String> categoryCB;
    @javafx.fxml.FXML
    private ComboBox<String> stockCB;
    @javafx.fxml.FXML
    private TextField statusTF;

    private static final String FILE_NAME = "menu_items.bin";
    private ArrayList<MenuItem> menuItemList = new ArrayList<>();
    private MenuItem selectedItem;

    @javafx.fxml.FXML
    public void initialize() {

        // Step 1: fill the category dropdown
        categoryCB.getItems().addAll("Breakfast", "Launch", "Snack", "Beverage");

        // Step 2: fill the stock dropdown with some sample numbers
        stockCB.getItems().addAll("0", "5", "10", "15", "20", "25", "30", "50");

        // Step 3: tell each column which field of MenuItem to show
        itemIdTC.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        itemNameTC.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        categoryTC.setCellValueFactory(new PropertyValueFactory<>("category"));
        priceTC.setCellValueFactory(new PropertyValueFactory<>("price"));
        stockTC.setCellValueFactory(new PropertyValueFactory<>("stockQty"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("availability"));

        // Step 4: load menu items from file
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

        // Step 5: show items in the table
        currentMenuItemTC.getItems().clear();
        currentMenuItemTC.getItems().addAll(menuItemList);

        // Step 6: when a row is clicked, fill in the edit form below
        currentMenuItemTC.getSelectionModel().selectedItemProperty().addListener((obs, oldItem, newItem) -> {
            if (newItem != null) {
                selectedItem = newItem;

                itemIdTF.setText(newItem.getItemId());
                itemNameTF.setText(newItem.getItemName());
                categoryCB.setValue(newItem.getCategory());
                priceTF.setText(String.valueOf(newItem.getPrice()));
                stockCB.setValue(String.valueOf(newItem.getStockQty()));
                statusTF.setText(newItem.getAvailability());
            }
        });
    }

    @javafx.fxml.FXML
    public void saveChangesButtonOA(ActionEvent actionEvent) {

        // Step 1: make sure a row was selected first
        if (selectedItem == null) {
            return;
        }

        // Step 2: get the updated values from the form
        String itemName = itemNameTF.getText();
        String category = categoryCB.getValue();
        double price = Double.parseDouble(priceTF.getText());
        int stockQty = Integer.parseInt(stockCB.getValue());
        String status = statusTF.getText();

        // Step 3: update the selected item's fields
        selectedItem.setItemName(itemName);
        selectedItem.setCategory(category);
        selectedItem.setPrice(price);
        selectedItem.setStockQty(stockQty);
        selectedItem.setAvailability(status);

        // Step 4: save the whole list back to the file
        try {
            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(menuItemList);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Step 5: refresh the table so the change shows immediately
        currentMenuItemTC.getItems().clear();
        currentMenuItemTC.getItems().addAll(menuItemList);

        // Step 6: clear the form
        clearButtonOA(actionEvent);
    }

    @javafx.fxml.FXML
    public void clearButtonOA(ActionEvent actionEvent) {
        itemIdTF.clear();
        itemNameTF.clear();
        categoryCB.setValue(null);
        priceTF.clear();
        stockCB.setValue(null);
        statusTF.clear();
        selectedItem = null;
    }
}