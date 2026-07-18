package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Transfer {
    private final String transferId;
    private final String playerId;
    private final String direction;
    private final String counterpartClubName;
    private final double transferFee;
    private final LocalDate effectiveDate;

    public Transfer(String transferId, String playerId, String direction, String counterpartClubName,
                     double transferFee, LocalDate effectiveDate) {
        this.transferId = transferId;
        this.playerId = playerId;
        this.direction = direction;
        this.counterpartClubName = counterpartClubName;
        this.transferFee = transferFee;
        this.effectiveDate = effectiveDate;
    }

    public String getTransferId() {
        return transferId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getDirection() {
        return direction;
    }

    public String getCounterpartClubName() {
        return counterpartClubName;
    }

    public double getTransferFee() {
        return transferFee;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    // --- Transfer records (all initiated transfers) ---

    private static final List<Transfer> transfers = new ArrayList<>();

    // event-8: save the transfer record, assign a Transfer ID
    public static String initiateTransfer(String playerId, String direction, String counterpartClubName,
                                           double transferFee, LocalDate effectiveDate) {
        String transferId = String.format("TRF-%04d", transfers.size() + 1);
        transfers.add(new Transfer(transferId, playerId, direction, counterpartClubName, transferFee, effectiveDate));
        return transferId;
    }

    public static List<Transfer> getTransfers() {
        return transfers;
    }
}
