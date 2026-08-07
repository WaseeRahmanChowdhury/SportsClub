package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
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
    private TableColumn<Order,String> statusTC;
    @javafx.fxml.FXML
    private TableColumn<Order,Double> totalPriceTC;
    @javafx.fxml.FXML
    private TableColumn<Order,String> orderIdTC;
    @javafx.fxml.FXML
    private TableView<Order> viewOrdersTC;
    @javafx.fxml.FXML
    private TableColumn<Order,String> customerTC;

    private static final String ORDER_FILE = "orders.bin";
    private ArrayList<Order> orderList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {

        // Step 1: tell each order column which field to show
        orderIdTC.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        customerTC.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerTypeTC.setCellValueFactory(new PropertyValueFactory<>("customerType"));
        totalPriceTC.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        orderTimeTC.setCellValueFactory(new PropertyValueFactory<>("orderTime"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    @javafx.fxml.FXML
    public void loadOrdersButtonOA(ActionEvent actionEvent) {

        if (orderDateDP.getValue() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please select a date");
            a.showAndWait();
            return;
        }

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

        String selectedDate = orderDateDP.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        ArrayList<Order> filteredList = new ArrayList<>();
        for (Order o : orderList) {
            if (o.getOrderDate().equals(selectedDate)) {
                filteredList.add(o);
            }
        }
        viewOrdersTC.getItems().clear();
        viewOrdersTC.getItems().addAll(filteredList);
    }
}