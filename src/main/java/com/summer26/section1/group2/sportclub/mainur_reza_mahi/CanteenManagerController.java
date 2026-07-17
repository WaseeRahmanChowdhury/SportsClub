package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import com.summer26.section1.group2.sportclub.general.LogoutHandler;
import com.summer26.section1.group2.sportclub.general.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CanteenManagerController
{
    @javafx.fxml.FXML
    private AnchorPane contentAreaCM;


    private void loadView(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/summer26/section1/group2/sportclub/mainur_reza_mahi/" + fxmlFile));

            Parent view = loader.load();

            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);

            contentAreaCM.getChildren().setAll(view);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @javafx.fxml.FXML
    public void initialize() {
        loadView("add-menu-item-view.fxml");
    }

    @javafx.fxml.FXML
    public void updateStockButtonOA(ActionEvent actionEvent) {
        loadView("update-stock-view.fxml");
    }

    @javafx.fxml.FXML
    public void viewMenuButtonOA(ActionEvent actionEvent) {
        loadView("view-menu-view.fxml");
    }

    @javafx.fxml.FXML
    public void updateMenuItemButtonOA(ActionEvent actionEvent) {
        loadView("update-menu-item-view.fxml");
    }

    @javafx.fxml.FXML
    public void markOrderServedButtonOA(ActionEvent actionEvent) {
        loadView("mark-order-served-view.fxml");
    }

    @javafx.fxml.FXML
    public void takeOrderButtonOA(ActionEvent actionEvent) {
        loadView("take-order-view.fxml");
    }

    @javafx.fxml.FXML
    public void addMenuItemButtonOA(ActionEvent actionEvent) {
        loadView("add-menu-item-view.fxml");
    }

    @javafx.fxml.FXML
    public void dailySalesReportButtonOA(ActionEvent actionEvent) {
        loadView("daily-sales-report-view.fxml");
    }

    @javafx.fxml.FXML
    public void viewOrdersButtonOA(ActionEvent actionEvent) {
        loadView("view-orders-view.fxml");
    }

    @javafx.fxml.FXML
    public void logoutCMButtonOA(ActionEvent actionEvent) {
        LogoutHandler.handleLogout(actionEvent);
    }

    @FXML
    public void logoutCMButtonOA(){
        try {
            SceneSwitcher.switchScene((Stage) contentAreaCM.getScene().getWindow(), "/com/summer26/section1/group2/sportclub/general/login.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}