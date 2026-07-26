package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Feedback implements Serializable {
    private static final long serialVersionUID = 1L;

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

    private static final String DATA_FILE = "Sajid_Data/Feedback.bin";
    private static final List<Feedback> feedbackEntries = loadFeedbackEntries();

    @SuppressWarnings("unchecked")
    private static List<Feedback> loadFeedbackEntries() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Feedback>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static void saveFeedbackEntries() {
        File file = new File(DATA_FILE);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(feedbackEntries);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // event-8: save the feedback record with a timestamp and assign a reference number
    public static String submitFeedback(String fanMembershipId, String feedbackType, String subject,
                                        String description, String relatedMatchId) {
        String referenceNumber = String.format("FBK-%04d", feedbackEntries.size() + 1);
        feedbackEntries.add(new Feedback(referenceNumber, fanMembershipId, feedbackType, subject,
                description, relatedMatchId, LocalDateTime.now()));
        saveFeedbackEntries();
        return referenceNumber;
    }

    public static List<Feedback> getFeedbackEntries() {
        return feedbackEntries;
    }
}
