package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketBooking implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String bookingId;
    private final String fanMembershipId;
    private final String fanName;
    private final String matchId;
    private final String matchName;
    private final String ticketCategory;
    private final int quantity;
    private final double totalAmount;
    private String bookingStatus;

    public TicketBooking(String bookingId, String fanMembershipId, String fanName, String matchId,
                         String matchName, String ticketCategory, int quantity, double totalAmount,
                         String bookingStatus) {
        this.bookingId = bookingId;
        this.fanMembershipId = fanMembershipId;
        this.fanName = fanName;
        this.matchId = matchId;
        this.matchName = matchName;
        this.ticketCategory = ticketCategory;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.bookingStatus = bookingStatus;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getFanMembershipId() {
        return fanMembershipId;
    }

    public String getFanName() {
        return fanName;
    }

    public String getMatchId() {
        return matchId;
    }

    public String getMatchName() {
        return matchName;
    }

    public String getTicketCategory() {
        return ticketCategory;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    // --- Ticket booking registry (all bookings + per-match inventory) ---

    public static final String CATEGORY_VIP = "VIP";
    public static final String CATEGORY_STAND = "Stand";
    public static final String CATEGORY_GALLERY = "Gallery";

    private static final int VIP_CAPACITY = 50;
    private static final int STAND_CAPACITY = 200;
    private static final int GALLERY_CAPACITY = 500;

    private static final String DATA_FILE = "Sajid_Data/TicketBooking.bin";
    private static final List<TicketBooking> bookings = loadBookings();
    // matchId -> (category -> tickets remaining)
    private static final Map<String, Map<String, Integer>> inventoryByMatch = new HashMap<>();

    static {
        // Replay loaded bookings against inventory so remaining capacity reflects tickets already sold.
        for (TicketBooking booking : bookings) {
            Map<String, Integer> stock = inventoryFor(booking.getMatchId());
            int remaining = stock.getOrDefault(booking.getTicketCategory(), 0);
            stock.put(booking.getTicketCategory(), remaining - booking.getQuantity());
        }
    }

    @SuppressWarnings("unchecked")
    private static List<TicketBooking> loadBookings() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<TicketBooking>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static void saveBookings() {
        File file = new File(DATA_FILE);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(bookings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double getUnitPrice(String ticketCategory) {
        return switch (ticketCategory) {
            case CATEGORY_VIP -> 2000.0;
            case CATEGORY_STAND -> 800.0;
            case CATEGORY_GALLERY -> 300.0;
            default -> 0.0;
        };
    }

    public static int getAvailableQuantity(String matchId, String ticketCategory) {
        return inventoryFor(matchId).getOrDefault(ticketCategory, 0);
    }

    private static Map<String, Integer> inventoryFor(String matchId) {
        return inventoryByMatch.computeIfAbsent(matchId, id -> {
            Map<String, Integer> stock = new HashMap<>();
            stock.put(CATEGORY_VIP, VIP_CAPACITY);
            stock.put(CATEGORY_STAND, STAND_CAPACITY);
            stock.put(CATEGORY_GALLERY, GALLERY_CAPACITY);
            return stock;
        });
    }

    // event-11/12: process payment, deduct seats, generate e-ticket with booking ID
    public static TicketBooking bookTicket(String fanMembershipId, String fanName, String matchId,
                                           String matchName, String ticketCategory, int quantity) {
        Map<String, Integer> stock = inventoryFor(matchId);
        int available = stock.getOrDefault(ticketCategory, 0);
        if (quantity > available) {
            return null;
        }
        stock.put(ticketCategory, available - quantity);

        double totalAmount = getUnitPrice(ticketCategory) * quantity;
        String bookingId = String.format("BKG-%04d", bookings.size() + 1);
        TicketBooking booking = new TicketBooking(bookingId, fanMembershipId, fanName, matchId,
                matchName, ticketCategory, quantity, totalAmount, "Confirmed");
        bookings.add(booking);
        saveBookings();
        return booking;
    }

    public static List<TicketBooking> getBookings() {
        return bookings;
    }

    // event-6: Admin enters a booking ID or fan name in the search box
    public static List<TicketBooking> searchBookings(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return new ArrayList<>(bookings);
        }
        String needle = keyword.trim().toLowerCase();
        List<TicketBooking> results = new ArrayList<>();
        for (TicketBooking booking : bookings) {
            if (booking.getBookingId().toLowerCase().contains(needle)
                    || booking.getFanName().toLowerCase().contains(needle)) {
                results.add(booking);
            }
        }
        return results;
    }
}
