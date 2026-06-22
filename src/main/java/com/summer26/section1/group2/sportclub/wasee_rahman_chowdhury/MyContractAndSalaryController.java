package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MyContractAndSalaryController {

    @FXML
    private Label contractStartDateLabel;
    @FXML
    private Label contractEndDateLabel;
    @FXML
    private Label weeklyWageLabel;

    @FXML
    private TableView<SalaryRow> salaryHistoryTable;

    @FXML
    private TableColumn<SalaryRow, String> colMonth;
    @FXML
    private TableColumn<SalaryRow, String> colNetSalaryPaid;
    @FXML
    private TableColumn<SalaryRow, String> colPaymentDate;

    private final ObservableList<SalaryRow> salaryData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        colMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        colNetSalaryPaid.setCellValueFactory(new PropertyValueFactory<>("netSalaryPaid"));
        colPaymentDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));

        salaryHistoryTable.setItems(salaryData);

        loadContractAndSalaryData();
    }

    /*
     * event-4: Load the player's contract details from the secure contract data file.
     * event-5: Display contract summary (start date, end date, weekly wage).
     * event-6: Display salary history in a table.
     * Replace with real data source.
     */
    private void loadContractAndSalaryData() {
        contractStartDateLabel.setText("");
        contractEndDateLabel.setText("");
        weeklyWageLabel.setText("");

        salaryData.clear();
        // populate contract labels and salaryData from the contract/salary data source
    }

    /*
     * Simple  representation of one row of salary history.
     */
    public static class SalaryRow {
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
