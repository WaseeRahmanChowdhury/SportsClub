package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import javafx.fxml.FXML;

public class AdminDashboardController extends DashboardController {

    @FXML
    private void registerStaff() {
        loadView("RegisterStaffMember.fxml");
    }

    @FXML
    private void scheduleMatch() {
        loadView("ScheduleMatch.fxml");
    }

    @FXML
    private void staffDirectory() {
        loadView("StaffDirectoryList.fxml");
    }

    @FXML
    private void clubActivityLog() {
        loadView("ClubActivityLog.fxml");
    }

    @FXML
    private void transfer() {
        loadView("InitiateTransfer.fxml");
    }

    @FXML
    private void postAnnouncement() {
        loadView("PostAnnouncement.fxml");
    }

    @FXML
    private void sponsershipManagement() {
        loadView("SponsorshipManagement.fxml");
    }

    @FXML
    private void ticketBookingList() {
        loadView("TicketBookingList.fxml");
    }
}
