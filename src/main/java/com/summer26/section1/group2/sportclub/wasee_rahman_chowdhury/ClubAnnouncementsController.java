package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;

import com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid.Announcement;

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

    private ArrayList<Announcement> currentAnnouncements = new ArrayList<>();

    @FXML
    private void initialize() {
        announcementsListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> displayAnnouncement(newValue)
        );

        loadAnnouncements();
    }

    /*
     event-4: Load all announcements from the announcements file that are
     targeted at 'All' or 'Players' audience.
     */
    @FXML
    private void loadAnnouncements() {
        announcementsListView.getItems().clear();
        currentAnnouncements = new ArrayList<>();

        List<Announcement> allAnnouncements = Announcement.getAnnouncements();

        for (Announcement announcement : allAnnouncements) {
            String audience = announcement.getTargetAudience();

            if (audience.equals("All") || audience.equals("Players")) {
                currentAnnouncements.add(announcement);
                announcementsListView.getItems().add(announcement.getTitle());
            }
        }
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

        Announcement announcement = findAnnouncementByTitle(title);
        if (announcement == null) {
            return;
        }

        titleLabel.setText(announcement.getTitle());
        postedByLabel.setText(announcement.getPostedBy());
        postedDateLabel.setText(announcement.getPostedDate().toString());
        contentTextArea.setText(announcement.getContent());
    }

    /*
     * Search the currently loaded announcements for one matching given title.
     */
    private Announcement findAnnouncementByTitle(String title) {
        for (Announcement announcement : currentAnnouncements) {
            if (announcement.getTitle().equals(title)) {
                return announcement;
            }
        }

        return null;
    }
}
