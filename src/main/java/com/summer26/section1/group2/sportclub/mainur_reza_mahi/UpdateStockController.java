package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.util.ArrayList;

public class UpdateStockController
{
    @javafx.fxml.FXML
    private TableColumn<MenuItem,String> stausTC;
    @javafx.fxml.FXML
    private TableView<MenuItem> allMenuItemTC;
    @javafx.fxml.FXML
    private TableColumn<MenuItem,String> itemIdTC;
    @javafx.fxml.FXML
    private TableColumn<MenuItem,String> categoryTC;
    @javafx.fxml.FXML
    private TableColumn<MenuItem,Integer> currentStockTC;
    @javafx.fxml.FXML
    private TextField itemIdTF;
    @javafx.fxml.FXML
    private TextField quantityTF;
    @javafx.fxml.FXML
    private TableColumn<MenuItem,String> itemNameTC;

    private static final String FILE_NAME = "menu_items.bin";
    private ArrayList<MenuItem> menuItemList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {

        // Step 1: tell each column which field of MenuItem to show
        itemIdTC.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        itemNameTC.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        categoryTC.setCellValueFactory(new PropertyValueFactory<>("category"));
        currentStockTC.setCellValueFactory(new PropertyValueFactory<>("stockQty"));
        stausTC.setCellValueFactory(new PropertyValueFactory<>("availability"));

        // Step 2: load menu items from file
        loadMenuItems();

        // Step 3: when a row is clicked, fill in the Item ID field automatically
        allMenuItemTC.getSelectionModel().selectedItemProperty().addListener((obs, oldItem, newItem) -> {
            if (newItem != null) {
                itemIdTF.setText(newItem.getItemId());
            }
        });
    }

    // loads the file and shows all menu items in the table
    private void loadMenuItems() {

        menuItemList.clear();

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

        allMenuItemTC.getItems().clear();
        allMenuItemTC.getItems().addAll(menuItemList);
    }

    @javafx.fxml.FXML
    public void updateStockButtonOA(ActionEvent actionEvent) {

        // Step 1: get the item ID and new quantity
        String itemId = itemIdTF.getText();
        String quantityText = quantityTF.getText();

        if (itemId.isEmpty() || quantityText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select an item and enter a quantity.");
            alert.showAndWait();
            return;
        }

        // Step 2: find the matching menu item
        MenuItem foundItem = null;
        for (MenuItem m : menuItemList) {
            if (m.getItemId().equals(itemId)) {
                foundItem = m;
            }
        }

        if (foundItem == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No menu item found with that Item ID.");
            alert.showAndWait();
            return;
        }

        // Step 3: update the stock quantity
        int newStock = Integer.parseInt(quantityText);
        foundItem.setStockQty(newStock);

        // Step 4: update availability based on new stock
        if (newStock == 0) {
            foundItem.setAvailability("Out of Stock");
        } else {
            foundItem.setAvailability("Available");
        }

        // Step 5: save the whole list back to the file
        try {
            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(menuItemList);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Step 6: confirmation message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Stock updated for " + foundItem.getItemName() + ". New quantity: " + newStock);
        alert.showAndWait();

        // Step 7: clear the form and refresh the table
        itemIdTF.clear();
        quantityTF.clear();
        loadMenuItems();
    }
}