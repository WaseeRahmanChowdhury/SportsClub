package com.summer26.section1.group2.sportclub.Mahidul;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class EquipmentManagerController
{
    @javafx.fxml.FXML
    private AnchorPane miain;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void AddEquipment(ActionEvent actionEvent) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("AddEquipment.fxml"));

            miain.getChildren().clear();
            miain.getChildren().add(root);

        }
        catch (Exception e) {

            e.printStackTrace();

        }
    }

    @javafx.fxml.FXML
    public void sign_out(ActionEvent actionEvent) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

            javafx.stage.Stage stage = (javafx.stage.Stage)
                    ((javafx.scene.Node) actionEvent.getSource())
                            .getScene()
                            .getWindow();

            stage.setScene(new javafx.scene.Scene(root));
            stage.setTitle("Login");
            stage.show();

        }
        catch (Exception e) {

            e.printStackTrace();

        }

    }

    @javafx.fxml.FXML
    public void IssueEquipment(ActionEvent actionEvent) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("iisueE.fxml"));

            miain.getChildren().clear();
            miain.getChildren().add(root);

        }
        catch (Exception e) {

            e.printStackTrace();

        }

    }

    @javafx.fxml.FXML
    public void PROCUREMENTREQUEST(ActionEvent actionEvent) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("PROCUREMENTREQUEST.fxml"));

            miain.getChildren().clear();
            miain.getChildren().add(root);

        }
        catch (Exception e) {

            e.printStackTrace();

        }
    }

    @javafx.fxml.FXML
    public void EquipmentMaintenance(ActionEvent actionEvent) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("EquipmentMaintenance.fxml"));

            miain.getChildren().clear();
            miain.getChildren().add(root);

        }
        catch (Exception e) {

            e.printStackTrace();

        }
    }

    @javafx.fxml.FXML
    public void InventoryReport(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void EquipmentReturn(ActionEvent actionEvent) {
    }
}