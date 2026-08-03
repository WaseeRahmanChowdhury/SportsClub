package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.util.ArrayList;

public class MarkOrderServedController
{
    @javafx.fxml.FXML
    private TableColumn<Order,String> orderTimeTC;
    @javafx.fxml.FXML
    private TableColumn<Order,String> customerTypeTC;
    @javafx.fxml.FXML
    private TextField orderIdTF;
    @javafx.fxml.FXML
    private TableColumn<Order,Double> totalPriceTC;
    @javafx.fxml.FXML
    private TableView<Order> pendingOrdersTC;
    @javafx.fxml.FXML
    private TableColumn<Order,String> orderIdTC;
    @javafx.fxml.FXML
    private TableColumn<Order,String> customerTC;

    private static final String ORDER_FILE = "orders.bin";
    private ArrayList<Order> orderList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {

        // Step 1: tell each column which field of Order to show
        orderIdTC.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        customerTC.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerTypeTC.setCellValueFactory(new PropertyValueFactory<>("customerType"));
        totalPriceTC.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        orderTimeTC.setCellValueFactory(new PropertyValueFactory<>("orderTime"));

        // Step 2: load pending orders right away when page opens
        loadPendingOrders();

        // Step 3: when a row is clicked, fill in the Order ID field automatically
        pendingOrdersTC.getSelectionModel().selectedItemProperty().addListener((obs, oldOrder, newOrder) -> {
            if (newOrder != null) {
                orderIdTF.setText(newOrder.getOrderId());
            }
        });
    }

    // loads the file, then shows only orders with status = pending
    private void loadPendingOrders() {

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

        ArrayList<Order> pendingList = new ArrayList<>();
        for (Order o : orderList) {
            if (o.getStatus().equals("pending")) {
                pendingList.add(o);
            }
        }

        pendingOrdersTC.getItems().clear();
        pendingOrdersTC.getItems().addAll(pendingList);
    }

    @javafx.fxml.FXML
    public void markAsServedButtonOA(ActionEvent actionEvent) {

        // Step 1: get the order ID typed or filled in
        String orderId = orderIdTF.getText();

        if (orderId.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select or enter an Order ID.");
            alert.showAndWait();
            return;
        }

        // Step 2: find the matching order in the list
        Order foundOrder = null;
        for (Order o : orderList) {
            if (o.getOrderId().equals(orderId)) {
                foundOrder = o;
            }
        }

        if (foundOrder == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No order found with that Order ID.");
            alert.showAndWait();
            return;
        }

        // Step 3: update the status to served
        foundOrder.setStatus("served");

        // Step 4: save the whole list back to the file
        try {
            FileOutputStream fos = new FileOutputStream(ORDER_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(orderList);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Step 5: confirmation message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Order " + orderId + " has been marked as served.");
        alert.showAndWait();

        // Step 6: clear the text field and refresh the table
        orderIdTF.clear();
        loadPendingOrders();
    }
}