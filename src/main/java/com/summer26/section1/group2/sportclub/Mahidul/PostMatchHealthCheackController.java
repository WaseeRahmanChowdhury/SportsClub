package com.summer26.section1.group2.sportclub.Mahidul;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PostMatchHealthCheackController
{
    @javafx.fxml.FXML
    private TableColumn<Medical,String> idtable;
    @javafx.fxml.FXML
    private Label resulfx;
    @javafx.fxml.FXML
    private TableColumn <Medical,String>nametable;
    @javafx.fxml.FXML
    private ComboBox <String>matchcombo;
    @javafx.fxml.FXML
    private TableColumn <Medical,String>statustable;
    @javafx.fxml.FXML
    private TableView<Medical> table;
    @javafx.fxml.FXML
    private TableColumn positiontable;
    ArrayList<Medical> playerList = new ArrayList<>();
    @javafx.fxml.FXML
    private ComboBox <String>statuscombo;

    @javafx.fxml.FXML
    public void initialize() {
        matchcombo.getItems().addAll(
                "M001 - Completed",
                "M002 - Completed",
                "M003 - Scheduled"
        );

        statuscombo.getItems().addAll(
                "Fit",
                "Minor Issue",
                "Requires Medical Attention",
                "Injured"
        );

        idtable.setCellValueFactory(new PropertyValueFactory<>("playerId"));
        nametable.setCellValueFactory(new PropertyValueFactory<>("playerName"));

        // If you don't have a position field, use bodypart
        positiontable.setCellValueFactory(new PropertyValueFactory<>("bodypart"));

        statustable.setCellValueFactory(new PropertyValueFactory<>("availabilityStatus"));


    }

    @javafx.fxml.FXML
    public void claer(ActionEvent actionEvent) {
        table.getItems().clear();
        playerList.clear();
        matchcombo.setValue(null);
        statuscombo.setValue(null);

        resulfx.setText("");

    }

    @javafx.fxml.FXML
    public void assignStatus(ActionEvent actionEvent) {
        Medical selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            resulfx.setText("Please select a player.");
            return;
        }
        if (statuscombo.getValue() == null) {
            resulfx.setText("Please select a health status.");
            return;
        }
        selected.setAvailabilityStatus(statuscombo.getValue());
        table.refresh();
        resulfx.setText("Status assigned.");
    }

    @javafx.fxml.FXML
    public void save(ActionEvent actionEvent) {
        int total = 0;
        String attention = "";

        for (Medical m : table.getItems()) {

            if (m.getAvailabilityStatus() == null ||
                    m.getAvailabilityStatus().isEmpty()) {

                resulfx.setText("Health check status is required for all players.");
                return;
            }

            total++;

            if (!m.getAvailabilityStatus().equals("Fit")) {
                attention += m.getPlayerName() + " ";
            }
        }

        resulfx.setText(
                "Health checks recorded for "
                        + total
                        + " players.\nPlayers requiring attention: "
                        + attention
        );
    }

    @javafx.fxml.FXML
    public void back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin1_dashboard.fxml"));
        Stage stage = (Stage) table.getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }

    @javafx.fxml.FXML
    public void select(ActionEvent actionEvent) {
        if (matchcombo.getValue() == null) {
            resulfx.setText("Please select a match.");
            return;
        }

        if (matchcombo.getValue().contains("Scheduled")) {
            resulfx.setText("Health checks can only be recorded for completed matches.");
            return;
        }

        table.getItems().clear();
        playerList.clear();

        Medical m1 = new Medical(
                "P001",
                "Rahim",
                "Muscle",
                "Moderate",
                LocalDate.now(),
                7,
                "D001",
                ""
        );
        m1.setBodypart("Forward");

        Medical m2 = new Medical(
                "P002",
                "Karim",
                "Ligament",
                "Minor",
                LocalDate.now(),
                10,
                "D002",
                ""
        );
        m2.setBodypart("Midfielder");

        Medical m3 = new Medical(
                "P003",
                "Sakib",
                "Bone",
                "Severe",
                LocalDate.now(),
                30,
                "D003",
                ""
        );
        m3.setBodypart("Defender");

        playerList.add(m1);
        playerList.add(m2);
        playerList.add(m3);

        table.getItems().addAll(playerList);

        resulfx.setText("Players loaded successfully.");

    }
}