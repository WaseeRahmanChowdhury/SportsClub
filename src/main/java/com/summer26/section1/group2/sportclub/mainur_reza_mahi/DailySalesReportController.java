package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DailySalesReportController
{
    @javafx.fxml.FXML
    private Label totalRevenueLabel;
    @javafx.fxml.FXML
    private Label totalOrderServedLabel;
    @javafx.fxml.FXML
    private Label totalItemSoldLabel;
    @javafx.fxml.FXML
    private DatePicker reportDateDP;

    private static final String ORDER_FILE = "orders.bin";
    private static final String ORDER_ITEM_FILE = "order_items.bin";

    private ArrayList<Order> orderList = new ArrayList<>();
    private ArrayList<OrderItem> orderItemList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void generateReportButtonOA(ActionEvent actionEvent) {


        if (reportDateDP.getValue() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please pick a date");
            a.showAndWait();

            return;
        }
        loadOrders();
        loadOrderItems();


        String selectedDate = reportDateDP.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));


        ArrayList<String> servedOrderIds = new ArrayList<>();
        double totalRevenue = 0.0;
        int totalOrdersServed = 0;

        for (Order o : orderList) {
            if (o.getOrderDate().equals(selectedDate) && o.getStatus().equals("served")) {
                servedOrderIds.add(o.getOrderId());
                totalRevenue = totalRevenue + o.getTotalPrice();
                totalOrdersServed++;
            }
        }

        int totalItemsSold = 0;

        for (OrderItem item : orderItemList) {
            if (servedOrderIds.contains(item.getOrderId())) {
                totalItemsSold = totalItemsSold + item.getQuantity();
            }
        }

        totalOrderServedLabel.setText(String.valueOf(totalOrdersServed));
        totalItemSoldLabel.setText(String.valueOf(totalItemsSold));
        totalRevenueLabel.setText(String.format("%.2f", totalRevenue));
    }

    private void loadOrders() {
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
    }

    private void loadOrderItems() {
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
    }
}