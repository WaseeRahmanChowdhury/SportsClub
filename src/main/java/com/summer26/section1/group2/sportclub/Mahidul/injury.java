package com.summer26.section1.group2.sportclub.Mahidul;

import java.time.LocalDate;

public class injury {
    private String playerId;
    private String playerName;
    private String injuryType;
    private LocalDate injuryDate;

    public injury(String playerId, String playerName, String injuryType, LocalDate injuryDate) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.injuryType = injuryType;
        this.injuryDate = injuryDate;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getInjuryType() {
        return injuryType;
    }

    public void setInjuryType(String injuryType) {
        this.injuryType = injuryType;
    }

    public LocalDate getInjuryDate() {
        return injuryDate;
    }

    public void setInjuryDate(LocalDate injuryDate) {
        this.injuryDate = injuryDate;
    }

    @Override
    public String toString() {
        return "injury{" +
                "playerId='" + playerId + '\'' +
                ", playerName='" + playerName + '\'' +
                ", injuryType='" + injuryType + '\'' +
                ", injuryDate=" + injuryDate +
                '}';
    }
}
