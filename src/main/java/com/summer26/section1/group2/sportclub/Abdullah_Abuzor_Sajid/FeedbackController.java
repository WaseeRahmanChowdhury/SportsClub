package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;

public class FeedbackController {

    @FXML
    private TextField fanMembershipIdField;
    @FXML
    private ComboBox<String> feedbackTypeCombo;
    @FXML
    private TextField subjectField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private ComboBox<Match> relatedMatchCombo;
    @FXML
    private Label statusLabel;

    @FXML
    private void initialize() {
        // event-4: feedback type options
        feedbackTypeCombo.getItems().addAll(
                "General Feedback", "Match Day Experience", "Stadium Facility Complaint", "Ticketing Issue", "Other");
        feedbackTypeCombo.setValue("General Feedback");

        relatedMatchCombo.getItems().setAll(Match.getMatches());
        relatedMatchCombo.setConverter(new StringConverter<>() {
            @Override
            public String toString(Match match) {
                return match == null ? "" : "vs " + match.getOpponentClubName() + " - " + match.getMatchDate();
            }

            @Override
            public Match fromString(String string) {
                return null;
            }
        });
    }

    // event-6/8: submit feedback
    @FXML
    private void submitFeedback() {
        statusLabel.setTextFill(Color.RED);

        if (fanMembershipIdField.getText().isBlank() || subjectField.getText().isBlank()
                || descriptionArea.getText().isBlank()) {
            statusLabel.setText("Please fill in all required fields.");
            return;
        }

        FanMember fan = FanMember.findByMembershipId(fanMembershipIdField.getText().trim());
        if (fan == null) {
            statusLabel.setText("Fan Membership ID not found. Please register first.");
            return;
        }

        // event-6: validate subject is not empty and within 100-character limit
        if (subjectField.getText().length() > 100) {
            statusLabel.setText("Subject must be 100 characters or fewer.");
            return;
        }

        // event-7: validate description is not empty and within 1000-character limit
        if (descriptionArea.getText().length() > 1000) {
            statusLabel.setText("Description must be 1000 characters or fewer.");
            return;
        }

        Match relatedMatch = relatedMatchCombo.getValue();
        String relatedMatchId = relatedMatch == null ? "" : relatedMatch.getMatchId();

        String referenceNumber = Feedback.submitFeedback(
                fan.getFanMembershipId(),
                feedbackTypeCombo.getValue(),
                subjectField.getText().trim(),
                descriptionArea.getText().trim(),
                relatedMatchId
        );

        statusLabel.setTextFill(Color.GREEN);
        statusLabel.setText("Thank you for your feedback. Reference Number: " + referenceNumber
                + ". We will review and respond within 3 working days.");
    }
}
