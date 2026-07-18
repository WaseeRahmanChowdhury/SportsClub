package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import com.summer26.section1.group2.sportclub.general.LogoutHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class FanDashboardController {

    @FXML
    private AnchorPane contentArea;

    @FXML
    private void signOut(ActionEvent event) {
        LogoutHandler.handleLogout(event);
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

    /*
     * Loads the given FXML file (located in this same package/resource folder)
     * into contentArea, replacing whatever is currently displayed.
     */
    private void loadView(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Node view = loader.load();

            contentArea.getChildren().setAll(view);

            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
