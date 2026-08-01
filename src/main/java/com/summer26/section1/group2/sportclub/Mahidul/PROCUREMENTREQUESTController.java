package com.summer26.section1.group2.sportclub.Mahidul;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class PROCUREMENTREQUESTController
{
    @javafx.fxml.FXML
    private TextField itemNameField;
    @javafx.fxml.FXML
    private TextArea reasonArea;
    @javafx.fxml.FXML
    private TextField quantityField;
    @javafx.fxml.FXML
    private Label RESULT;
    @javafx.fxml.FXML
    private TextField costField;
    @javafx.fxml.FXML
    private ComboBox<String> urgencyCombo;

    @javafx.fxml.FXML
    public void initialize() {
        urgencyCombo.getItems().addAll(
                "Normal",
                "High",
                "Critical"
        );
    }

    @javafx.fxml.FXML
    public void previous(ActionEvent actionEvent) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("iisueE.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        }
        catch (Exception e) {

            RESULT.setText("Unable to load previous page.");

        }


    }

    @javafx.fxml.FXML
    public void submit(ActionEvent actionEvent) {
        try {

            String itemName = itemNameField.getText().trim();

            if (itemName.isEmpty()) {
                RESULT.setText("Enter Item Name.");
                return;
            }

            int quantity = Integer.parseInt(quantityField.getText());

            if (quantity <= 0) {
                RESULT.setText("Quantity must be a positive integer.");
                return;
            }

            double cost = Double.parseDouble(costField.getText());

            if (cost <= 0) {
                RESULT.setText("Estimated Unit Cost must be positive.");
                return;
            }

            String reason = reasonArea.getText().trim();

            if (reason.isEmpty()) {
                RESULT.setText("Enter Justification.");
                return;
            }

            if (reason.length() > 500) {
                RESULT.setText("Justification cannot exceed 500 characters.");
                return;
            }

            if (urgencyCombo.getValue() == null) {
                RESULT.setText("Select Urgency Level.");
                return;
            }

            String urgency = urgencyCombo.getValue();


            Equipment equipment = new Equipment(
                    itemName,
                    quantity,
                    cost,
                    reason,
                    urgency
            );

            FileOutputStream fos = new FileOutputStream("procurement.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(equipment);

            oos.close();
            fos.close();

            RESULT.setText("Procurement request submitted successfully.");

        }
        catch (NumberFormatException e) {

            RESULT.setText("Quantity and Cost must be numeric.");

        }
        catch (IOException e) {

            RESULT.setText("Error saving procurement request.");

        }
    }

    @javafx.fxml.FXML
    public void clear(ActionEvent actionEvent) {
        itemNameField.clear();
        quantityField.clear();
        costField.clear();
        reasonArea.clear();
        urgencyCombo.getSelectionModel().clearSelection();
        RESULT.setText("");

    }
}