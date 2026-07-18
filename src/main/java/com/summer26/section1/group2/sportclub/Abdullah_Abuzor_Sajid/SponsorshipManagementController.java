package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

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

    private final ObservableList<Sponsor> sponsorRows = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("annualAmount"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("contractStartDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("contractEndDate"));

        sponsorTable.setItems(sponsorRows);

        // event-4: display sponsor list
        refreshSponsors();
    }

    private void refreshSponsors() {
        sponsorRows.setAll(Sponsor.getSponsors());
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
}
