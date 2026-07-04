package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import com.summer26.section1.group2.sportclub.general.LogoutHandler;

public class AdminDashboardController {

    @FXML
    private Button signoutButton;

    @FXML
    private Button registerStaffButton;

    @FXML
    private Button scheduleMatchButton;

    @FXML
    private Button staffDirectoryButton;

    @FXML
    private Button reportsButton;

    @FXML
    private Button transferButton;

    @FXML
    private Button postAnnouncementButton;

    @FXML
    private Button sponsershipManagementButton;

    @FXML
    private Button ticketSaleSummaryButton;

    @FXML
    private void signOut(ActionEvent event) {
        LogoutHandler.handleLogout(event);
    }

    @FXML
    private void registerStaff(ActionEvent event) {
        // event-4: display the registration form window
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterStaffMember.fxml"));
            Parent root = loader.load();

            Stage formStage = new Stage();
            formStage.setTitle("Register New Staff Member");
            formStage.initModality(Modality.APPLICATION_MODAL);
            formStage.initOwner(registerStaffButton.getScene().getWindow());
            formStage.setScene(new Scene(root));
            formStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void scheduleMatch(ActionEvent event) {
        System.out.println("Schedule New Match button clicked");
    }

    @FXML
    private void staffDirectory(ActionEvent event) {
        System.out.println("Staff Directory button clicked");
    }

    @FXML
    private void reports(ActionEvent event) {
        System.out.println("Reports button clicked");
    }

    @FXML
    private void transfer(ActionEvent event) {
        System.out.println("Transfers button clicked");
    }

    @FXML
    private void postAnnouncement(ActionEvent event) {
        System.out.println("Post Announcement button clicked");
    }

    @FXML
    private void sponsershipManagement(ActionEvent event) {
        System.out.println("Sponsorship Management button clicked");
    }

    @FXML
    private void ticketSaleSummary(ActionEvent event) {
        System.out.println("Ticket Sale Summary button clicked");
    }
}