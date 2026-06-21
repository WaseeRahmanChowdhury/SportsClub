package com.summer26.section1.group2.sportclub.general;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

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
