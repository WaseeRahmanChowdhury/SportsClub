package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class RegisterStaffMemberController {

    @FXML
    private TextField fullNameField;
    @FXML
    private TextField roleField;
    @FXML
    private TextField departmentField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private Label statusLabel;

    @FXML
    private void registerStaffMember() {
        statusLabel.setTextFill(Color.RED);

        if (fullNameField.getText().isBlank()
                || roleField.getText().isBlank()
                || departmentField.getText().isBlank()
                || phoneNumberField.getText().isBlank()) {
            statusLabel.setText("Please fill in all fields.");
            return;
        }

        String staffId = StaffMember.registerStaffMember(
                fullNameField.getText().trim(),
                roleField.getText().trim(),
                departmentField.getText().trim(),
                phoneNumberField.getText().trim()
        );

        ActivityLog.log(ActivityLog.TYPE_STAFF,
                "Registered staff member " + fullNameField.getText().trim() + " (" + staffId + ")", "Admin");

        statusLabel.setTextFill(Color.GREEN);
        statusLabel.setText("Staff member registered successfully. Member ID: " + staffId);
    }
}
