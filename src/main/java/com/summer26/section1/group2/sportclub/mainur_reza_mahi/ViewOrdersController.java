package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ViewOrdersController
{
    @javafx.fxml.FXML
    private TableColumn<Order,String> orderTimeTC;
    @javafx.fxml.FXML
    private DatePicker orderDateDP;
    @javafx.fxml.FXML
    private TableColumn<Order,String> customerTypeTC;
    @javafx.fxml.FXML
    private TableView<OrderItem> itemSellingListTC;
    @javafx.fxml.FXML
    private TableColumn<Order,String> statusTC;
    @javafx.fxml.FXML
    private TableColumn<Order,Double> totalPriceTC;
    @javafx.fxml.FXML
    private TableColumn<Order,String> orderIdTC;
    @javafx.fxml.FXML
    private TableColumn<OrderItem,Integer> quantityTC;
    @javafx.fxml.FXML
    private TableView<Order> viewOrdersTC;
    @javafx.fxml.FXML
    private TableColumn<Order,String> customerTC;
    @javafx.fxml.FXML
    private TableColumn<OrderItem,String> itemNameTC;

    private static final String ORDER_FILE = "orders.bin";
    private static final String ORDER_ITEM_FILE = "order_items.bin";

    private ArrayList<Order> orderList = new ArrayList<>();
    private ArrayList<OrderItem> orderItemList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {

        // Step 1: tell each order column which field to show
        orderIdTC.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        customerTC.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerTypeTC.setCellValueFactory(new PropertyValueFactory<>("customerType"));
        totalPriceTC.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        orderTimeTC.setCellValueFactory(new PropertyValueFactory<>("orderTime"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Step 2: tell each order-item column which field to show
        itemNameTC.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        quantityTC.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        // Step 3: when an order row is clicked, show its items below
        viewOrdersTC.getSelectionModel().selectedItemProperty().addListener((obs, oldOrder, newOrder) -> {
            if (newOrder != null) {
                showItemsForOrder(newOrder.getOrderId());
            }
        });
    }

    @javafx.fxml.FXML
    public void loadOrdersButtonOA(ActionEvent actionEvent) {

        // Step 1: check a date was picked
        if (orderDateDP.getValue() == null) {
            return;
        }

        // Step 2: load orders from file
        orderList.clear();

        File file = new File(ORDER_FILE);
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(ORDER_FILE);
                ObjectInputStream ois = new ObjectInputStream(fis);
                orderList = (ArrayList<Order>) ois.readObject();
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Step 3: convert picked date to text, same format used when saving
        String selectedDate = orderDateDP.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        // Step 4: filter orders for that date
        ArrayList<Order> filteredList = new ArrayList<>();
        for (Order o : orderList) {
            if (o.getOrderDate().equals(selectedDate)) {
                filteredList.add(o);
            }
        }

        // Step 5: show the filtered list in the table
        viewOrdersTC.getItems().clear();
        viewOrdersTC.getItems().addAll(filteredList);

        // Step 6: clear the item list below, since no order is selected yet
        itemSellingListTC.getItems().clear();
    }

    // loads order_items.bin, then shows only items belonging to the clicked order
    private void showItemsForOrder(String orderId) {

        orderItemList.clear();

        File file = new File(ORDER_ITEM_FILE);
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(ORDER_ITEM_FILE);
                ObjectInputStream ois = new ObjectInputStream(fis);
                orderItemList = (ArrayList<OrderItem>) ois.readObject();
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ArrayList<OrderItem> matchingItems = new ArrayList<>();
        for (OrderItem item : orderItemList) {
            if (item.getOrderId().equals(orderId)) {
                matchingItems.add(item);
            }
        }

        itemSellingListTC.getItems().clear();
        itemSellingListTC.getItems().addAll(matchingItems);
    }
}