package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import com.summer26.section1.group2.sportclub.general.SceneSwitcher;
import com.summer26.section1.group2.sportclub.general.LogoutHandler;

public class coach_Dashboard {

    @FXML
    private AnchorPane mainpane;

    @FXML
    private void openManageRoster() {
        loadView("ManageRoster.fxml");
    }

    @FXML
    private void openCreateLineup() {
        loadView("CreateLineup.fxml");
    }

    @FXML
    private void openScheduleTrainingSession() {
        loadView("ScheduleTrainingSession.fxml");
    }

    @FXML
    private void openPlayerReports() {
        loadView("PlayerReports.fxml");
    }

    @FXML
    private void openSubmitMatchAnalysis() {
        loadView("SubmitMatchAnalysis.fxml");
    }

    @FXML
    private void openRequestEquipment() {
        loadView("RequestEquipment.fxml");
    }

    @FXML
    private void openFixtureList() {
        loadView("FixtureList.fxml");
    }

    @FXML
    private void openPlayerAvailabilityManager() {
        loadView("PlayerAvailabilityManager.fxml");
    }

    /*
     * Loads the given FXML file (located in this same package/resource folder)
     * into mainpane, replacing whatever is currently displayed.
     */
    private void loadView(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Node view = loader.load();

            mainpane.getChildren().setAll(view);

            // Make the loaded view fill mainpane.
            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
        } catch (IOException e) {
            e.printStackTrace();
            // displays a user-facing error message (e.g. via an alert or status label)
        }
    }

    @FXML
    private void logout() {
        try {
            // NOTE: update this path/filename if your login FXML lives somewhere
            // other than /com/summer26/section1/group2/sportclub/general/login.fxml
            SceneSwitcher.switchScene(
                    (Stage) mainpane.getScene().getWindow(),
                    "/com/summer26/section1/group2/sportclub/general/login.fxml"
            );
        } catch (IOException e) {
            e.printStackTrace();
            // displays a user-facing error message (e.g. via an alert or status label)
        }
    }

    @FXML
    private void logout(ActionEvent event) {
        LogoutHandler.handleLogout(event);
    }
}
