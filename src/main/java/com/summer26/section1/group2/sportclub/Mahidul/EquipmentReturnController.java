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

import java.io.*;
import java.util.ArrayList;

public class EquipmentReturnController
{
    @javafx.fxml.FXML
    private Label result;
    @javafx.fxml.FXML
    private TextField itemid;
    @javafx.fxml.FXML
    private TextField id;
    @javafx.fxml.FXML
    private TextField QuantityBeingReturned;
    @javafx.fxml.FXML
    private ComboBox <String>ConditionUponReturn;

    @javafx.fxml.FXML
    public void initialize() {
        ConditionUponReturn.getItems().addAll(
                "Good",
                "Damaged",
                "Lost"
        );
    }

    @javafx.fxml.FXML
    public void previous(ActionEvent actionEvent) { try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InventoryReport.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }
    catch (Exception e) {
        result.setText("Unable to load previous page.");
    }


    }

    @javafx.fxml.FXML
    public void submit(ActionEvent actionEvent) {
        String recipientID = id.getText();
        String itemID = itemid.getText();
        int returnQty = Integer.parseInt(QuantityBeingReturned.getText());
        String condition = ConditionUponReturn.getValue();

        EquipmentIssuance issuance = null;

        try {

            ObjectInputStream ois =
                    new ObjectInputStream(new FileInputStream("EquipmentIssuance.bin"));

            while (true) {

                EquipmentIssuance temp =
                        (EquipmentIssuance) ois.readObject();

                if (temp.getRecipientID().equals(recipientID)
                        && temp.getItemID().equals(itemID)) {

                    issuance = temp;
                    break;
                }
            }

        } catch (EOFException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (issuance == null) {

            result.setText("No active equipment issuance found.");
            return;
        }

        if (returnQty > issuance.getIssuedQuantity()) {

            result.setText("Returned quantity exceeds the issued quantity.");
            return;
        }

        if (condition.equals("Damaged") || condition.equals("Lost")) {

            try {

                ObjectOutputStream oos =
                        new ObjectOutputStream(
                                new FileOutputStream("EquipmentDamage.bin", true));

                EquipmentDamage damage = new EquipmentDamage(
                        recipientID,
                        itemID,
                        returnQty,
                        condition
                );

                oos.writeObject(damage);
                oos.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            result.setText("Equipment return processed successfully.");
            return;
        }

        ArrayList<Equipment> list = new ArrayList<>();

        try {

            ObjectInputStream ois =
                    new ObjectInputStream(new FileInputStream("Equipment.bin"));

            while (true) {

                Equipment e = (Equipment) ois.readObject();

                if (e.getItemID().equals(itemID)) {

                    e.setQuantity(e.getQuantity() + returnQty);
                }

                list.add(e);
            }

        } catch (EOFException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            ObjectOutputStream oos =
                    new ObjectOutputStream(new FileOutputStream("Equipment.bin"));

            for (Equipment e : list) {

                oos.writeObject(e);
            }

            oos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        result.setText("Equipment return processed successfully.");

    }

    @javafx.fxml.FXML
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

    @javafx.fxml.FXML
    public void clear(ActionEvent actionEvent) {
        id.clear();
        itemid.clear();
        QuantityBeingReturned.clear();
        ConditionUponReturn.getSelectionModel().clearSelection();
        result.setText("");
    }
}