package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class PostAnnouncementController {

    @FXML
    private TextField titleField;
    @FXML
    private ComboBox<String> targetAudienceCombo;
    @FXML
    private ComboBox<String> priorityCombo;
    @FXML
    private TextArea contentArea;
    @FXML
    private Label statusLabel;

    @FXML
    private void initialize() {
        targetAudienceCombo.getItems().addAll("All", "Players", "Coaching Staff", "Admin Staff");
        targetAudienceCombo.setValue("All");

        priorityCombo.getItems().addAll("Normal", "Urgent");
        priorityCombo.setValue("Normal");
    }

    @FXML
    private void postAnnouncement() {
        statusLabel.setTextFill(Color.RED);

        // event-5: validate title is not empty
        if (titleField.getText().isBlank()) {
            statusLabel.setText("Title cannot be empty.");
            return;
        }

        String announcementId = Announcement.postAnnouncement(
                titleField.getText().trim(),
                contentArea.getText().trim(),
                targetAudienceCombo.getValue(),
                priorityCombo.getValue(),
                "Admin"
        );

        ActivityLog.log(ActivityLog.TYPE_ANNOUNCEMENT,
                "Posted announcement: " + titleField.getText().trim(), "Admin");

        statusLabel.setTextFill(Color.GREEN);
        statusLabel.setText("Announcement published. Announcement ID: " + announcementId);
    }
}
