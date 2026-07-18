package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class ClubShopController {

    @FXML
    private TextField fanMembershipIdField;
    @FXML
    private ComboBox<MerchandiseItem> itemCombo;
    @FXML
    private TextField quantityField;
    @FXML
    private Label summaryLabel;
    @FXML
    private TextField deliveryAddressField;
    @FXML
    private ComboBox<String> paymentMethodCombo;
    @FXML
    private Label statusLabel;

    @FXML
    private void initialize() {
        // event-4: display item catalog
        itemCombo.getItems().setAll(MerchandiseItem.getItems());

        paymentMethodCombo.getItems().addAll("bKash", "Nagad", "COD");
        paymentMethodCombo.setValue("bKash");

        itemCombo.valueProperty().addListener((obs, oldVal, newVal) -> updateSummary());
        quantityField.textProperty().addListener((obs, oldVal, newVal) -> updateSummary());
    }

    private void updateSummary() {
        MerchandiseItem item = itemCombo.getValue();
        if (item == null) {
            summaryLabel.setText("");
            return;
        }
        int quantity = parseQuantity();
        if (quantity > 0) {
            summaryLabel.setText(String.format("%s x %d = %.2f BDT (Stock: %d)",
                    item.getName() + " (" + item.getSize() + ")", quantity, item.getPrice() * quantity, item.getStockQuantity()));
        } else {
            summaryLabel.setText("Unit price: " + item.getPrice() + " BDT (Stock: " + item.getStockQuantity() + ")");
        }
    }

    private int parseQuantity() {
        try {
            return Integer.parseInt(quantityField.getText().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // event-11/12: process payment, deduct items, save order record
    @FXML
    private void placeOrder() {
        statusLabel.setTextFill(Color.RED);

        MerchandiseItem item = itemCombo.getValue();
        if (fanMembershipIdField.getText().isBlank() || item == null || quantityField.getText().isBlank()
                || deliveryAddressField.getText().isBlank() || paymentMethodCombo.getValue() == null) {
            statusLabel.setText("Please fill in all fields.");
            return;
        }

        FanMember fan = FanMember.findByMembershipId(fanMembershipIdField.getText().trim());
        if (fan == null) {
            statusLabel.setText("Fan Membership ID not found. Please register first.");
            return;
        }

        // event-6/8: verify item in stock, validate quantity is a positive integer and does not exceed stock
        int quantity = parseQuantity();
        if (quantity <= 0) {
            statusLabel.setText("Quantity must be a positive integer.");
            return;
        }
        if (quantity > item.getStockQuantity()) {
            statusLabel.setText("Only " + item.getStockQuantity() + " units in stock.");
            return;
        }

        // event-10: validate delivery address is not empty
        if (deliveryAddressField.getText().isBlank()) {
            statusLabel.setText("Delivery address cannot be empty.");
            return;
        }

        MerchOrder order = MerchOrder.placeOrder(
                fan.getFanMembershipId(), item, quantity,
                deliveryAddressField.getText().trim(), paymentMethodCombo.getValue());

        if (order == null) {
            statusLabel.setText("Order failed. Item may no longer be in stock.");
            return;
        }

        statusLabel.setTextFill(Color.GREEN);
        statusLabel.setText("Order placed! Order ID: " + order.getOrderId() + ". Estimated delivery: 3-5 working days.");
        updateSummary();
    }
}
