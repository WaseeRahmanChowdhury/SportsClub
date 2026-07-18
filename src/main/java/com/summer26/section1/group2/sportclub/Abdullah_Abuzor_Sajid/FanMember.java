package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FanMember {
    private final String fanMembershipId;
    private final String fullName;
    private final String email;
    private final String phoneNumber;
    private final LocalDate dateOfBirth;
    private final String favoritePlayerId;
    private final String temporaryPassword;

    public FanMember(String fanMembershipId, String fullName, String email, String phoneNumber,
                      LocalDate dateOfBirth, String favoritePlayerId, String temporaryPassword) {
        this.fanMembershipId = fanMembershipId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.favoritePlayerId = favoritePlayerId;
        this.temporaryPassword = temporaryPassword;
    }

    public String getFanMembershipId() {
        return fanMembershipId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getFavoritePlayerId() {
        return favoritePlayerId;
    }

    public String getTemporaryPassword() {
        return temporaryPassword;
    }

    // --- Fan membership registry (all registered fans) ---

    private static final List<FanMember> fanMembers = new ArrayList<>();
    private static final Random RANDOM = new Random();

    // event-6: verify the email address is not already registered
    public static boolean isEmailRegistered(String email) {
        for (FanMember fan : fanMembers) {
            if (fan.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    // event-7: assign a unique Fan Membership ID, generate a temporary password
    public static FanMember register(String fullName, String email, String phoneNumber,
                                      LocalDate dateOfBirth, String favoritePlayerId) {
        String fanMembershipId = String.format("FAN-%04d", fanMembers.size() + 1);
        String temporaryPassword = "Temp" + (1000 + RANDOM.nextInt(9000));

        FanMember fan = new FanMember(fanMembershipId, fullName, email, phoneNumber,
                dateOfBirth, favoritePlayerId, temporaryPassword);
        fanMembers.add(fan);
        return fan;
    }

    public static FanMember findByMembershipId(String fanMembershipId) {
        for (FanMember fan : fanMembers) {
            if (fan.getFanMembershipId().equals(fanMembershipId)) {
                return fan;
            }
        }
        return null;
    }

    public static List<FanMember> getFanMembers() {
        return fanMembers;
    }
}
