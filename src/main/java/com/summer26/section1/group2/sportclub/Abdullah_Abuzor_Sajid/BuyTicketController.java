package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;

import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class BuyTicketController {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^01\\d{9}$");

    @FXML
    private TextField fanMembershipIdField;
    @FXML
    private ComboBox<Match> matchCombo;
    @FXML
    private ComboBox<String> categoryCombo;
    @FXML
    private TextField quantityField;
    @FXML
    private Label availabilityLabel;
    @FXML
    private Label summaryLabel;
    @FXML
    private TextField mobileBankingField;
    @FXML
    private Label statusLabel;

    @FXML
    private void initialize() {
        // event-4: fetch list of upcoming home matches
        matchCombo.getItems().setAll(Match.getMatches());
        matchCombo.setConverter(new StringConverter<>() {
            @Override
            public String toString(Match match) {
                if (match == null) {
                    return "";
                }
                return "vs " + match.getOpponentClubName() + " - " + match.getMatchDate().format(DATE_FORMAT)
                        + " (" + match.getVenueName() + ")";
            }

            @Override
            public Match fromString(String string) {
                return null;
            }
        });

        categoryCombo.getItems().addAll(
                TicketBooking.CATEGORY_VIP,
                TicketBooking.CATEGORY_STAND,
                TicketBooking.CATEGORY_GALLERY
        );

        matchCombo.valueProperty().addListener((obs, oldVal, newVal) -> updateSummary());
        categoryCombo.valueProperty().addListener((obs, oldVal, newVal) -> updateSummary());
        quantityField.textProperty().addListener((obs, oldVal, newVal) -> updateSummary());
    }

    private void updateSummary() {
        Match match = matchCombo.getValue();
        String category = categoryCombo.getValue();

        if (match == null || category == null) {
            availabilityLabel.setText("");
            summaryLabel.setText("");
            return;
        }

        int available = TicketBooking.getAvailableQuantity(match.getMatchId(), category);
        availabilityLabel.setText(available + " " + category + " tickets available");

        double unitPrice = TicketBooking.getUnitPrice(category);
        int quantity = parseQuantity();
        if (quantity > 0) {
            summaryLabel.setText(String.format("%.2f x %d = %.2f BDT", unitPrice, quantity, unitPrice * quantity));
        } else {
            summaryLabel.setText(String.format("Unit price: %.2f BDT", unitPrice));
        }
    }

    private int parseQuantity() {
        try {
            return Integer.parseInt(quantityField.getText().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // event-11/12: process payment, deduct seats, generate e-ticket
    @FXML
    private void confirmPurchase() {
        statusLabel.setTextFill(Color.RED);

        Match match = matchCombo.getValue();
        String category = categoryCombo.getValue();

        if (fanMembershipIdField.getText().isBlank() || match == null || category == null
                || quantityField.getText().isBlank() || mobileBankingField.getText().isBlank()) {
            statusLabel.setText("Please fill in all fields.");
            return;
        }

        FanMember fan = FanMember.findByMembershipId(fanMembershipIdField.getText().trim());
        if (fan == null) {
            statusLabel.setText("Fan Membership ID not found. Please register first.");
            return;
        }

        // event-7: validate quantity is between 1 and 4
        int quantity = parseQuantity();
        if (quantity < 1 || quantity > 4) {
            statusLabel.setText("Quantity must be an integer between 1 and 4.");
            return;
        }

        // event-8: verify the requested number of tickets is still available
        int available = TicketBooking.getAvailableQuantity(match.getMatchId(), category);
        if (quantity > available) {
            statusLabel.setText("Only " + available + " tickets left in this category.");
            return;
        }

        // event-10: validate mobile banking number
        String mobileBankingNumber = mobileBankingField.getText().trim();
        if (!PHONE_PATTERN.matcher(mobileBankingNumber).matches()) {
            statusLabel.setText("Mobile banking number must be 11 digits starting with 01.");
            return;
        }

        String matchName = "vs " + match.getOpponentClubName() + " - " + match.getMatchDate().format(DATE_FORMAT);
        TicketBooking booking = TicketBooking.bookTicket(
                fan.getFanMembershipId(), fan.getFullName(), match.getMatchId(), matchName, category, quantity);

        if (booking == null) {
            statusLabel.setText("Booking failed. Tickets may no longer be available.");
            return;
        }

        statusLabel.setTextFill(Color.GREEN);
        statusLabel.setText("Booking confirmed. Booking ID: " + booking.getBookingId());
    }
}
