package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import java.io.Serializable;

public class Visitor implements Serializable {
    private String visitorId , fullName , contactNo , purpose , hostStaff , entryTime , exitTime , status , visitDate;

    public Visitor(String visitorId, String fullName, String contactNo, String purpose, String hostStaff, String entryTime, String exitTime, String status, String visitDate) {
        this.visitorId = visitorId;
        this.fullName = fullName;
        this.contactNo = contactNo;
        this.purpose = purpose;
        this.hostStaff = hostStaff;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.status = status;
        this.visitDate = visitDate;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getHostStaff() {
        return hostStaff;
    }

    public void setHostStaff(String hostStaff) {
        this.hostStaff = hostStaff;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "visitorId='" + visitorId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", purpose='" + purpose + '\'' +
                ", hostStaff='" + hostStaff + '\'' +
                ", entryTime='" + entryTime + '\'' +
                ", exitTime='" + exitTime + '\'' +
                ", status='" + status + '\'' +
                ", visitDate='" + visitDate + '\'' +
                '}';
    }
}
