package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DailySalesReportController
{
    @javafx.fxml.FXML
    private Label totalRevenueLabel;
    @javafx.fxml.FXML
    private TableView<CategorySummary> categoryBreakdownTC;
    @javafx.fxml.FXML
    private TableColumn<CategorySummary,Integer> itemSoldTC;
    @javafx.fxml.FXML
    private TableColumn<CategorySummary,String> revenueTC;
    @javafx.fxml.FXML
    private Label totalOrderServedLabel;
    @javafx.fxml.FXML
    private Label totalItemSoldLabel;
    @javafx.fxml.FXML
    private Label bestSellingItemLabel;
    @javafx.fxml.FXML
    private TableColumn<CategorySummary,String> categoryTC;
    @javafx.fxml.FXML
    private DatePicker reportDateDP;

    private static final String ORDER_FILE = "orders.bin";
    private static final String ORDER_ITEM_FILE = "order_items.bin";
    private static final String MENU_FILE = "menu_items.bin";

    private ArrayList<Order> orderList = new ArrayList<>();
    private ArrayList<OrderItem> orderItemList = new ArrayList<>();
    private ArrayList<MenuItem> menuItemList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        categoryTC.setCellValueFactory(new PropertyValueFactory<>("category"));
        itemSoldTC.setCellValueFactory(new PropertyValueFactory<>("itemSold"));
        revenueTC.setCellValueFactory(new PropertyValueFactory<>("revenue"));
    }

    @javafx.fxml.FXML
    public void generateReportButtonOA(ActionEvent actionEvent) {

        // Step 1: check a date was picked
        if (reportDateDP.getValue() == null) {
            return;
        }

        // Step 2: load orders, order items, and menu items from file
        loadOrders();
        loadOrderItems();
        loadMenuItems();

        // Step 3: convert picked date to text, same format used when saving
        String selectedDate = reportDateDP.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        // Step 4: find all SERVED order IDs for that date
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

        // Step 5: simple counters for each fixed category
        int breakfastQty = 0, launchQty = 0, snackQty = 0, beverageQty = 0;
        double breakfastRevenue = 0.0, launchRevenue = 0.0, snackRevenue = 0.0, beverageRevenue = 0.0;
        int totalItemsSold = 0;

        // Step 6: to find the best-selling item, use two simple lists (name + quantity)
        ArrayList<String> itemNames = new ArrayList<>();
        ArrayList<Integer> itemQuantities = new ArrayList<>();

        // Step 7: go through every order item and check if it belongs to a served order today
        for (OrderItem item : orderItemList) {

            if (servedOrderIds.contains(item.getOrderId())) {

                totalItemsSold = totalItemsSold + item.getQuantity();

                // find this item's category from the menu list
                String category = "Other";
                for (MenuItem m : menuItemList) {
                    if (m.getItemName().equals(item.getItemName())) {
                        category = m.getCategory();
                    }
                }

                if (category.equals("Breakfast")) {
                    breakfastQty = breakfastQty + item.getQuantity();
                    breakfastRevenue = breakfastRevenue + item.getSubtotal();
                }
                if (category.equals("Launch")) {
                    launchQty = launchQty + item.getQuantity();
                    launchRevenue = launchRevenue + item.getSubtotal();
                }
                if (category.equals("Snack")) {
                    snackQty = snackQty + item.getQuantity();
                    snackRevenue = snackRevenue + item.getSubtotal();
                }
                if (category.equals("Beverage")) {
                    beverageQty = beverageQty + item.getQuantity();
                    beverageRevenue = beverageRevenue + item.getSubtotal();
                }

                // Step 8: track quantity sold per item name manually
                boolean found = false;
                for (int i = 0; i < itemNames.size(); i++) {
                    if (itemNames.get(i).equals(item.getItemName())) {
                        itemQuantities.set(i, itemQuantities.get(i) + item.getQuantity());
                        found = true;
                    }
                }
                if (!found) {
                    itemNames.add(item.getItemName());
                    itemQuantities.add(item.getQuantity());
                }
            }
        }

        // Step 9: update the top summary labels
        totalOrderServedLabel.setText(String.valueOf(totalOrdersServed));
        totalItemSoldLabel.setText(String.valueOf(totalItemsSold));
        totalRevenueLabel.setText(String.format("%.2f", totalRevenue));

        // Step 10: find the best-selling item by comparing quantities
        String bestItem = "N/A";
        int highestQty = 0;

        for (int i = 0; i < itemNames.size(); i++) {
            if (itemQuantities.get(i) > highestQty) {
                highestQty = itemQuantities.get(i);
                bestItem = itemNames.get(i);
            }
        }
        bestSellingItemLabel.setText(bestItem);

        // Step 11: build the category breakdown table
        ArrayList<CategorySummary> summaryList = new ArrayList<>();
        summaryList.add(new CategorySummary("Breakfast", breakfastQty, String.format("%.2f", breakfastRevenue)));
        summaryList.add(new CategorySummary("Launch", launchQty, String.format("%.2f", launchRevenue)));
        summaryList.add(new CategorySummary("Snack", snackQty, String.format("%.2f", snackRevenue)));
        summaryList.add(new CategorySummary("Beverage", beverageQty, String.format("%.2f", beverageRevenue)));

        categoryBreakdownTC.getItems().clear();
        categoryBreakdownTC.getItems().addAll(summaryList);
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

    private void loadMenuItems() {
        menuItemList.clear();
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
}