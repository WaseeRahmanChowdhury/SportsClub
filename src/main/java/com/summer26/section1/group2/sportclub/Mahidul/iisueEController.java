package com.summer26.section1.group2.sportclub.Mahidul;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class iisueEController
{
    @FXML
    private Label result;
    @FXML
    private TextField itemid;
    @FXML
    private Label id;
    @FXML
    private TextField ouantity;

    @FXML
    public void initialize() {
    }

    @FXML
    public void submit(ActionEvent actionEvent) {
        try {

            String recipientID = id.getText().trim();
            String itemID = itemid.getText().trim();

            if (recipientID.isEmpty()) {
                result.setText("Invalid Recipient ID.");
                return;
            }

            if (itemID.isEmpty()) {
                result.setText("Invalid Item ID.");
                return;
            }

            int quantity = Integer.parseInt(ouantity.getText());

            if (quantity <= 0) {
                result.setText("Quantity must be greater than 0.");
                return;
            }


            if (!recipientID.equalsIgnoreCase("P001")
                    && !recipientID.equalsIgnoreCase("S001")) {

                result.setText("Invalid Recipient ID.");
                return;
            }

            if (!itemID.equalsIgnoreCase("EQP-1001")) {

                result.setText("Invalid Item ID.");
                return;
            }

            int availableStock = 20;

            if (quantity > availableStock) {

                result.setText("Requested quantity exceeds available stock.");
                return;
            }

            int remainingStock = availableStock - quantity;

            String recipientName = "Rahim";
            String itemName = "Football";

            // Save issue record and update inventory file here

            result.setText(
                    "Issued " + quantity + " × " + itemName +
                            " to " + recipientName +
                            ". Remaining stock: " + remainingStock + " units."
            );

        }
        catch (NumberFormatException e) {
            result.setText("Quantity must be numeric.");
        }
        catch (Exception e) {
            result.setText("Error: " + e.getMessage());
        }

    }

    @FXML
    public void previous(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEquipment.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }
        catch (Exception e) {
            result.setText("Unable to load previous page.");
        }

    }

    @FXML
    public void Next(ActionEvent actionEvent) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("NextPage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (Exception e) {
            result.setText("Unable to load next page.");
        }

    }

    @FXML
    public void clear(ActionEvent actionEvent) {
        id.setText(null);
        itemid.clear();
        ouantity.clear();
        result.setText("");
    }
}