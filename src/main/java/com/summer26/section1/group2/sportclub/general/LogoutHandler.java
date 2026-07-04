package com.summer26.section1.group2.sportclub.general;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.Optional;

/*
  Centralized logout logic shared by every user dashboard

  Each dashboard controller's "Logout" button should call
  LogoutHandler.handleLogout(event) from its onAction method.
 */
public class LogoutHandler {

    private static final String LOGIN_FXML_PATH =
            "/com/summer26/section1/group2/sportclub/general/loginview.fxml";

    private LogoutHandler() {
        // Utility class; prevent instantiation.
    }

    public static void handleLogout(ActionEvent event) {
        // event-2: confirmation dialog
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Sign Out");
        confirm.setHeaderText(null);
        confirm.setContentText("Are you sure you want to sign out?");

        ButtonType confirmBtn = new ButtonType("Confirm");
        ButtonType cancelBtn = new ButtonType("Cancel", ButtonType.CANCEL.getButtonData());
        confirm.getButtonTypes().setAll(confirmBtn, cancelBtn);

        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == confirmBtn) {
            // User confirmed -> event-3
            completeSignOut(event);
        }
        // else: Cancel -> dialog closes, user stays on current dashboard
    }

    // event-3: redirect to login, show success message
    private static void completeSignOut(ActionEvent event) {
        // Redirect to Login Page
        try {
            SceneSwitcher.switchScene(event, LOGIN_FXML_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setContentText("Failed to return to login page.");
            error.showAndWait();
            return;
        }

        // Display success message
        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setTitle("Signed Out");
        success.setHeaderText(null);
        success.setContentText("You have successfully signed out.");
        success.showAndWait();
    }
}