package com.summer26.section1.group2.sportclub.Mahidul;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class EquipmentMaintenanceController
{
    @javafx.fxml.FXML
    private TableColumn idtable;
    @javafx.fxml.FXML
    private TableColumn typetable;
    @javafx.fxml.FXML
    private TableColumn Techniciantable;
    @javafx.fxml.FXML
    private TableColumn valuetable;
    @javafx.fxml.FXML
    private TableColumn datetable;
    @javafx.fxml.FXML
    private TableColumn costtable;
    @javafx.fxml.FXML
    private TableColumn Descriptiontable;
    @javafx.fxml.FXML
    private TableView table;
    @javafx.fxml.FXML
    private Label Result;

    @javafx.fxml.FXML
    public void initialize() {
        idtable.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        datetable.setCellValueFactory(new PropertyValueFactory<>("maintenanceDate"));
        typetable.setCellValueFactory(new PropertyValueFactory<>("maintenanceType"));
        Descriptiontable.setCellValueFactory(new PropertyValueFactory<>("description"));
        costtable.setCellValueFactory(new PropertyValueFactory<>("maintenanceCost"));
        Techniciantable.setCellValueFactory(new PropertyValueFactory<>("technicianName"));
        valuetable.setCellValueFactory(new PropertyValueFactory<>("itemName"));
    }

    @javafx.fxml.FXML
    public void previous(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEquipment.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }
        catch (Exception e) {
            Result.setText("Unable to load previous page.");
        }

    }

    @javafx.fxml.FXML
    public void submit(ActionEvent actionEvent) {
        loadData();


    }

    private void loadData() {
        table.getItems().clear();

        try {
            FileInputStream fis = new FileInputStream("Equipment.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                Equipment equipment = (Equipment) ois.readObject();

                if (null == equipment.getMaintenanceDate()) {
                } else {
                    table.getItems().add(equipment);
                }
            }

        } catch (EOFException e) {
            Result.setText("Maintenance records loaded successfully.");
        } catch (Exception e) {
            Result.setText("No maintenance record found.");
        }

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
            Result.setText("Unable to load next page.");
        }

    }

    @javafx.fxml.FXML
    public void clear(ActionEvent actionEvent) {
        table.getItems().clear();
        Result.setText("");
    }

    @javafx.fxml.FXML
    public void load(ActionEvent actionEvent) {
        loadData();

    }
}