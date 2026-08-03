package com.summer26.section1.group2.sportclub.Mahidul;

//import com.sun.javafx.tk.quantum.PaintRenderJob;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class AddEquipmentController
{
    @javafx.fxml.FXML
    private TextField supplierField;
    @javafx.fxml.FXML
    private ComboBox <String> categoryCombo;
    @javafx.fxml.FXML
    private TextField itemNameField;
    @javafx.fxml.FXML
    private TextField unitCostField;
    @javafx.fxml.FXML
    private TextField quantityField;
    @javafx.fxml.FXML
    private Label messageLabel;
    @javafx.fxml.FXML
    private DatePicker purchaseDatePicker;

    @javafx.fxml.FXML
    public void initialize() {
        categoryCombo.getItems().addAll(
                "Footballs",
                "Training Gear",
                "Kits",
                "Medical",
                "Cones & Poles",
                "Other"
        );
    }

    @javafx.fxml.FXML
    public void Submit(ActionEvent actionEvent) {
        try {

            String itemName = itemNameField.getText().trim();
            String category = categoryCombo.getValue();
            String supplier = supplierField.getText().trim();

            if (itemName.isEmpty()) {
                messageLabel.setText("Enter Item Name.");
                return;
            }

            if (category == null) {
                messageLabel.setText("Select Category.");
                return;
            }

            if (supplier.isEmpty()) {
                messageLabel.setText("Enter Supplier Name.");
                return;
            }

            int quantity = Integer.parseInt(quantityField.getText());

            if (quantity <= 0) {
                messageLabel.setText("Quantity Received must be a positive integer.");
                return;
            }

            double unitCost = Double.parseDouble(unitCostField.getText());

            if (unitCost < 0) {
                messageLabel.setText("Unit Cost must be a non-negative value.");
                return;
            }

            LocalDate purchaseDate = purchaseDatePicker.getValue();

            if (purchaseDate == null) {
                messageLabel.setText("Select Purchase Date.");
                return;
            }

            if (purchaseDate.isAfter(LocalDate.now())) {
                messageLabel.setText("Purchase Date must not be a future date.");
                return;
            }

            String itemID = "EQP-" + (1000 + (int) (Math.random() * 9000));

            messageLabel.setText(
                    "Equipment added to inventory.\n" +
                            "Item ID: " + itemID +
                            ". Total stock: " + quantity + " units."
            );

        }
        catch (NumberFormatException e) {

            messageLabel.setText("Quantity and Unit Cost must be numeric.");

        }
        catch (Exception e) {

            messageLabel.setText("Please fill all fields correctly.");

        }


    }

    @javafx.fxml.FXML
    public void Clear(ActionEvent actionEvent) {
        itemNameField.clear();
        quantityField.clear();
        unitCostField.clear();
        supplierField.clear();
        categoryCombo.setValue(null);
        purchaseDatePicker.setValue(null);
        messageLabel.setText("");

    }

    @javafx.fxml.FXML
    public void Next(ActionEvent actionEvent) {
        messageLabel.setText("Next button clicked.");
        try {

            Parent root = FXMLLoader.load(getClass().getResource(".fxml"));

            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource())
                    .getScene()
                    .getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        }
        catch (Exception e) {

            messageLabel.setText("Unable to open the next page.");

        }


    }

    @javafx.fxml.FXML
    public void Back(ActionEvent actionEvent) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("EquipmentManager.fxml"));

            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource())
                    .getScene()
                    .getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        }
        catch (Exception e) {

            messageLabel.setText("Unable to open Equipment Manager page.");

        }
    }

}