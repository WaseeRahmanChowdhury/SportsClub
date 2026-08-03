//package com.summer26.section1.group2.sportclub.Mahidul;

//import java.time.LocalDate;

//public class Medical {
package com.summer26.section1.group2.sportclub.Mahidul;

import java.time.LocalDate;

public class Medical {

    // =========================
    // Injury Assessment
    // =========================
    private String playerId;
    private String playerName;
    private String injuryType;
    private String severity;
    private LocalDate assessmentDate;
    private int recoveryDays;
    private String doctorId;
    private String availabilityStatus;

    // =========================
    // Injury Statistics
    // =========================
    private String bodypart;
    private int totalInjuries;
    private String minor;
    private String moderate;
    private String severe;

    // =========================
    // Medical Supply Request
    // =========================
    private String itemName;
    private int quantity;
    private String unit;
    private String urgency;
    private String reason;
    private String attachment;

    // =========================
    // Recovery Session
    // =========================
    private LocalDate sessionDate;
    private String startTime;
    private String endTime;
    private String physiotherapist;
    private String sessionType;

    // =====================================================
    // Constructor 1 : Injury Assessment
    // =====================================================

    public Medical(String playerId,
                   String playerName,
                   String injuryType,
                   String severity,
                   LocalDate assessmentDate,
                   int recoveryDays,
                   String doctorId,
                   String availabilityStatus) {

        this.playerId = playerId;
        this.playerName = playerName;
        this.injuryType = injuryType;
        this.severity = severity;
        this.assessmentDate = assessmentDate;
        this.recoveryDays = recoveryDays;
        this.doctorId = doctorId;
        this.availabilityStatus = availabilityStatus;
    }

    // =====================================================
    // Constructor 2 : Recovery Session
    // =====================================================

    public Medical(String playerId,
                   LocalDate sessionDate,
                   String startTime,
                   String endTime,
                   String physiotherapist,
                   String sessionType) {

        this.playerId = playerId;
        this.sessionDate = sessionDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.physiotherapist = physiotherapist;
        this.sessionType = sessionType;
    }

    // =====================================================
    // Constructor 3 : Injury Statistics
    // =====================================================

    public Medical(String bodypart,
                   int totalInjuries,
                   String minor,
                   String moderate,
                   String severe) {

        this.bodypart = bodypart;
        this.totalInjuries = totalInjuries;
        this.minor = minor;
        this.moderate = moderate;
        this.severe = severe;
    }

    // =====================================================
    // Constructor 4 : Medical Supply Request
    // =====================================================

    public Medical(String itemName,
                   int quantity,
                   String unit,
                   String urgency,
                   String reason,
                   String attachment) {

        this.itemName = itemName;
        this.quantity = quantity;
        this.unit = unit;
        this.urgency = urgency;
        this.reason = reason;
        this.attachment = attachment;
    }

    // =====================================================
    // Getters and Setters
    // =====================================================

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

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public String getBodypart() {
        return bodypart;
    }

    public void setBodypart(String bodypart) {
        this.bodypart = bodypart;
    }

    public int getTotalInjuries() {
        return totalInjuries;
    }

    public void setTotalInjuries(int totalInjuries) {
        this.totalInjuries = totalInjuries;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

    public String getModerate() {
        return moderate;
    }

    public void setModerate(String moderate) {
        this.moderate = moderate;
    }

    public String getSevere() {
        return severe;
    }

    public void setSevere(String severe) {
        this.severe = severe;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public LocalDate getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPhysiotherapist() {
        return physiotherapist;
    }

    public void setPhysiotherapist(String physiotherapist) {
        this.physiotherapist = physiotherapist;
    }

    public String getSessionType() {
        return sessionType;
    }

    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
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
                ", availabilityStatus='" + availabilityStatus + '\'' +
                ", bodypart='" + bodypart + '\'' +
                ", totalInjuries=" + totalInjuries +
                ", minor='" + minor + '\'' +
                ", moderate='" + moderate + '\'' +
                ", severe='" + severe + '\'' +
                ", itemName='" + itemName + '\'' +
                ", quantity=" + quantity +
                ", unit='" + unit + '\'' +
                ", urgency='" + urgency + '\'' +
                ", reason='" + reason + '\'' +
                ", attachment='" + attachment + '\'' +
                ", sessionDate=" + sessionDate +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", physiotherapist='" + physiotherapist + '\'' +
                ", sessionType='" + sessionType + '\'' +
                '}';
    }
}