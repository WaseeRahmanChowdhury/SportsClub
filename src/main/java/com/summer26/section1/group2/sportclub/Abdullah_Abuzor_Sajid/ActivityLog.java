package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ActivityLog {
    private final LocalDateTime activityDate;
    private final String activityType;
    private final String activityTitle;
    private final String createdBy;

    public ActivityLog(LocalDateTime activityDate, String activityType, String activityTitle, String createdBy) {
        this.activityDate = activityDate;
        this.activityType = activityType;
        this.activityTitle = activityTitle;
        this.createdBy = createdBy;
    }

    public LocalDateTime getActivityDate() {
        return activityDate;
    }

    public String getActivityType() {
        return activityType;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    // --- Activity log (all recorded club activity) ---

    // event-4: filter options for activity type
    public static final String TYPE_ALL = "All Activities";
    public static final String TYPE_MATCH = "Match";
    public static final String TYPE_TRAINING = "Training";
    public static final String TYPE_STAFF = "Staff";
    public static final String TYPE_ANNOUNCEMENT = "Announcement";
    public static final String TYPE_SPONSORSHIP = "Sponsorship";

    private static final List<ActivityLog> activities = new ArrayList<>();

    public static void log(String activityType, String activityTitle, String createdBy) {
        activities.add(new ActivityLog(LocalDateTime.now(), activityType, activityTitle, createdBy));
    }

    // event-6: load matching activity records for the selected activity type
    public static List<ActivityLog> getActivities(String activityType) {
        if (activityType == null || activityType.isBlank() || activityType.equals(TYPE_ALL)) {
            return new ArrayList<>(activities);
        }
        List<ActivityLog> results = new ArrayList<>();
        for (ActivityLog activity : activities) {
            if (activity.getActivityType().equals(activityType)) {
                results.add(activity);
            }
        }
        return results;
    }

    // event-8: search by activity title or created-by name
    public static List<ActivityLog> search(List<ActivityLog> source, String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return source;
        }
        String needle = keyword.trim().toLowerCase();
        List<ActivityLog> results = new ArrayList<>();
        for (ActivityLog activity : source) {
            if (activity.getActivityTitle().toLowerCase().contains(needle)
                    || activity.getCreatedBy().toLowerCase().contains(needle)) {
                results.add(activity);
            }
        }
        return results;
    }
}
