package com.summer26.section1.group2.sportclub.Mahidul;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class InventoryReportController
{
    @javafx.fxml.FXML
    private TableColumn <Equipment, Integer> quantity;
    @javafx.fxml.FXML
    private TableColumn<Equipment, Integer>  Min;
    @javafx.fxml.FXML
    private TableColumn <Equipment, Double> cost;
    @javafx.fxml.FXML
    private TableColumn<Equipment,String> name;
    @javafx.fxml.FXML
    private TableColumn<Equipment,String> id;
    @javafx.fxml.FXML
    private TableColumn <Equipment,String>category;
    @javafx.fxml.FXML
    private TableView table;
    @javafx.fxml.FXML
    private Label result;
    @javafx.fxml.FXML
    private ComboBox <String>categoryCombo;
    private final ObservableList<Equipment> equipmentList =
            FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {
        name.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        id.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        Min.setCellValueFactory(new PropertyValueFactory<>("minimumThreshold"));
        cost.setCellValueFactory(new PropertyValueFactory<>("unitCost"));
        categoryCombo.getItems().addAll(
                "All",
                "Footballs",
                "Training Gear",
                "Kits",
                "Medical",
                "Other"
        );

        categoryCombo.getSelectionModel().selectFirst();

    }

    @javafx.fxml.FXML
    public void previous(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EquipmentMaintenance.fxml"));
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
        equipmentList.clear();

        String selectedCategory = categoryCombo.getValue();

        try {

            ObjectInputStream ois =
                    new ObjectInputStream(new FileInputStream("Equipment.bin"));

            while (true) {

                Equipment e = (Equipment) ois.readObject();

                if (selectedCategory.equals("All")
                        || e.getCategory().equalsIgnoreCase(selectedCategory)) {

                    equipmentList.add(e);
                }
            }

        } catch (EOFException e) {

            // End of file

        } catch (IOException | ClassNotFoundException e) {

            e.printStackTrace();
        }

        table.setItems(equipmentList);

        result.setText(equipmentList.size() + " record(s) found.");
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
        table.getItems().clear();
        result.setText("");
        categoryCombo.getSelectionModel().selectFirst();
    }
}