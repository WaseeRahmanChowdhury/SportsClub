package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class SponsorshipManagementController {

    @FXML
    private Button addSponsorButton;
    @FXML
    private TableView<Sponsor> sponsorTable;
    @FXML
    private TableColumn<Sponsor, String> nameColumn;
    @FXML
    private TableColumn<Sponsor, Double> amountColumn;
    @FXML
    private TableColumn<Sponsor, String> startDateColumn;
    @FXML
    private TableColumn<Sponsor, String> endDateColumn;
    @FXML
    private PieChart sponsorshipChart;

    @FXML
    private Label selectedSponsorIdLabel;
    @FXML
    private TextField editCompanyNameField;
    @FXML
    private TextField editContactPersonField;
    @FXML
    private TextField editContactNumberField;
    @FXML
    private TextField editAnnualAmountField;
    @FXML
    private DatePicker editContractStartField;
    @FXML
    private DatePicker editContractEndField;
    @FXML
    private Label statusLabel;

    private final ObservableList<Sponsor> sponsorRows = FXCollections.observableArrayList();
    @FXML
    private Button deleteSponsorButton;

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("annualAmount"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("contractStartDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("contractEndDate"));

        sponsorTable.setItems(sponsorRows);

        sponsorTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Sponsor>() {
            @Override
            public void changed(ObservableValue<? extends Sponsor> observable, Sponsor oldValue, Sponsor newValue) {
                populateEditFields(newValue);
            }
        });

        // event-4: display sponsor list
        refreshSponsors();
    }

    private void refreshSponsors() {
        sponsorRows.setAll(Sponsor.getSponsors());
        updateSponsorshipChart();
    }

    private void updateSponsorshipChart() {
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
        for (Sponsor sponsor : sponsorRows) {
            pieData.add(new PieChart.Data(sponsor.getCompanyName(), sponsor.getAnnualAmount()));
        }
        sponsorshipChart.setData(pieData);
    }

    private void populateEditFields(Sponsor sponsor) {
        if (sponsor == null) {
            selectedSponsorIdLabel.setText("");
            editCompanyNameField.clear();
            editContactPersonField.clear();
            editContactNumberField.clear();
            editAnnualAmountField.clear();
            editContractStartField.setValue(null);
            editContractEndField.setValue(null);
            return;
        }
        selectedSponsorIdLabel.setText(sponsor.getSponsorId());
        editCompanyNameField.setText(sponsor.getCompanyName());
        editContactPersonField.setText(sponsor.getContactPersonName());
        editContactNumberField.setText(sponsor.getContactNumber());
        editAnnualAmountField.setText(String.valueOf(sponsor.getAnnualAmount()));
        editContractStartField.setValue(sponsor.getContractStartDate());
        editContractEndField.setValue(sponsor.getContractEndDate());
    }

    // event-5: Admin clicks 'Add New Sponsor'
    @FXML
    private void openAddSponsor() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddSponsor.fxml"));
            Parent root = loader.load();

            Stage formStage = new Stage();
            formStage.setTitle("Add New Sponsor");
            formStage.initModality(Modality.APPLICATION_MODAL);
            formStage.initOwner(addSponsorButton.getScene().getWindow());
            formStage.setScene(new Scene(root));
            formStage.showAndWait();

            refreshSponsors();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Admin selects a sponsor above, edits the fields below, and clicks 'Save Changes'
    @FXML
    private void saveChanges() {
        statusLabel.setTextFill(Color.RED);

        String sponsorId = selectedSponsorIdLabel.getText();
        if (sponsorId == null || sponsorId.isBlank()) {
            statusLabel.setText("Select a sponsor from the table first.");
            return;
        }

        if (editCompanyNameField.getText().isBlank() || editContactPersonField.getText().isBlank()
                || editContactNumberField.getText().isBlank() || editAnnualAmountField.getText().isBlank()
                || editContractStartField.getValue() == null || editContractEndField.getValue() == null) {
            statusLabel.setText("Please fill in all fields.");
            return;
        }

        double annualAmount;
        try {
            annualAmount = Double.parseDouble(editAnnualAmountField.getText().trim());
        } catch (NumberFormatException e) {
            statusLabel.setText("Sponsorship amount must be numeric.");
            return;
        }

        if (annualAmount <= 0) {
            statusLabel.setText("Sponsorship amount must be a positive number.");
            return;
        }

        LocalDate startDate = editContractStartField.getValue();
        LocalDate endDate = editContractEndField.getValue();
        if (!endDate.isAfter(startDate)) {
            statusLabel.setText("Contract end date must be after the start date.");
            return;
        }

        boolean updated = Sponsor.updateSponsor(
                sponsorId,
                editCompanyNameField.getText().trim(),
                editContactPersonField.getText().trim(),
                editContactNumberField.getText().trim(),
                annualAmount,
                startDate,
                endDate
        );

        if (!updated) {
            statusLabel.setText("Could not find sponsor record to update.");
            return;
        }

        ActivityLog.log(ActivityLog.TYPE_SPONSORSHIP,
                "Updated sponsor record " + sponsorId, "Admin");

        refreshSponsors();
        sponsorTable.refresh();
        statusLabel.setTextFill(Color.GREEN);
        statusLabel.setText("Sponsor record updated successfully.");
    }

    // Admin clicks 'Delete Selected' to remove the currently selected sponsor
    @FXML
    private void deleteSelectedSponsor() {
        statusLabel.setText("");

        Sponsor selected = sponsorTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Select a sponsor from the table first.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Delete sponsor \"" + selected.getCompanyName() + "\"? This cannot be undone.",
                ButtonType.YES, ButtonType.NO);
        confirm.setTitle("Confirm Delete");
        confirm.setHeaderText(null);
        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            Sponsor.deleteSponsor(selected.getSponsorId());
            ActivityLog.log(ActivityLog.TYPE_SPONSORSHIP,
                    "Deleted sponsor " + selected.getCompanyName() + " (" + selected.getSponsorId() + ")", "Admin");
            refreshSponsors();
            populateEditFields(null);
            statusLabel.setTextFill(Color.GREEN);
            statusLabel.setText("Sponsor deleted.");
        }
    }
}
