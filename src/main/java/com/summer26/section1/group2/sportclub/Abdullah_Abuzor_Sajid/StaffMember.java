package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import java.util.ArrayList;
import java.util.List;

public class StaffMember {
    private final String staffId;
    private String fullName;
    private String role;
    private String department;
    private String phoneNumber;

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

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // --- Staff directory (all registered staff members) ---

    private static final List<StaffMember> staffMembers = new ArrayList<>();

    public static String registerStaffMember(String fullName, String role, String department, String phoneNumber) {
        String staffId = String.format("STF-%04d", staffMembers.size() + 1);
        staffMembers.add(new StaffMember(staffId, fullName, role, department, phoneNumber));
        return staffId;
    }

    public static List<StaffMember> getStaffMembers() {
        return staffMembers;
    }

    // event-6: Admin types a name or ID in the search box to filter
    public static List<StaffMember> searchStaff(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return new ArrayList<>(staffMembers);
        }
        String needle = keyword.trim().toLowerCase();
        List<StaffMember> results = new ArrayList<>();
        for (StaffMember member : staffMembers) {
            if (member.getFullName().toLowerCase().contains(needle)
                    || member.getStaffId().toLowerCase().contains(needle)) {
                results.add(member);
            }
        }
        return results;
    }

    public static StaffMember findByStaffId(String staffId) {
        for (StaffMember member : staffMembers) {
            if (member.getStaffId().equals(staffId)) {
                return member;
            }
        }
        return null;
    }

    // event-8: save the updated record to the staff directory
    public static boolean updateStaffMember(String staffId, String fullName, String role,
                                            String department, String phoneNumber) {
        StaffMember member = findByStaffId(staffId);
        if (member == null) {
            return false;
        }
        member.setFullName(fullName);
        member.setRole(role);
        member.setDepartment(department);
        member.setPhoneNumber(phoneNumber);
        return true;
    }
}
