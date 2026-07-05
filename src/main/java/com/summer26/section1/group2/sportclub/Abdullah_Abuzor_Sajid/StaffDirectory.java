package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import java.util.ArrayList;
import java.util.List;

public class StaffDirectory {

    private static final List<StaffMember> staffMembers = new ArrayList<>();

    private StaffDirectory() {
    }

    public static String registerStaffMember(String fullName, String role, String department, String phoneNumber) {
        String staffId = String.format("STF-%04d", staffMembers.size() + 1);
        staffMembers.add(new StaffMember(staffId, fullName, role, department, phoneNumber));
        return staffId;
    }

    public static List<StaffMember> getStaffMembers() {
        return staffMembers;
    }
}
