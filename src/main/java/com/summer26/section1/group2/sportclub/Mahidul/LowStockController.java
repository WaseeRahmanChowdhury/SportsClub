package com.summer26.section1.group2.sportclub.Mahidul;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class LowStockController
{
    @javafx.fxml.FXML
    private TextField BDT;
    @javafx.fxml.FXML
    private TextField name;
    @javafx.fxml.FXML
    private Label Quantity;
    @javafx.fxml.FXML
    private Label REASON;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void previous(ActionEvent actionEvent) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("EquipmentManagerDashboard.fxml"));

            Parent root = loader.load();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));

            stage.setTitle("Equipment Manager Dashboard");

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void submit(ActionEvent actionEvent) {
        try {

            String itemName = name.getText();

            double cost = Double.parseDouble(BDT.getText());

            String reason = "Low Stock";

            Equipment request = new Equipment(
                    itemName,
                    0,
                    cost,
                    reason,
                    "High"
            );

            FileOutputStream fos = new FileOutputStream("Procurement.bin", true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(request);

            oos.close();

            REASON.setText("Procurement Request Saved");

        }

        catch (Exception e) {

            REASON.setText("Invalid Input");

        }
    }

    @javafx.fxml.FXML
    public void clear(ActionEvent actionEvent) {
        name.clear();
        BDT.clear();

        Quantity.setText("");
        REASON.setText("");

    }
}