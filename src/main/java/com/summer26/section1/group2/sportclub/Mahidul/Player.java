package com.summer26.section1.group2.sportclub.Mahidul;

import java.time.LocalDate;

public class Player {
    private String playerID;
    private String playerName;
    private int squadNumber;

    private String kitTypeAssigned;
    private String kitSize;
    private LocalDate lastAssignedDate;

    public Player(String playerID, String playerName, int squadNumber, String kitTypeAssigned, String kitSize, LocalDate lastAssignedDate) {
        this.playerID = playerID;
        this.playerName = playerName;
        this.squadNumber = squadNumber;
        this.kitTypeAssigned = kitTypeAssigned;
        this.kitSize = kitSize;
        this.lastAssignedDate = lastAssignedDate;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getSquadNumber() {
        return squadNumber;
    }

    public void setSquadNumber(int squadNumber) {
        this.squadNumber = squadNumber;
    }

    public String getKitTypeAssigned() {
        return kitTypeAssigned;
    }

    public void setKitTypeAssigned(String kitTypeAssigned) {
        this.kitTypeAssigned = kitTypeAssigned;
    }

    public String getKitSize() {
        return kitSize;
    }

    public void setKitSize(String kitSize) {
        this.kitSize = kitSize;
    }

    public LocalDate getLastAssignedDate() {
        return lastAssignedDate;
    }

    public void setLastAssignedDate(LocalDate lastAssignedDate) {
        this.lastAssignedDate = lastAssignedDate;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerID='" + playerID + '\'' +
                ", playerName='" + playerName + '\'' +
                ", squadNumber=" + squadNumber +
                ", kitTypeAssigned='" + kitTypeAssigned + '\'' +
                ", kitSize='" + kitSize + '\'' +
                ", lastAssignedDate=" + lastAssignedDate +
                '}';
    }
}
