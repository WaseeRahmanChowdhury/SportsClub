package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.util.ArrayList;

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
    private TableColumn<MenuItem,String> itemNameTC;
    @javafx.fxml.FXML
    private TextField stockQuantityTF;
    @javafx.fxml.FXML
    private ComboBox<String> selectCategoryCB;

    private static final String FILE_NAME = "menu_items.bin";
    private ArrayList<MenuItem> menuItemList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        selectCategoryCB.getItems().addAll("Breakfast","Launch","Snack","Beverage");

        itemIdTC.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        itemNameTC.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        categoryTC.setCellValueFactory(new PropertyValueFactory<>("category"));
        priceTC.setCellValueFactory(new PropertyValueFactory<>("price"));
        stockTC.setCellValueFactory(new PropertyValueFactory<>("stockQty"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("availability"));

        // Step 1: load existing menu items from file, if it exists
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

        // Step 2: show existing items in the table right away
        currentMenuItemTC.getItems().clear();
        currentMenuItemTC.getItems().addAll(menuItemList);
    }

    @javafx.fxml.FXML
    public void addMenuItemButtonOA(ActionEvent actionEvent) {

        // Step 1: get the values the user entered
        String itemName = itemNameTF.getText();
        String category = selectCategoryCB.getValue();
        String priceText = priceTF.getText();
        String stockText = stockQuantityTF.getText();

        // Step 2: basic validation - check nothing is empty
        if (itemName.isEmpty() || category == null || priceText.isEmpty() || stockText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in all fields.");
            alert.showAndWait();
            return;
        }

        // Step 3: convert price and stock text into numbers
        double price = Double.parseDouble(priceText);
        int stockQty = Integer.parseInt(stockText);

        // Step 4: generate item ID based on list size
        int nextNumber = menuItemList.size() + 1;
        String itemId = String.format("ITM-%04d", nextNumber);

        // Step 5: decide availability based on stock quantity
        String availability = "Available";
        if (stockQty == 0) {
            availability = "Out of Stock";
        }

        // Step 6: create the MenuItem object
        MenuItem menuItem = new MenuItem(itemId, itemName, category,availability , price ,stockQty);

        // Step 7: add to list and save back to file
        menuItemList.add(menuItem);

        try {
            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(menuItemList);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Step 8: refresh the table
        currentMenuItemTC.getItems().clear();
        currentMenuItemTC.getItems().addAll(menuItemList);

        // Step 9: clear the form
        itemNameTF.clear();
        selectCategoryCB.setValue(null);
        priceTF.clear();
        stockQuantityTF.clear();
    }
}