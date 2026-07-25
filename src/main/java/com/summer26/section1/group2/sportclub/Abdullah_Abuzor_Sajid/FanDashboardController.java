package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import javafx.fxml.FXML;

public class FanDashboardController extends DashboardController {

    @FXML
    private void findMembershipId() {
        loadView("FindMembershipId.fxml");
    }

    @FXML
    private void buyTicket() {
        loadView("BuyTicket.fxml");
    }

    @FXML
    private void matchSchedule() {
        loadView("FanMatchSchedule.fxml");
    }

    @FXML
    private void ourPlayers() {
        loadView("OurPlayers.fxml");
    }

    @FXML
    private void feedback() {
        loadView("SubmitFeedback.fxml");
    }

    @FXML
    private void leagueTable() {
        loadView("LeagueTable.fxml");
    }

    @FXML
    private void clubShop() {
        loadView("ClubShop.fxml");
    }

    @FXML
    private void matchResults() {
        loadView("MatchResults.fxml");
    }
}
