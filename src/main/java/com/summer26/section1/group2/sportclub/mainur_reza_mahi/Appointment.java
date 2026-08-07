package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import java.io.Serializable;

public class Appointment implements Serializable {
    private String apptId , visitorName , contactNo , hostStaff , apptDate , apptTime , purpose , status ;

    public Appointment(String apptId, String visitorName, String contactNo, String hostStaff, String apptDate, String apptTime, String purpose, String status) {
        this.apptId = apptId;
        this.visitorName = visitorName;
        this.contactNo = contactNo;
        this.hostStaff = hostStaff;
        this.apptDate = apptDate;
        this.apptTime = apptTime;
        this.purpose = purpose;
        this.status = status;
    }

    public String getApptId() {
        return apptId;
    }

    public void setApptId(String apptId) {
        this.apptId = apptId;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getHostStaff() {
        return hostStaff;
    }

    public void setHostStaff(String hostStaff) {
        this.hostStaff = hostStaff;
    }

    public String getApptDate() {
        return apptDate;
    }

    public void setApptDate(String apptDate) {
        this.apptDate = apptDate;
    }

    public String getApptTime() {
        return apptTime;
    }

    public void setApptTime(String apptTime) {
        this.apptTime = apptTime;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "apptId='" + apptId + '\'' +
                ", visitorName='" + visitorName + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", hostStaff='" + hostStaff + '\'' +
                ", apptDate='" + apptDate + '\'' +
                ", apptTime='" + apptTime + '\'' +
                ", purpose='" + purpose + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
