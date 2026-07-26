package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    public static final String TYPE_TRANSFER = "Transfer";

    private static final String DATA_FILE = "Sajid_Data/ActivityLog.txt";
    private static final List<ActivityLog> activities = loadActivities();

    private static List<ActivityLog> loadActivities() {
        List<ActivityLog> loaded = new ArrayList<>();
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return loaded;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                String[] parts = line.split("\\|", 4);
                if (parts.length < 4) {
                    continue;
                }
                loaded.add(new ActivityLog(LocalDateTime.parse(parts[0]), parts[1], parts[2], parts[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loaded;
    }

    // Appends one line for this activity instead of rewriting the whole file.
    private static void appendActivity(ActivityLog activity) {
        File file = new File(DATA_FILE);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        String line = activity.getActivityDate() + "|" + activity.getActivityType() + "|"
                + activity.getActivityTitle() + "|" + activity.getCreatedBy();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void log(String activityType, String activityTitle, String createdBy) {
        ActivityLog activity = new ActivityLog(LocalDateTime.now(), activityType, activityTitle, createdBy);
        activities.add(activity);
        appendActivity(activity);
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
