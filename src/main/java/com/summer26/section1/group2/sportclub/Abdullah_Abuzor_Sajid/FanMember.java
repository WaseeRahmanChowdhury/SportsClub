package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FanMember implements Serializable {
    private static final long serialVersionUID = 1L;

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

    private static final String DATA_FILE = "FanMember.bin";
    private static final List<FanMember> fanMembers = loadFanMembers();
    private static final Random RANDOM = new Random();

    @SuppressWarnings("unchecked")
    private static List<FanMember> loadFanMembers() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<FanMember>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static void saveFanMembers() {
        File file = new File(DATA_FILE);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(fanMembers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        saveFanMembers();
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

    // Find My Membership ID lookup: match by email + phone number together
    public static FanMember findByEmailAndPhone(String email, String phoneNumber) {
        for (FanMember fan : fanMembers) {
            if (fan.getEmail().equalsIgnoreCase(email) && fan.getPhoneNumber().equals(phoneNumber)) {
                return fan;
            }
        }
        return null;
    }

    public static List<FanMember> getFanMembers() {
        return fanMembers;
    }
}
