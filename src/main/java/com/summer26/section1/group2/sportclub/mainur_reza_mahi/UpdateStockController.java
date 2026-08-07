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

        itemIdTC.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        itemNameTC.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        categoryTC.setCellValueFactory(new PropertyValueFactory<>("category"));
        currentStockTC.setCellValueFactory(new PropertyValueFactory<>("stockQty"));
        stausTC.setCellValueFactory(new PropertyValueFactory<>("availability"));
        loadMenuItems();
    }

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

        if (itemIdTF.getText().isEmpty() || quantityTF.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select an item and enter a quantity.");
            alert.showAndWait();
            return;
        }

        MenuItem foundItem = null;
        for (MenuItem m : menuItemList) {
            if (m.getItemId().equals(itemIdTF.getText())) {
                foundItem = m;
            }
        }

        if (foundItem == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No menu item found with that Item ID.");
            alert.showAndWait();
            return;
        }

        //update the stock quantity
        int newStock = Integer.parseInt(quantityTF.getText());
        foundItem.setStockQty(newStock);

        //update availability
        if (newStock == 0) {
            foundItem.setAvailability("Out of Stock");
        } else {
            foundItem.setAvailability("Available");
        }

        //save the whole list to the file
        try {
            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(menuItemList);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        itemIdTF.clear();
        quantityTF.clear();
        loadMenuItems();
    }
}