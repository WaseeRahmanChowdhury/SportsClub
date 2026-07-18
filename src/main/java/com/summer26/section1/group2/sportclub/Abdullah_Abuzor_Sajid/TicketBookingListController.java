package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TicketBookingListController {

    @FXML
    private TextField searchField;
    @FXML
    private TableView<TicketBooking> bookingTable;
    @FXML
    private TableColumn<TicketBooking, String> bookingIdColumn;
    @FXML
    private TableColumn<TicketBooking, String> fanNameColumn;
    @FXML
    private TableColumn<TicketBooking, String> matchNameColumn;
    @FXML
    private TableColumn<TicketBooking, String> categoryColumn;
    @FXML
    private TableColumn<TicketBooking, Integer> quantityColumn;
    @FXML
    private TableColumn<TicketBooking, String> statusColumn;

    @FXML
    private Label detailBookingIdLabel;
    @FXML
    private Label detailFanMembershipIdLabel;
    @FXML
    private Label detailMatchLabel;
    @FXML
    private Label detailCategoryLabel;
    @FXML
    private Label detailQuantityLabel;
    @FXML
    private Label detailStatusLabel;

    private final ObservableList<TicketBooking> bookingRows = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        bookingIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        fanNameColumn.setCellValueFactory(new PropertyValueFactory<>("fanName"));
        matchNameColumn.setCellValueFactory(new PropertyValueFactory<>("matchName"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("ticketCategory"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("bookingStatus"));

        bookingTable.setItems(bookingRows);

        bookingTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDetails(newValue)
        );

        // event-4: load all ticket booking records
        bookingRows.setAll(TicketBooking.getBookings());
    }

    // event-6/7: Admin enters a booking ID or fan name, matching results displayed
    @FXML
    private void onSearch() {
        bookingRows.setAll(TicketBooking.searchBookings(searchField.getText()));
    }

    // event-8/9: display selected booking details
    private void showDetails(TicketBooking booking) {
        if (booking == null) {
            detailBookingIdLabel.setText("");
            detailFanMembershipIdLabel.setText("");
            detailMatchLabel.setText("");
            detailCategoryLabel.setText("");
            detailQuantityLabel.setText("");
            detailStatusLabel.setText("");
            return;
        }
        detailBookingIdLabel.setText(booking.getBookingId());
        detailFanMembershipIdLabel.setText(booking.getFanMembershipId());
        detailMatchLabel.setText(booking.getMatchName());
        detailCategoryLabel.setText(booking.getTicketCategory());
        detailQuantityLabel.setText(String.valueOf(booking.getQuantity()));
        detailStatusLabel.setText(booking.getBookingStatus());
    }
}
