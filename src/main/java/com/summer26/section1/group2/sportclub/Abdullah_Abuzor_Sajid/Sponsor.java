package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Sponsor {
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

    private static final List<Sponsor> sponsors = new ArrayList<>();

    // event-8: assign a Sponsor ID and save the new sponsor record
    public static String addSponsor(String companyName, String contactPersonName, String contactNumber,
                                     double annualAmount, LocalDate contractStartDate, LocalDate contractEndDate) {
        String sponsorId = String.format("SPN-%04d", sponsors.size() + 1);
        sponsors.add(new Sponsor(sponsorId, companyName, contactPersonName, contactNumber,
                annualAmount, contractStartDate, contractEndDate));
        return sponsorId;
    }

    public static List<Sponsor> getSponsors() {
        return sponsors;
    }
}
