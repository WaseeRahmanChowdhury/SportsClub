package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Feedback {
    private final String referenceNumber;
    private final String fanMembershipId;
    private final String feedbackType;
    private final String subject;
    private final String description;
    private final String relatedMatchId;
    private final LocalDateTime submittedAt;

    public Feedback(String referenceNumber, String fanMembershipId, String feedbackType, String subject,
                     String description, String relatedMatchId, LocalDateTime submittedAt) {
        this.referenceNumber = referenceNumber;
        this.fanMembershipId = fanMembershipId;
        this.feedbackType = feedbackType;
        this.subject = subject;
        this.description = description;
        this.relatedMatchId = relatedMatchId;
        this.submittedAt = submittedAt;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public String getFanMembershipId() {
        return fanMembershipId;
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public String getRelatedMatchId() {
        return relatedMatchId;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    // --- Feedback registry (all submitted feedback/complaints) ---

    private static final List<Feedback> feedbackEntries = new ArrayList<>();

    // event-8: save the feedback record with a timestamp and assign a reference number
    public static String submitFeedback(String fanMembershipId, String feedbackType, String subject,
                                         String description, String relatedMatchId) {
        String referenceNumber = String.format("FBK-%04d", feedbackEntries.size() + 1);
        feedbackEntries.add(new Feedback(referenceNumber, fanMembershipId, feedbackType, subject,
                description, relatedMatchId, LocalDateTime.now()));
        return referenceNumber;
    }

    public static List<Feedback> getFeedbackEntries() {
        return feedbackEntries;
    }
}
