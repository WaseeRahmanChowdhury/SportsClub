package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TakeOrderController
{
    @javafx.fxml.FXML
    private TextField itemNameTF;
    @javafx.fxml.FXML
    private ComboBox<String> customerTypeCB;
    @javafx.fxml.FXML
    private Label totalPriceLabel;
    @javafx.fxml.FXML
    private TextField customerNameTF;
    @javafx.fxml.FXML
    private TextField itemIdTF;
    @javafx.fxml.FXML
    private TableColumn<OrderItem,Integer> quantityTC;
    @javafx.fxml.FXML
    private TableColumn<OrderItem,Double> unitPriceTC;
    @javafx.fxml.FXML
    private TextField quantityTF;
    @javafx.fxml.FXML
    private ComboBox<String> selectCategoryCB;
    @javafx.fxml.FXML
    private TableColumn<OrderItem,String> itemNameTC;
    @javafx.fxml.FXML
    private TableColumn<OrderItem,Double> subtotalTC;
    @javafx.fxml.FXML
    private TableView<OrderItem> currentOrderTC;

    private static final String MENU_FILE = "menu_items.bin";
    private static final String ORDER_FILE = "orders.bin";
    private static final String ORDER_ITEM_FILE = "order_items.bin";

    private ArrayList<MenuItem> menuItemList = new ArrayList<>();
    private ArrayList<OrderItem> cartList = new ArrayList<>();
    private double totalPrice = 0.0;

    @javafx.fxml.FXML
    public void initialize() {

        // Step 1: fill the dropdowns
        customerTypeCB.getItems().addAll("Player", "Staff", "Coach");
        selectCategoryCB.getItems().addAll("Breakfast", "Launch", "Snack", "Beverage");

        // Step 2: tell each cart column which field of OrderItem to show
        itemNameTC.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        quantityTC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unitPriceTC.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        subtotalTC.setCellValueFactory(new PropertyValueFactory<>("subtotal"));

        // Step 3: load the menu items from file, so we can look up prices
        File file = new File(MENU_FILE);
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(MENU_FILE);
                ObjectInputStream ois = new ObjectInputStream(fis);
                menuItemList = (ArrayList<MenuItem>) ois.readObject();
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @javafx.fxml.FXML
    public void addToOrderButtonOA(ActionEvent actionEvent) {

        // Step 1: get what the manager typed
        String itemId = itemIdTF.getText();
        String quantityText = quantityTF.getText();

        if (itemId.isEmpty() || quantityText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter Item ID and Quantity.");
            alert.showAndWait();
            return;
        }

        // Step 2: find the matching menu item by Item ID
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

        // Step 3: fill in item name automatically from the found item
        itemNameTF.setText(foundItem.getItemName());

        // Step 4: calculate subtotal
        int quantity = Integer.parseInt(quantityText);
        double unitPrice = foundItem.getPrice();
        double subtotal = quantity * unitPrice;

        // Step 5: create the OrderItem (orderId left empty for now, filled in when order is placed)
        OrderItem item = new OrderItem("", foundItem.getItemName(), quantity, unitPrice, subtotal);
        cartList.add(item);

        // Step 6: refresh the cart table
        currentOrderTC.getItems().clear();
        currentOrderTC.getItems().addAll(cartList);

        // Step 7: update the total price
        totalPrice = totalPrice + subtotal;
        totalPriceLabel.setText("BDT : " + totalPrice);

        // Step 8: clear the item input fields for the next item
        itemIdTF.clear();
        itemNameTF.clear();
        quantityTF.clear();
    }

    @javafx.fxml.FXML
    public void placeOrderButtonOA(ActionEvent actionEvent) {

        // Step 1: get customer info
        String customerName = customerNameTF.getText();
        String customerType = customerTypeCB.getValue();

        if (customerName.isEmpty() || customerType == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter customer name and type.");
            alert.showAndWait();
            return;
        }

        if (cartList.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please add at least one item to the order.");
            alert.showAndWait();
            return;
        }

        // Step 2: load existing orders list, to generate the next order ID
        ArrayList<Order> orderList = new ArrayList<>();
        File orderFile = new File(ORDER_FILE);
        if (orderFile.exists()) {
            try {
                FileInputStream fis = new FileInputStream(ORDER_FILE);
                ObjectInputStream ois = new ObjectInputStream(fis);
                orderList = (ArrayList<Order>) ois.readObject();
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Step 3: generate order ID based on list size
        int nextNumber = orderList.size() + 1;
        String orderId = String.format("ORD-%04d", nextNumber);

        // Step 4: get today's date and current time
        String orderDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        String orderTime = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss"));

        // Step 5: create and save the Order
        Order order = new Order(orderId, customerName, customerType, orderDate, orderTime, "pending", totalPrice);
        orderList.add(order);

        try {
            FileOutputStream fos = new FileOutputStream(ORDER_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(orderList);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Step 6: load existing order items list
        ArrayList<OrderItem> orderItemList = new ArrayList<>();
        File orderItemFile = new File(ORDER_ITEM_FILE);
        if (orderItemFile.exists()) {
            try {
                FileInputStream fis = new FileInputStream(ORDER_ITEM_FILE);
                ObjectInputStream ois = new ObjectInputStream(fis);
                orderItemList = (ArrayList<OrderItem>) ois.readObject();
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Step 7: give every item in the cart the new orderId, then save them all
        for (OrderItem item : cartList) {
            item.setOrderId(orderId);
            orderItemList.add(item);
        }

        try {
            FileOutputStream fos = new FileOutputStream(ORDER_ITEM_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(orderItemList);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Step 8: confirmation message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Order placed successfully. Order ID: " + orderId);
        alert.showAndWait();

        // Step 9: clear everything for the next order
        clearOrderButtonOA(actionEvent);
    }

    @javafx.fxml.FXML
    public void clearOrderButtonOA(ActionEvent actionEvent) {
        customerNameTF.clear();
        customerTypeCB.setValue(null);
        itemIdTF.clear();
        itemNameTF.clear();
        selectCategoryCB.setValue(null);
        quantityTF.clear();

        cartList.clear();
        currentOrderTC.getItems().clear();

        totalPrice = 0.0;
        totalPriceLabel.setText("BDT : 0.00");
    }
}