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

public class Sponsor implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String sponsorId;
    private final String companyName;
    private final String contactPersonName;
    private final String contactNumber;
    private final double annualAmount;
    private final LocalDate contractStartDate;
    private final LocalDate contractEndDate;

    public Sponsor(String sponsorId, String companyName, String contactPersonName, String contactNumber,
                   double annualAmount, LocalDate contractStartDate, LocalDate contractEndDate) {
        this.sponsorId = sponsorId;
        this.companyName = companyName;
        this.contactPersonName = contactPersonName;
        this.contactNumber = contactNumber;
        this.annualAmount = annualAmount;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
    }

    public String getSponsorId() {
        return sponsorId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public double getAnnualAmount() {
        return annualAmount;
    }

    public LocalDate getContractStartDate() {
        return contractStartDate;
    }

    public LocalDate getContractEndDate() {
        return contractEndDate;
    }

    // --- Sponsor registry (all club sponsors) ---

    private static final String DATA_FILE = "Sponsor.bin";
    private static final List<Sponsor> sponsors = loadSponsors();

    @SuppressWarnings("unchecked")
    private static List<Sponsor> loadSponsors() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Sponsor>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static void saveSponsors() {
        File file = new File(DATA_FILE);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(sponsors);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // event-8: assign a Sponsor ID and save the new sponsor record
    public static String addSponsor(String companyName, String contactPersonName, String contactNumber,
                                    double annualAmount, LocalDate contractStartDate, LocalDate contractEndDate) {
        String sponsorId = String.format("SPN-%04d", sponsors.size() + 1);
        sponsors.add(new Sponsor(sponsorId, companyName, contactPersonName, contactNumber,
                annualAmount, contractStartDate, contractEndDate));
        saveSponsors();
        return sponsorId;
    }

    public static List<Sponsor> getSponsors() {
        return sponsors;
    }
}
