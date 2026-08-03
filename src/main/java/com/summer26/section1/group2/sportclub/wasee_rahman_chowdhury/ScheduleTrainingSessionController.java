package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class ScheduleTrainingSessionController {

    @FXML
    private DatePicker dateField;
    @FXML
    private TextField startTimeField;
    @FXML
    private TextField endTimeField;
    @FXML
    private TextField venueField;
    @FXML
    private ComboBox<String> sessionTypeCombo;
    @FXML
    private ComboBox<String> targetGroupCombo;
    @FXML
    private Label statusLabel;

    private final ArrayList<TrainingSession> trainingSessions = new ArrayList<>();

    private static final String FILE_NAME = "TrainingSession.bin";

    @FXML
    private void initialize() {
        // event-4: session type dropdown (Fitness/Technical/Tactical)
        ArrayList<String> sessionTypes = new ArrayList<>();
        sessionTypes.add("Fitness");
        sessionTypes.add("Technical");
        sessionTypes.add("Tactical");
        sessionTypeCombo.setItems(FXCollections.observableList(sessionTypes));

        // event-4: target group dropdown (Full Squad/Forwards/Midfielders/Defenders/Goalkeepers)
        ArrayList<String> targetGroups = new ArrayList<>();
        targetGroups.add("Full Squad");
        targetGroups.add("Forwards");
        targetGroups.add("Midfielders");
        targetGroups.add("Defenders");
        targetGroups.add("Goalkeepers");
        targetGroupCombo.setItems(FXCollections.observableList(targetGroups));

        loadTrainingSessionsFromFile();
    }

    @FXML
    private void scheduleTrainingSession() {
        statusLabel.setText("");

        // event-5: validate date - must be today or a future date
        LocalDate date = dateField.getValue();
        if (date == null) {
            statusLabel.setText("Please select a date.");
            return;
        }

        if (date.isBefore(LocalDate.now())) {
            statusLabel.setText("Date must be today or a future date.");
            return;
        }

        String startTime = startTimeField.getText();
        if (startTime == null || startTime.trim().isEmpty()) {
            statusLabel.setText("Start time must not be empty.");
            return;
        }

        String endTime = endTimeField.getText();
        if (endTime == null || endTime.trim().isEmpty()) {
            statusLabel.setText("End time must not be empty.");
            return;
        }

        LocalTime start;
        LocalTime end;
        try {
            start = LocalTime.parse(startTime.trim());
            end = LocalTime.parse(endTime.trim());
        } catch (Exception e) {
            statusLabel.setText("Start Time and End Time must be in HH:mm format, e.g. 16:00.");
            return;
        }

        if (!end.isAfter(start)) {
            statusLabel.setText("End Time must be after Start Time.");
            return;
        }

        String venue = venueField.getText();
        if (venue == null || venue.trim().isEmpty()) {
            statusLabel.setText("Venue must not be empty.");
            return;
        }

        String sessionType = sessionTypeCombo.getValue();
        if (sessionType == null) {
            statusLabel.setText("Please select a session type.");
            return;
        }

        String targetGroup = targetGroupCombo.getValue();
        if (targetGroup == null) {
            statusLabel.setText("Please select a target group.");
            return;
        }

        // event-6: verify that the selected venue is not already booked for another session
        // on the same date and overlapping time
        if (isVenueAlreadyBooked(venue, date, start, end)) {
            statusLabel.setText("The selected venue is already booked for an overlapping session on this date.");
            return;
        }

        // event-7: save training session to training schedule file and generate a session ID
        String sessionId = saveTrainingSession(date, startTime, endTime, venue, sessionType, targetGroup);

        // event-9: display confirmation
        statusLabel.setText("Training session scheduled. Session ID: " + sessionId + ". All targeted players have been notified.");

        dateField.setValue(null);
        startTimeField.clear();
        endTimeField.clear();
        venueField.clear();
        sessionTypeCombo.setValue(null);
        targetGroupCombo.setValue(null);
    }

    /*
     * event-6: Verify that the selected venue is not already booked for another session  on the same date and overlapping time.
     */
    private boolean isVenueAlreadyBooked(String venue, LocalDate date, LocalTime start, LocalTime end) {
        for (TrainingSession session : trainingSessions) {
            if (session.getVenue().equalsIgnoreCase(venue) && session.getDate().equals(date)) {
                LocalTime existingStart = LocalTime.parse(session.getStartTime());
                LocalTime existingEnd = LocalTime.parse(session.getEndTime());

                if (start.isBefore(existingEnd) && existingStart.isBefore(end)) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     event-7: Save the training session to the training schedule file and generate a session ID in the format TRN-XXXX.
     */
    private String saveTrainingSession(LocalDate date, String startTime, String endTime,
                                        String venue, String sessionType, String targetGroup) {
        int randomNumber = new Random().nextInt(9999);
        String sessionId = String.format("TRN-%04d", randomNumber);

        TrainingSession session = new TrainingSession(sessionId, date, startTime, endTime,
                venue, sessionType, targetGroup);

        trainingSessions.add(session);
        saveTrainingSessionsToFile();

        return sessionId;
    }

    private void saveTrainingSessionsToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(trainingSessions);
        } catch (IOException e) {
            statusLabel.setText("ERROR: Could not save the training session to file.");
        }
    }

    @SuppressWarnings("unchecked")
    private void loadTrainingSessionsFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            ArrayList<TrainingSession> loadedSessions = (ArrayList<TrainingSession>) in.readObject();
            trainingSessions.addAll(loadedSessions);
        } catch (IOException | ClassNotFoundException e) {
            statusLabel.setText("ERROR: Could not load training sessions from file.");
        }
    }

    /*
    Simple representation of one scheduled training session.
     */
    public static class TrainingSession implements Serializable {
        private String sessionId;
        private LocalDate date;
        private String startTime;
        private String endTime;
        private String venue;
        private String sessionType;
        private String targetGroup;

        public TrainingSession() {
        }

        public TrainingSession(String sessionId, LocalDate date, String startTime, String endTime,
                                String venue, String sessionType, String targetGroup) {
            this.sessionId = sessionId;
            this.date = date;
            this.startTime = startTime;
            this.endTime = endTime;
            this.venue = venue;
            this.sessionType = sessionType;
            this.targetGroup = targetGroup;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getVenue() {
            return venue;
        }

        public void setVenue(String venue) {
            this.venue = venue;
        }

        public String getSessionType() {
            return sessionType;
        }

        public void setSessionType(String sessionType) {
            this.sessionType = sessionType;
        }

        public String getTargetGroup() {
            return targetGroup;
        }

        public void setTargetGroup(String targetGroup) {
            this.targetGroup = targetGroup;
        }
    }
}
