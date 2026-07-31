package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;


import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class MarkMyAttendanceController {

    private static final String FILE_NAME = "Attendance.bin";

    @FXML
    private ComboBox<String> sessionNameCombo;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Label statusLabel;

    private ArrayList<AttendanceRecord> attendanceData = new ArrayList<>();

    @FXML
    private void initialize() {
        // event-4: player picks today's session name before marking attendance
        ArrayList<String> sessionNames = new ArrayList<>();
        sessionNames.add("Morning Training");
        sessionNames.add("Afternoon Training");
        sessionNames.add("Evening Training");
        sessionNames.add("Match Preparation");

        sessionNameCombo.getItems().addAll(sessionNames);
        sessionNameCombo.getSelectionModel().selectFirst();
    }

    @FXML
    private void markAttendance() {

        String sessionName = sessionNameCombo.getValue();
        LocalDate sessionDate = datePicker.getValue();

        if (sessionName == null) {
            statusLabel.setText("Please select a session name.");
            return;
        }

        if (sessionDate == null) {
            statusLabel.setText("Please select the session date.");
            return;
        }

        // event-5: verify that the player has not already marked attendance for this session
        if (isAttendanceAlreadyMarked(sessionName, sessionDate)) {
            statusLabel.setText("You have already marked attendance for this session.");
            return;
        }

        // event-6: record attendance entry with session name and date to the attendance log file
        recordAttendance(sessionName, sessionDate);

        // event-7: display confirmation
        statusLabel.setText("Attendance marked successfully for session: " + sessionName);
    }

    /*
     * event-5: Verify that the player has not already marked attendance for this session.
     */
    private boolean isAttendanceAlreadyMarked(String sessionName, LocalDate sessionDate) {
        ArrayList<AttendanceRecord> currentRecords = readAttendanceFromFile();

        for (AttendanceRecord record : currentRecords) {
            if (record.getSessionName().equals(sessionName) && record.getSessionDate().equals(sessionDate)) {
                return true;
            }
        }

        return false;
    }

    /*
      event-6: Record attendance entry with session name, time, and date to the attendance log file.
     New record is appended to list already stored in the bin file, then the whole list is written back to the file.
     */
    private void recordAttendance(String sessionName, LocalDate sessionDate) {
        ArrayList<AttendanceRecord> currentRecords = readAttendanceFromFile();
        currentRecords.add(new AttendanceRecord(sessionName, sessionDate));

        try (FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject(currentRecords);

        } catch (IOException e) {
            e.printStackTrace();
        }

        attendanceData = currentRecords;
    }

    private ArrayList<AttendanceRecord> readAttendanceFromFile() {
        ArrayList<AttendanceRecord> records = new ArrayList<>();

        try (FileInputStream fileIn = new FileInputStream(FILE_NAME);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            records = (ArrayList<AttendanceRecord>) objectIn.readObject();

        } catch (EOFException e) {
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        return records;
    }

    /*
     * Simple representation of one attendance entry
     * (session name, session time, session date).
     */
    public static class AttendanceRecord implements Serializable {
        private String sessionName;
        private LocalDate sessionDate;

        public AttendanceRecord() {
        }

        public AttendanceRecord(String sessionName, LocalDate sessionDate) {
            this.sessionName = sessionName;
            this.sessionDate = sessionDate;
        }

        public String getSessionName() {
            return sessionName;
        }

        public void setSessionName(String sessionName) {
            this.sessionName = sessionName;
        }

        public LocalDate getSessionDate() {
            return sessionDate;
        }

        public void setSessionDate(LocalDate sessionDate) {
            this.sessionDate = sessionDate;
        }
    }
}
