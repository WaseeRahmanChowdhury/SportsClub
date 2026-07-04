package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

public class StaffMember {
    private final String staffId;
    private final String fullName;
    private final String role;
    private final String department;
    private final String phoneNumber;

    public StaffMember(String staffId, String fullName, String role, String department, String phoneNumber) {
        this.staffId = staffId;
        this.fullName = fullName;
        this.role = role;
        this.department = department;
        this.phoneNumber = phoneNumber;
    }

    public String getStaffId() {
        return staffId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRole() {
        return role;
    }

    public String getDepartment() {
        return department;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
