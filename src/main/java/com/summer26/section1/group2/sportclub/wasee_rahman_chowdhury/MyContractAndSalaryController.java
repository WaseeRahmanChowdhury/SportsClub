package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MyContractAndSalaryController {

    @FXML
    private TextField monthTF;
    @FXML
    private TextField netSalaryPaidTF;
    @FXML
    private DatePicker paymentDateDP;
    @FXML
    private Button addSalaryRecordButton;

    @FXML
    private TableView<SalaryRow> salaryHistoryTable;

    @FXML
    private TableColumn<SalaryRow, String> colMonth;
    @FXML
    private TableColumn<SalaryRow, String> colNetSalaryPaid;
    @FXML
    private TableColumn<SalaryRow, String> colPaymentDate;

    private final ArrayList<SalaryRow> salaryData = new ArrayList<>();

    private static final String FILE_NAME = "SalaryPayment.bin";

    @FXML
    private void initialize() {
        colMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        colNetSalaryPaid.setCellValueFactory(new PropertyValueFactory<>("netSalaryPaid"));
        colPaymentDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));

        loadSalaryDataFromFile();

        salaryHistoryTable.getItems().addAll(salaryData);
    }

    @FXML
    public void addSalaryRecordOA(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        String month = monthTF.getText();
        String netSalaryPaidText = netSalaryPaidTF.getText();
        LocalDate paymentDate = paymentDateDP.getValue();

        if (month == null || month.isEmpty()) {
            alert.setContentText("ERROR: Month cannot be empty.");
            alert.showAndWait();
            return;
        }

        if (netSalaryPaidText == null || netSalaryPaidText.isEmpty()) {
            alert.setContentText("ERROR: Net Salary Paid cannot be empty.");
            alert.showAndWait();
            return;
        }

        double netSalaryPaid;
        try {
            netSalaryPaid = Double.parseDouble(netSalaryPaidText);
        } catch (NumberFormatException e) {
            alert.setContentText("ERROR: Net Salary Paid must be a valid number.");
            alert.showAndWait();
            return;
        }

        if (netSalaryPaid <= 0) {
            alert.setContentText("ERROR: Net Salary Paid must be greater than zero.");
            alert.showAndWait();
            return;
        }

        if (paymentDate == null) {
            alert.setContentText("ERROR: Payment Date must be selected.");
            alert.showAndWait();
            return;
        }

        SalaryRow row = new SalaryRow(month, String.valueOf(netSalaryPaid), paymentDate.toString());

        salaryData.add(row);
        salaryHistoryTable.getItems().add(row);

        saveSalaryDataToFile();

        monthTF.clear();
        netSalaryPaidTF.clear();
        paymentDateDP.setValue(null);
    }

    private void saveSalaryDataToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(salaryData);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("ERROR: Could not save salary data to file.");
            alert.showAndWait();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadSalaryDataFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            ArrayList<SalaryRow> loadedData = (ArrayList<SalaryRow>) in.readObject();
            salaryData.addAll(loadedData);
        } catch (IOException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("ERROR: Could not load salary data from file.");
            alert.showAndWait();
        }
    }

    /*
     * Simple representation of one row of salary history.
     */
    public static class SalaryRow implements Serializable {
        private String month;
        private String netSalaryPaid;
        private String paymentDate;

        public SalaryRow() {
        }

        public SalaryRow(String month, String netSalaryPaid, String paymentDate) {
            this.month = month;
            this.netSalaryPaid = netSalaryPaid;
            this.paymentDate = paymentDate;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getNetSalaryPaid() {
            return netSalaryPaid;
        }

        public void setNetSalaryPaid(String netSalaryPaid) {
            this.netSalaryPaid = netSalaryPaid;
        }

        public String getPaymentDate() {
            return paymentDate;
        }

        public void setPaymentDate(String paymentDate) {
            this.paymentDate = paymentDate;
        }
    }
}
