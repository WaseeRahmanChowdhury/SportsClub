package com.summer26.section1.group2.sportclub.Mahidul;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class admin1_dashboardController
{
    @javafx.fxml.FXML
    private AnchorPane mainpane;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void MedicalStaffRecordAnInjuryAssessment(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Medical.fxml"));
            Node node = fxmlLoader.load();
            // Optional: remove existing content
            mainpane.getChildren().setAll(node);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void signoutAction(ActionEvent actionEvent) {
    }
}