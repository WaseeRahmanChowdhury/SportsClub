package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class MarkMyAttendanceController {

    @FXML
    private Label sessionNameLabel;
    @FXML
    private Label sessionTimeLabel;
    @FXML
    private Label sessionLocationLabel;
    @FXML
    private Label statusLabel;

    /* event-2/event-3: holds today's training session info, fetched on load. */
    private String currentSessionId;
    private String currentSessionName;

    @FXML
    private void initialize() {
        fetchTodaysTrainingSession();
    }

    /*
     * event-3: System automatically fetches today's training session
     * scheduled for this player from the training schedule file.
     *  Replace with real data source lookup.
     */
    private void fetchTodaysTrainingSession() {
        sessionNameLabel.setText("");
        sessionTimeLabel.setText("");
        sessionLocationLabel.setText("");
        currentSessionId = null;
        currentSessionName = null;
        // fill session fields and currentSessionId/currentSessionName
    }

    @FXML
    private void markAttendance() {
        statusLabel.setTextFill(Color.RED);

        if (currentSessionId == null) {
            statusLabel.setText("No training session is scheduled for today.");
            return;
        }

        // event-5: verify that the player has not already marked attendance for this session
        if (isAttendanceAlreadyMarked(currentSessionId)) {
            statusLabel.setText("You have already marked attendance for this session.");
            return;
        }

        // event-6: record attendance entry with player ID, session ID, and timestamp to the attendance log file
        recordAttendance(currentSessionId);

        // event-7: display confirmation
        statusLabel.setTextFill(Color.GREEN);
        statusLabel.setText("Attendance marked successfully for session: " + currentSessionName);
    }

    /*
     * event-5: Verify that the player has not already marked attendance for this session.
     * Replace with a real check against the attendance log file.
     */
    private boolean isAttendanceAlreadyMarked(String sessionId) {
        return false;
    }

    /*
     * event-6: Record attendance entry with player ID, session ID, and timestamp
     * to the attendance log file.
     *  Replace with real persistence logic.
     */
    private void recordAttendance(String sessionId) {
        // persist attendance entry
    }
}
