package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ScheduleAppointmentController
{
    @javafx.fxml.FXML
    private TextField contactNumberTF;
    @javafx.fxml.FXML
    private ComboBox<String> hostStaffCB;
    @javafx.fxml.FXML
    private DatePicker appointmentDateDP;
    @javafx.fxml.FXML
    private TextField visitorNameTF;
    @javafx.fxml.FXML
    private TextField purposeOfMeetingTA;
    @javafx.fxml.FXML
    private TextField appointmentTimeTF;

    private static final String FILE_NAME = "appointments.bin";
    private ArrayList<Appointment> appointmentList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        hostStaffCB.getItems().addAll(
                "Coach Salim",
                "Coach Abdullah",
                "Admin Rafi",
                "Admin Nadia",
                "Club President",
                "Team Manager",
                "Physiotherapist",
                "Media Officer");

        File file = new File(FILE_NAME);
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(FILE_NAME);
                ObjectInputStream ois = new ObjectInputStream(fis);
                appointmentList = (ArrayList<Appointment>) ois.readObject();
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @javafx.fxml.FXML
    public void bookAppointmentButtonOA(ActionEvent actionEvent) {

        int nextNumber = appointmentList.size() + 1;
        String apptId = String.format("APT-%04d", nextNumber);
        String visitorName = visitorNameTF.getText();
        String contactNo = contactNumberTF.getText();
        String hostStaff = hostStaffCB.getValue();
        String purpose = purposeOfMeetingTA.getText();
        String apptTime = appointmentTimeTF.getText();
        String apptDate = appointmentDateDP.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));


        Appointment appointment = new Appointment(apptId, visitorName, contactNo, hostStaff, apptDate, apptTime, purpose, "pending");

        appointmentList.add(appointment);

        if (visitorName.isEmpty()||contactNo.isEmpty()||hostStaff.isEmpty()||appointmentDateDP.getValue() == null||purpose.isEmpty() || apptTime.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in all fields.");
            alert.showAndWait();
            return;
        }

        try {
            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(appointmentList);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Appointment booked successfully. ID: " + apptId);
        alert.showAndWait();


        visitorNameTF.clear();
        contactNumberTF.clear();
        hostStaffCB.setValue(null);
        appointmentDateDP.setValue(null);
        purposeOfMeetingTA.clear();
        appointmentTimeTF.clear();
    }
    public void clearAllButtonOA(){
        visitorNameTF.clear();
        contactNumberTF.clear();
        hostStaffCB.setValue(null);
        appointmentDateDP.setValue(null);
        purposeOfMeetingTA.clear();
        appointmentTimeTF.clear();
    }
}