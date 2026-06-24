package com.summer26.section1.group2.sportclub.Mahidul;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MedicalController
{
    @javafx.fxml.FXML
    private ComboBox <String>combofx;
    @javafx.fxml.FXML
    private TextField namefx;
    @javafx.fxml.FXML
    private DatePicker dob;
    @javafx.fxml.FXML
    private TextField recovaryfx;
    @javafx.fxml.FXML
    private ComboBox<String> avalility_status;
    @javafx.fxml.FXML
    private TextArea outfutfx;
    @javafx.fxml.FXML
    private TextField idfx;

    @javafx.fxml.FXML
    public void initialize() {
        combofx.getItems().addAll(
                "Head Injury",
                "Leg Injury",
                "Arm Injury",
                "Muscle Strain",
                "Fracture"
        );

        avalility_status.getItems().addAll(
                "Available",
                "Unavailable",
                "Under Treatment"
        );
    }

    @javafx.fxml.FXML
    public void result(ActionEvent actionEvent) {
        int id = Integer.parseInt(idfx.getText());
        ;
        String name = namefx.getText();
        String injuryType = combofx.getValue();
        String recoveryTime = recovaryfx.getText();
        String status = avalility_status.getValue();
        if (name.isEmpty() ||
                injuryType == null ||
                dob.getValue() == null ||
                recoveryTime.isEmpty() ||
                status == null) {
            outfutfx.setText("Please fill up all fields.");
        }
    }

}