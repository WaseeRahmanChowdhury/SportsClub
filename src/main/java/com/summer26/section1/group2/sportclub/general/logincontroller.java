package com.summer26.section1.group2.sportclub.general;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
    private Button fanRegisterButton;


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

        String selectedRole = usercombobox.getValue();

        switch (selectedRole) {
            case "Player":
                SceneSwitcher.switchScene(actionEvent,
                        "/com/summer26/section1/group2/sportclub/wasee_rahman_chowdhury/player_Dashboard.fxml");
                break;
            case "Coach":
                SceneSwitcher.switchScene(actionEvent,
                        "/com/summer26/section1/group2/sportclub/wasee_rahman_chowdhury/coach_Dashboard.fxml");
                break;

            case "Club Admin":
                SceneSwitcher.switchScene(actionEvent,
                        "/com/summer26/section1/group2/sportclub/Abdullah_Abuzor_Sajid/admin-dashboard.fxml");
                break;

            case "Medical Staff":
                SceneSwitcher.switchScene(actionEvent,
                        "/com/summer26/section1/group2/sportclub/Mahidul/Medical.fxml");
                break;


            case "Fan":
                SceneSwitcher.switchScene(actionEvent,
                        "/com/summer26/section1/group2/sportclub/Abdullah_Abuzor_Sajid/fan-dashboard.fxml");
                break;

            // add these once other dashboard FXML files exist
            case "Equipment Manager":
            case "Match Official Liaison":
            case "Finance Officer":
            case "Receptionist":
                SceneSwitcher.switchScene(actionEvent,
                        "/com/summer26/section1/group2/sportclub/mainur_reza_mahi/receptionist-dashboard.fxml");
                break;
            case "Canteen Manager":
                SceneSwitcher.switchScene(actionEvent,
                        "/com/summer26/section1/group2/sportclub/mainur_reza_mahi/canteen-manager-dashboard.fxml");
                break;

            default:
                logininfo.setText("Unknown role selected.");
                break;
        }
    }

    // event-1: Fan opens the fan membership registration page (no login required)
    @FXML
    public void openFanRegistration(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/summer26/section1/group2/sportclub/Abdullah_Abuzor_Sajid/FanRegistration.fxml"));
            Parent root = loader.load();

            Stage formStage = new Stage();
            formStage.setTitle("Fan Membership Registration");
            formStage.initModality(Modality.APPLICATION_MODAL);
            formStage.initOwner(fanRegisterButton.getScene().getWindow());
            formStage.setScene(new Scene(root));
            formStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}