package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

public class ManageAppointmentsController
{
    @javafx.fxml.FXML
    private TableColumn<Appointment,String> hostStaffTC;
    @javafx.fxml.FXML
    private TableColumn<Appointment,String> fullNameTC;
    @javafx.fxml.FXML
    private TableView<Appointment> pendingAppointmentsTC;
    @javafx.fxml.FXML
    private TableColumn<Appointment,String> appointmentIdTC;
    @javafx.fxml.FXML
    private TableColumn<Appointment,String> dateTC;
    @javafx.fxml.FXML
    private TableColumn<Appointment,String> purposeTC;

    private static final String FILE_NAME = "appointments.bin";
    private ArrayList<Appointment> appointmentList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        appointmentIdTC.setCellValueFactory(new PropertyValueFactory<>("apptId"));
        fullNameTC.setCellValueFactory(new PropertyValueFactory<>("visitorName"));
        hostStaffTC.setCellValueFactory(new PropertyValueFactory<>("hostStaff"));
        dateTC.setCellValueFactory(new PropertyValueFactory<>("apptDate"));
        purposeTC.setCellValueFactory(new PropertyValueFactory<>("purpose"));

        loadPendingAppointments();
    }

    private void loadPendingAppointments() {

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

        ArrayList<Appointment> pendingList = new ArrayList<>();
        for (Appointment a : appointmentList) {
            if (a.getStatus().equals("pending")) {
                pendingList.add(a);
            }
        }

        pendingAppointmentsTC.getItems().clear();
        pendingAppointmentsTC.getItems().addAll(pendingList);
    }

    @javafx.fxml.FXML
    public void updateAppointmentButtonOA(ActionEvent actionEvent) {

        loadPendingAppointments();
    }

    @javafx.fxml.FXML
    public void cancelAppointmentButtonOA(ActionEvent actionEvent) {

        Appointment selectedAppt = pendingAppointmentsTC.getSelectionModel().getSelectedItem();

        if (selectedAppt == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please select an appointment first.");
            a.showAndWait();
            return;
        }
        selectedAppt.setStatus("cancelled");

        try {
            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(appointmentList);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadPendingAppointments();
    }

}