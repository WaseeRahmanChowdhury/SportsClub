package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    @javafx.fxml.FXML
    private TextField appointmentIdTF;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void cancelAppointmentButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void updateAppointmentButtonOA(ActionEvent actionEvent) {
    }
}