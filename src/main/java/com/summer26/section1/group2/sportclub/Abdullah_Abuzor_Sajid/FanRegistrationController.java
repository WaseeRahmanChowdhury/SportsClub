package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public class FanRegistrationController {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^01\\d{9}$");

    @FXML
    private TextField fullNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private DatePicker dateOfBirthField;
    @FXML
    private TextField favoritePlayerField;
    @FXML
    private Label statusLabel;

    // event-3: Fan fills in the form and clicks 'Register'
    @FXML
    private void register() {
        statusLabel.setTextFill(Color.RED);

        if (fullNameField.getText().isBlank() || emailField.getText().isBlank()
                || phoneNumberField.getText().isBlank() || dateOfBirthField.getValue() == null) {
            statusLabel.setText("Please fill in all required fields.");
            return;
        }

        String email = emailField.getText().trim();
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            statusLabel.setText("Please enter a valid email address.");
            return;
        }

        // event-4: validate phone number is 11 digits starting with 01
        String phoneNumber = phoneNumberField.getText().trim();
        if (!PHONE_PATTERN.matcher(phoneNumber).matches()) {
            statusLabel.setText("Phone number must be 11 digits starting with 01.");
            return;
        }

        // event-5: validate fan must be at least 8 years old
        LocalDate dateOfBirth = dateOfBirthField.getValue();
        if (Period.between(dateOfBirth, LocalDate.now()).getYears() < 8) {
            statusLabel.setText("You must be at least 8 years old to register.");
            return;
        }

        // event-6: verify the email address is not already registered
        if (FanMember.isEmailRegistered(email)) {
            statusLabel.setText("This email address is already registered.");
            return;
        }

        FanMember fan = FanMember.register(
                fullNameField.getText().trim(),
                email,
                phoneNumber,
                dateOfBirth,
                favoritePlayerField.getText().trim()
        );

        statusLabel.setTextFill(Color.GREEN);
        statusLabel.setText("Registration successful! Your Membership ID is: " + fan.getFanMembershipId()
                + ". Temporary password: " + fan.getTemporaryPassword());
    }
}
