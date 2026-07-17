package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.util.HashMap;
import java.util.Map;

public class ClubAnnouncementsController {

    @FXML
    private ListView<String> announcementsListView;

    @FXML
    private Label titleLabel;
    @FXML
    private Label postedByLabel;
    @FXML
    private Label postedDateLabel;
    @FXML
    private TextArea contentTextArea;

    private final ObservableList<String> announcementTitles = FXCollections.observableArrayList();

    //Maps announcement title to its full announcement details
    private final Map<String, Announcement> announcementsByTitle = new HashMap<>();

    @FXML
    private void initialize() {
        announcementsListView.setItems(announcementTitles);

        // event-5: player clicks on an announcement to read it
        announcementsListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> displayAnnouncement(newValue)
        );

        loadAnnouncements();
    }

    /*
     event-4: Load all announcements from the announcements file that are
     targeted at 'All' or 'Players' audience, sorted by date descending.
     Replace with real data source lookup.
     */
    private void loadAnnouncements() {
        announcementTitles.clear();
        announcementsByTitle.clear();
        // fill announcementTitles and announcementsByTitle from the announcements data file
        // filtered by audience ('All' or 'Players') and sorted by date descending
    }


     // event-6: Display full announcement content (title, posted by, posted date, body).

    private void displayAnnouncement(String title) {
        if (title == null) {
            titleLabel.setText("");
            postedByLabel.setText("");
            postedDateLabel.setText("");
            contentTextArea.setText("");
            return;
        }

        Announcement announcement = announcementsByTitle.get(title);
        if (announcement == null) {
            return;
        }

        titleLabel.setText(announcement.getTitle());
        postedByLabel.setText(announcement.getPostedBy());
        postedDateLabel.setText(announcement.getPostedDate());
        contentTextArea.setText(announcement.getContent());
    }


     // Simple single club announcement.
    public static class Announcement {
        private String title;
        private String postedBy;
        private String postedDate;
        private String content;
        private String audience;

        public Announcement() {
        }

        public Announcement(String title, String postedBy, String postedDate, String content, String audience) {
            this.title = title;
            this.postedBy = postedBy;
            this.postedDate = postedDate;
            this.content = content;
            this.audience = audience;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPostedBy() {
            return postedBy;
        }

        public void setPostedBy(String postedBy) {
            this.postedBy = postedBy;
        }

        public String getPostedDate() {
            return postedDate;
        }

        public void setPostedDate(String postedDate) {
            this.postedDate = postedDate;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

//        public String getAudience() {
//            return audience;
//        }
//
//        public void setAudience(String audience) {
//            this.audience = audience;
//        }
    }
}
