package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RegisterVisitorController
{
    @javafx.fxml.FXML
    private TextField contactNumberTF;
    @javafx.fxml.FXML
    private TextField visitorNameTF;
    @javafx.fxml.FXML
    private ComboBox<String> purposeOfVisitCB;
    @javafx.fxml.FXML
    private ComboBox<String> hostStaffNameCB;

    private static final String FILE_NAME = "visitors.bin";
    ArrayList<Visitor> visitorList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        purposeOfVisitCB.getItems().addAll("Meeting", "Delivery", "Training", "Other");
        hostStaffNameCB.getItems().addAll(
                "Coach Salim",
                "Coach Abdullah",
                "Admin Rafi",
                "Admin Nadia",
                "Club President",
                "Team Manager",
                "Physiotherapist",
                "Media Officer"
        );

        // Load existing visitors from file, if it exists
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(FILE_NAME);
                ObjectInputStream ois = new ObjectInputStream(fis);
                visitorList = (ArrayList<Visitor>) ois.readObject();
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @javafx.fxml.FXML
    public void registerVisitorButtonOA(ActionEvent actionEvent) {
        int nextNumber = visitorList.size() + 1;

        String visitorId = String.format("VIS-%04d", nextNumber);
        String fullName = visitorNameTF.getText();
        String contactNo = contactNumberTF.getText();
        String purpose = purposeOfVisitCB.getValue();
        String hostStaff = hostStaffNameCB.getValue();
        String entryTime = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss"));
        String exitTime = "";
        String status = "inside";
        String visitDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        Visitor visitor = new Visitor(visitorId, fullName, contactNo, purpose, hostStaff, entryTime, exitTime, status, visitDate);

        visitorList.add(visitor);

        try {
            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(visitorList);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        visitorNameTF.clear();
        contactNumberTF.clear();
        purposeOfVisitCB.setValue(null);
        hostStaffNameCB.setValue(null);

        System.out.println("Visitor registered: " + visitor);
    }
}