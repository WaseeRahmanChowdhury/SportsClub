package com.summer26.section1.group2.sportclub.Mahidul;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Medical {
    private String playerId;
    private String playerName;
    private String injuryType;
    private String severity; // Minor, Moderate, Severe
    private LocalDate assessmentDate;
    private int recoveryDays;
    private String doctorId;
    private LocalDateTime timestamp;
    private String availabilityStatus;

    public Medical(String playerId, String playerName, String injuryType, String severity, LocalDate assessmentDate, int recoveryDays, String doctorId, LocalDateTime timestamp, String availabilityStatus) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.injuryType = injuryType;
        this.severity = severity;
        this.assessmentDate = assessmentDate;
        this.recoveryDays = recoveryDays;
        this.doctorId = doctorId;
        this.timestamp = timestamp;
        this.availabilityStatus = availabilityStatus;
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

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public LocalDate getAssessmentDate() {
        return assessmentDate;
    }

    public void setAssessmentDate(LocalDate assessmentDate) {
        this.assessmentDate = assessmentDate;
    }

    public int getRecoveryDays() {
        return recoveryDays;
    }

    public void setRecoveryDays(int recoveryDays) {
        this.recoveryDays = recoveryDays;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    @Override
    public String toString() {
        return "Medical{" +
                "playerId='" + playerId + '\'' +
                ", playerName='" + playerName + '\'' +
                ", injuryType='" + injuryType + '\'' +
                ", severity='" + severity + '\'' +
                ", assessmentDate=" + assessmentDate +
                ", recoveryDays=" + recoveryDays +
                ", doctorId='" + doctorId + '\'' +
                ", timestamp=" + timestamp +
                ", availabilityStatus='" + availabilityStatus + '\'' +
                '}';
    }
}
