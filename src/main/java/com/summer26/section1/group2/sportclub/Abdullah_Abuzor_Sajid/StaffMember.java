package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StaffMember implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String staffId;
    private String fullName;
    private String role;
    private String department;
    private String phoneNumber;

    // No-arg constructor (JavaBean convention): fields start empty and get filled in via setters.
    public StaffMember() {
        this.staffId = null;
        this.fullName = null;
        this.role = null;
        this.department = null;
        this.phoneNumber = null;
    }

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

    private static final String DATA_FILE = "Sajid_Data/StaffMember.bin";
    private static final List<StaffMember> staffMembers = loadStaffMembers();

    @SuppressWarnings("unchecked")
    private static List<StaffMember> loadStaffMembers() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Object loaded = in.readObject();
            // Defensive check: only trust the deserialized data if it's really a List.
            if (loaded instanceof List) {
                return (List<StaffMember>) loaded;
            }
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static void saveStaffMembers() {
        File file = new File(DATA_FILE);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(staffMembers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String registerStaffMember(String fullName, String role, String department, String phoneNumber) {
        String staffId = String.format("STF-%04d", staffMembers.size() + 1);
        staffMembers.add(new StaffMember(staffId, fullName, role, department, phoneNumber));
        saveStaffMembers();
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
        saveStaffMembers();
        return true;
    }
}
