package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void loadAppointmentsButtonOA(ActionEvent actionEvent) {
    }
}