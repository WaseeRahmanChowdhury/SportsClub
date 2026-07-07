package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class ReceptionistController {

    @FXML
    private AnchorPane contentArea;

    private void loadView(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/summer26/section1/group2/sportclub/mainur_reza_mahi/" + fxmlFile
            ));

            Parent view = loader.load();

            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);

            contentArea.getChildren().setAll(view);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        loadView("register-visitor-view.fxml");
    }

    @FXML
    public void registerVisitorButtonOA(ActionEvent actionEvent) {
        loadView("register-visitor-view.fxml");
    }

    @FXML
    public void visitorLogButtonOA(ActionEvent actionEvent) {
        loadView("visitor-log-view.fxml");
    }

    @FXML
    public void searchVisitorButtonOA(ActionEvent actionEvent) {
        loadView("search-visitor-view.fxml");
    }

    @FXML
    public void markExitButtonOA(ActionEvent actionEvent) {
        loadView("mark-exit-view.fxml");
    }

    @FXML
    public void scheduleAppointmentButtonOA(ActionEvent actionEvent) {
        loadView("schedule-appointment-view.fxml");
    }

    @FXML
    public void viewAppointmentButtonOA(ActionEvent actionEvent) {
        loadView("view-appointments-view.fxml");
    }

    @FXML
    public void manageAppointmentButtonOA(ActionEvent actionEvent) {
        loadView("manage-appointments-view.fxml");
    }

    @FXML
    public void dailyReportButtonOA(ActionEvent actionEvent) {
        loadView("daily-report-view.fxml");
    }

    @FXML
    public void logoutButtonOA(ActionEvent actionEvent) {
        loadView("login-view.fxml");
    }
}