package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class FindMembershipIdController {

    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private Label statusLabel;

    @FXML
    private void findMembershipId() {
        statusLabel.setTextFill(Color.RED);

        String email = emailField.getText().trim();
        String phoneNumber = phoneNumberField.getText().trim();

        if (email.isBlank() || phoneNumber.isBlank()) {
            statusLabel.setText("Please enter both your email address and phone number.");
            return;
        }

        if (!ValidationUtils.isValidEmail(email)) {
            statusLabel.setText("Please enter a valid email address.");
            return;
        }

        FanMember fan = FanMember.findByEmailAndPhone(email, phoneNumber);
        if (fan == null) {
            statusLabel.setText("No fan membership found matching that email and phone number.");
            return;
        }

        statusLabel.setTextFill(Color.GREEN);
        statusLabel.setText("Found it! Your Membership ID is: " + fan.getFanMembershipId());
    }
}
