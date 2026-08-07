package com.summer26.section1.group2.sportclub.Mahidul;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

public class KitController
{
    @javafx.fxml.FXML
    private TextField jerseyNumberTextField;
    @javafx.fxml.FXML
    private Label result;
    @javafx.fxml.FXML
    private ComboBox <String> kitTypeComboBox;
    @javafx.fxml.FXML
    private ComboBox <String> kitSizeComboBox;
    @javafx.fxml.FXML
    private ComboBox <String> playerComboBox;

    @javafx.fxml.FXML
    public void initialize() {
        playerComboBox.getItems().addAll(
                "P001",
                "P002",
                "P003",
                "P004",
                "P005"
        );

        kitTypeComboBox.getItems().addAll(
                "Match Kit",
                "Training Kit",
                "Goalkeeper Kit",
                "Warm-up Kit"
        );

        kitSizeComboBox.getItems().addAll(
                "XS",
                "S",
                "M",
                "L",
                "XL",
                "XXL"
        );
    }

    @javafx.fxml.FXML
    public void submit(ActionEvent actionEvent) {
        try {

            if (playerComboBox.getValue() == null ||
                    kitTypeComboBox.getValue() == null ||
                    kitSizeComboBox.getValue() == null ||
                    jerseyNumberTextField.getText().isEmpty()) {

                result.setText("Please fill in all fields.");
                return;
            }

            int jerseyNumber = Integer.parseInt(jerseyNumberTextField.getText());

            if (jerseyNumber <= 0) {
                result.setText("Invalid Jersey Number.");
                return;
            }

            Equipment equipment = new Equipment(
                    "",                                     // itemID
                    kitTypeComboBox.getValue(),             // itemName
                    "Kit",                                 // category
                    1,                                     // quantity
                    0.0,                                   // unitCost
                    "",                                    // supplier
                    LocalDate.now(),                       // purchaseDate
                    playerComboBox.getValue(),             // recipientID
                    0,                                     // quantityNeeded
                    0.0,                                   // estimatedUnitCost
                    "Kit Size : " + kitSizeComboBox.getValue(),
                    "Jersey No : " + jerseyNumber,
                    null,
                    "",
                    "",
                    0.0,
                    ""
            );

            FileOutputStream fos = new FileOutputStream("KitAssignment.bin", true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(equipment);

            oos.close();
            fos.close();

            result.setText("Kit assigned successfully.");

            playerComboBox.setValue(null);
            kitTypeComboBox.setValue(null);
            kitSizeComboBox.setValue(null);
            jerseyNumberTextField.clear();

        }
        catch (NumberFormatException e) {
            result.setText("Jersey Number must be numeric.");
        }
        catch (IOException e) {
            result.setText("Error saving data.");
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void previous(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EquipmentManagerDashboard.fxml"));

        Parent root = loader.load();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void Next(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("IssueEquipment.fxml"));

        Parent root = loader.load();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }
}