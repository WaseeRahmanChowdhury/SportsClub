package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Announcement {
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

    private static final List<Announcement> announcements = new ArrayList<>();

    // event-6: save the announcement with a timestamp, announcement ID, and the admin's user ID as author
    public static String postAnnouncement(String title, String content, String targetAudience,
                                          String priority, String postedBy) {
        String announcementId = String.format("ANN-%04d", announcements.size() + 1);
        announcements.add(new Announcement(announcementId, title, content, targetAudience,
                priority, postedBy, LocalDate.now()));
        return announcementId;
    }

    public static List<Announcement> getAnnouncements() {
        return announcements;
    }
}
