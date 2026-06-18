package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import static com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury.user_class.userlist;

public class logincontroller {
    @FXML private ComboBox<String> usercombobox;
    @FXML
    private Button passlogin;
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

    }
}
