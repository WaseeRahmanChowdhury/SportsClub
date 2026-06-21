package com.summer26.section1.group2.sportclub.Mahidul;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class injuryController
{
    @javafx.fxml.FXML
    private TextField player_name_tf;
    @javafx.fxml.FXML
    private ComboBox <String> injury_type_combo;
    @javafx.fxml.FXML
    private TextField player_idtf;
    @javafx.fxml.FXML
    private DatePicker injurydatefx;
    @javafx.fxml.FXML
    private TextArea messege_lavel;
    @javafx.fxml.FXML
    public void initialize() {
        injury_type_combo.getItems().addAll("Shoulder Injury","Head Injury","Hamstring Strain");
    }

    @javafx.fxml.FXML
    public void save_button(ActionEvent actionEvent) {
        String id = String.valueOf(Integer.parseInt(player_idtf.getText()));
        String name=player_name_tf.getText();
        String type=injury_type_combo.getValue();
        LocalDate date=injurydatefx.getValue();
        injury palyer=new injury(id,name,type,date);
        messege_lavel.setText("Successfull");



    }

    @javafx.fxml.FXML
    public void Clear_button(ActionEvent actionEvent) {
        player_idtf.clear();
        player_name_tf.clear();
        injury_type_combo.setValue(null);
        injurydatefx.setValue(null);
        messege_lavel.setText("clear");



    }
}