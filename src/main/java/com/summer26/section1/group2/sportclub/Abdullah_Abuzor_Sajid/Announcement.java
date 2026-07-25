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

public class Announcement implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String announcementId;
    private final String title;
    private final String content;
    private final String targetAudience;
    private final String priority;
    private final String postedBy;
    private final LocalDate postedDate;

    public Announcement(String announcementId, String title, String content, String targetAudience,
                        String priority, String postedBy, LocalDate postedDate) {
        this.announcementId = announcementId;
        this.title = title;
        this.content = content;
        this.targetAudience = targetAudience;
        this.priority = priority;
        this.postedBy = postedBy;
        this.postedDate = postedDate;
    }

    public String getAnnouncementId() {
        return announcementId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public String getPriority() {
        return priority;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public LocalDate getPostedDate() {
        return postedDate;
    }

    // --- Announcement board (all posted announcements) ---

    private static final String DATA_FILE = "Announcement.bin";
    private static final List<Announcement> announcements = loadAnnouncements();

    @SuppressWarnings("unchecked")
    private static List<Announcement> loadAnnouncements() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Announcement>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static void saveAnnouncements() {
        File file = new File(DATA_FILE);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(announcements);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // event-6: save the announcement with a timestamp, announcement ID, and the admin's user ID as author
    public static String postAnnouncement(String title, String content, String targetAudience,
                                          String priority, String postedBy) {
        String announcementId = String.format("ANN-%04d", announcements.size() + 1);
        announcements.add(new Announcement(announcementId, title, content, targetAudience,
                priority, postedBy, LocalDate.now()));
        saveAnnouncements();
        return announcementId;
    }

    public static List<Announcement> getAnnouncements() {
        return announcements;
    }
}
