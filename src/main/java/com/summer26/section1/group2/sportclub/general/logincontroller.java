package com.summer26.section1.group2.sportclub.general;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class logincontroller {
    @FXML private ComboBox<String> usercombobox;
    @FXML
    private PasswordField passlogin;
    @FXML
    private Button loginbutton;
    @FXML
    private Label logininfo;


    @FXML
    public void initialize() {
        usercombobox.getItems().addAll(
                "Player",
                "Coach",
                "Club Admin",
                "Fan",
                "Medical Staff",
                "Equipment Manager",
                "Match Official Liaison",
                "Finance Officer",
                "Receptionist",
                "Canteen Manager"
        );
    }
    @FXML
    public void Login(ActionEvent actionEvent) throws IOException {
        if (usercombobox.getValue() == null || passlogin.getText().isEmpty()) {
            logininfo.setText("Please fill in all the fields");
            return;
        }

        // For now, this routes every successful login to the Player dashboard
        // Later, switch to the matching dashboard FXML for that role.
        String selectedRole = usercombobox.getValue();

        if ("Player".equals(selectedRole)) {
            SceneSwitcher.switchScene(actionEvent,
                    "/com/summer26/section1/group2/sportclub/wasee_rahman_chowdhury/player_Dashboard.fxml");
        } else {
            //  add scene-switch calls for the other roles' dashboards once they exist
            logininfo.setText("Dashboard for '" + selectedRole + "' is not set up yet.");
        }
    }
}