package com.summer26.section1.group2.sportclub.Mahidul;

import java.time.LocalDate;

public class MedicalRecord {
    private String injuryType;
    private String bodyPart;
    private String severity;
    private LocalDate injuryDate;
    private int recoveryDays;

    public MedicalRecord(String injuryType, String bodyPart, String severity, LocalDate injuryDate, int recoveryDays) {
        this.injuryType = injuryType;
        this.bodyPart = bodyPart;
        this.severity = severity;
        this.injuryDate = injuryDate;
        this.recoveryDays = recoveryDays;
    }

    public String getInjuryType() {
        return injuryType;
    }

    public void setInjuryType(String injuryType) {
        this.injuryType = injuryType;
    }

    public String getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public LocalDate getInjuryDate() {
        return injuryDate;
    }

    public void setInjuryDate(LocalDate injuryDate) {
        this.injuryDate = injuryDate;
    }

    public int getRecoveryDays() {
        return recoveryDays;
    }

    public void setRecoveryDays(int recoveryDays) {
        this.recoveryDays = recoveryDays;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "injuryType='" + injuryType + '\'' +
                ", bodyPart='" + bodyPart + '\'' +
                ", severity='" + severity + '\'' +
                ", injuryDate=" + injuryDate +
                ", recoveryDays=" + recoveryDays +
                '}';
    }
}
