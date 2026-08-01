package com.summer26.section1.group2.sportclub.Mahidul;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SupplyController
{
    @javafx.fxml.FXML
    private Label namefx;
    @javafx.fxml.FXML
    private ComboBox<String> unitCombo;
    @javafx.fxml.FXML
    private ComboBox<String> urgencyCombo;
    @javafx.fxml.FXML
    private TextField attachmentField;
    @javafx.fxml.FXML
    private Label quantityField;
    @javafx.fxml.FXML
    private TextArea ReasonforRequest;
    @javafx.fxml.FXML
    private Label messageLabel;

    @javafx.fxml.FXML
    public void initialize() {
        unitCombo.getItems().addAll(
                "Pieces",
                "Boxes",
                "Bottles",
                "Packets",
                "Tablets"
        );

        urgencyCombo.getItems().addAll(
                "Normal",
                "High",
                "Critical"
        );
    }

    @javafx.fxml.FXML
    public void submit(ActionEvent actionEvent) {
        String item = namefx.getText();
        String quantity = quantityField.getText();

        if (item.isEmpty()) {
            messageLabel.setText("Item Name cannot be empty.");
            return;
        }

        if (quantity.isEmpty()) {
            messageLabel.setText("Quantity is required.");
            return;
        }

        int qty;

        try {
            qty = Integer.parseInt(quantity);

            if (qty <= 0) {
                messageLabel.setText("Quantity must be a positive integer.");
                return;
            }

        } catch (NumberFormatException e) {
            messageLabel.setText("Quantity must be a positive integer.");
            return;
        }

        messageLabel.setText("Medical supply request submitted.");
    }

    @javafx.fxml.FXML
    public void Next(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(".fxml"));
        Stage stage = (Stage) attachmentField.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @javafx.fxml.FXML
    public void Back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin1_dashboard.fxml"));
        Stage stage = (Stage) attachmentField.getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    public void clear(ActionEvent actionEvent) {
        attachmentField.clear();
        ReasonforRequest.clear();
        unitCombo.setValue(null);
        urgencyCombo.setValue(null);

        messageLabel.setText("");
    }
}