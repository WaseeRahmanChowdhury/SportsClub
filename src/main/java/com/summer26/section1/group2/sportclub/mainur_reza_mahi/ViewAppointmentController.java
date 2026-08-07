package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ViewAppointmentController
{
    @javafx.fxml.FXML
    private TableColumn<Appointment,String> timeTC;
    @javafx.fxml.FXML
    private TableColumn<Appointment,String> hostStaffTC;
    @javafx.fxml.FXML
    private TableColumn<Appointment,String> visitorNameTC;
    @javafx.fxml.FXML
    private TableView<Appointment> viewAppointmentsTC;
    @javafx.fxml.FXML
    private Label totalAppointmentLabel;
    @javafx.fxml.FXML
    private TableColumn<Appointment,String> statusTC;
    @javafx.fxml.FXML
    private Label pendingLabel;
    @javafx.fxml.FXML
    private Label completedLabel;
    @javafx.fxml.FXML
    private TableColumn<Appointment,String> appointmentIdTC;
    @javafx.fxml.FXML
    private DatePicker appointmentFilterDP;
    @javafx.fxml.FXML
    private TableColumn<Appointment,String> purposeTC;
    @javafx.fxml.FXML
    private Label cancelledLabel;

    private static final String FILE_NAME = "appointments.bin";
    private ArrayList<Appointment> appointmentList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        appointmentIdTC.setCellValueFactory(new PropertyValueFactory<>("apptId"));
        visitorNameTC.setCellValueFactory(new PropertyValueFactory<>("visitorName"));
        hostStaffTC.setCellValueFactory(new PropertyValueFactory<>("hostStaff"));
        timeTC.setCellValueFactory(new PropertyValueFactory<>("apptTime"));
        purposeTC.setCellValueFactory(new PropertyValueFactory<>("purpose"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    @javafx.fxml.FXML
    public void loadAppointmentsButtonOA(ActionEvent actionEvent) {
        if (appointmentFilterDP.getValue() == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please select a date");
            return;
        }

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


        String selectedDate = appointmentFilterDP.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));


        ArrayList<Appointment> filteredList = new ArrayList<>();
        int pendingCount = 0;
        int completedCount = 0;
        int cancelledCount = 0;

        for (Appointment a : appointmentList) {
            if (a.getApptDate().equals(selectedDate)) {
                filteredList.add(a);
            }
        }

        for (Appointment a : filteredList){
            if (a.getStatus().equals("pending")) {
                pendingCount++;
            } else if (a.getStatus().equals("completed")) {
                completedCount++;
            } else if (a.getStatus().equals("cancelled")) {
                cancelledCount++;
            }
        }

        viewAppointmentsTC.getItems().clear();
        viewAppointmentsTC.getItems().addAll(filteredList);

        totalAppointmentLabel.setText(Integer.toString(filteredList.size()));
        pendingLabel.setText(Integer.toString(pendingCount));
        completedLabel.setText(Integer.toString(completedCount));
        cancelledLabel.setText(Integer.toString(cancelledCount));
    }
}