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
import java.util.Random;

public class RequestLeaveController {

    private static final String FILE_NAME = "LeaveRequest.bin";

    @FXML
    private ComboBox<String> leaveTypeCombo;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private Label statusLabel;

    private ArrayList<LeaveRequest> requestData = new ArrayList<>();

    @FXML
    private void initialize() {
        // event-4: leave type dropdown (Personal/Medical)
        ArrayList<String> leaveTypes = new ArrayList<>();
        leaveTypes.add("Personal");
        leaveTypes.add("Medical");

        leaveTypeCombo.getItems().addAll(leaveTypes);
        leaveTypeCombo.getSelectionModel().selectFirst();
    }

    @FXML
    private void submitLeaveRequest() {

        String leaveType = leaveTypeCombo.getValue();

        // event-5: validate start date - must be future date
        LocalDate startDate = startDatePicker.getValue();
        if (startDate == null) {
            statusLabel.setText("Please select a start date.");
            return;
        }

        if (!startDate.isAfter(LocalDate.now())) {
            statusLabel.setText("Start date must be a future date.");
            return;
        }

        // event-6: validate end date - must be equal to or after start date
        LocalDate endDate = endDatePicker.getValue();
        if (endDate == null) {
            statusLabel.setText("Please select an end date.");
            return;
        }

        if (endDate.isBefore(startDate)) {
            statusLabel.setText("End date must be equal to or after the start date.");
            return;
        }

        // event-7: save the leave request to the leave request file and notify the coach
        String requestId = saveLeaveRequest(leaveType, startDate, endDate);

        // event-8: display confirmation with request ID
        statusLabel.setText("Your leave request has been submitted. Request ID: " + requestId);
    }

    /*
      event-7: Save leave request to leave request file and notify coach.
      The new request is appended to the list already stored in the bin file,
      then the whole list is written back to the file.
      return a generated request ID in the format LRQ-XXXXXX
     */
    private String saveLeaveRequest(String leaveType, LocalDate startDate, LocalDate endDate) {
        int randomNumber = new Random().nextInt(999999);
        String requestId = String.format("LRQ-%06d", randomNumber);

        ArrayList<LeaveRequest> currentRequests = readRequestsFromFile();
        currentRequests.add(new LeaveRequest(requestId, leaveType, startDate, endDate));

        try (FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject(currentRequests);

        } catch (IOException e) {
            e.printStackTrace();
        }

        requestData = currentRequests;

        return requestId;
    }

    private ArrayList<LeaveRequest> readRequestsFromFile() {
        ArrayList<LeaveRequest> requests = new ArrayList<>();

        try (FileInputStream fileIn = new FileInputStream(FILE_NAME);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            requests = (ArrayList<LeaveRequest>) objectIn.readObject();

        } catch (EOFException e) {
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        return requests;
    }

    /*
      Simple representation of the leave request entry
      (request ID, leave type, start date, end date).
     */
    public static class LeaveRequest implements Serializable {
        private String requestId;
        private String leaveType;
        private LocalDate startDate;
        private LocalDate endDate;

        public LeaveRequest() {
        }

        public LeaveRequest(String requestId, String leaveType, LocalDate startDate, LocalDate endDate) {
            this.requestId = requestId;
            this.leaveType = leaveType;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public String getLeaveType() {
            return leaveType;
        }

        public void setLeaveType(String leaveType) {
            this.leaveType = leaveType;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public void setStartDate(LocalDate startDate) {
            this.startDate = startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public void setEndDate(LocalDate endDate) {
            this.endDate = endDate;
        }
    }
}
