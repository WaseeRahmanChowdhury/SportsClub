package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import com.summer26.section1.group2.sportclub.general.SceneSwitcher;

public class player_Dashboard {

    @FXML
    private AnchorPane mainpane;

    @FXML
    private void openMyMatchSchedule() {
        loadView("MyMatchSchedule.fxml");
    }

    @FXML
    private void openSubmitFitnessReport() {
        loadView("SubmitFitnessReport.fxml");
    }

    @FXML
    private void openMyPerformanceStats() {
        loadView("MyPerformanceStats.fxml");
    }

    @FXML
    private void openRequestLeave() {
        loadView("RequestLeave.fxml");
    }

    @FXML
    private void openMyContractAndSalary() {
        loadView("MyContractAndSalary.fxml");
    }

    @FXML
    private void openMarkMyAttendance() {
        loadView("MarkMyAttendance.fxml");
    }

    @FXML
    private void openEditMyProfile() {
        loadView("EditMyProfile.fxml");
    }

    @FXML
    private void openClubAnnouncements() {
        loadView("ClubAnnouncements.fxml");
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
}