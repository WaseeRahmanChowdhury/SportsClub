package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
        System.out.println("Sign Out button clicked");
    }

    @FXML
    private void registerStaff(ActionEvent event) {
        System.out.println("Register New Staff Member button clicked");
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