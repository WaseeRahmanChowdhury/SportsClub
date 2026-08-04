package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid.Announcement;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class ManageAnnouncementsController {

    @FXML
    private TextField titleField;
    @FXML
    private ComboBox<String> targetAudienceCombo;
    @FXML
    private TextArea contentArea;
    @FXML
    private Label statusLabel;
    @FXML
    private TextArea announcementsDisplayArea;
    @FXML
    private TextField announcementIdField;

    private ArrayList<Announcement> currentAnnouncements = new ArrayList<>();
    private ArrayList<String> unpublishedIds = new ArrayList<>();

    @FXML
    private void initialize() {
        ArrayList<String> audiences = new ArrayList<>();
        audiences.add("All");
        audiences.add("Players");
        targetAudienceCombo.getItems().addAll(audiences);
        targetAudienceCombo.getSelectionModel().selectFirst();

        loadAnnouncementList();
    }

    /*
     * Checks whether the given announcement ID has been marked as unpublished
     * by this controller (separate from the shared Announcement class,
     * since that class cannot be modified). This tracking only lasts for
     * the current run of the program.
     */
    private boolean isPublished(String announcementId) {
        return !unpublishedIds.contains(announcementId);
    }

    /*
     * Refreshes the on-screen list with every announcement currently
     * stored in Announcement.bin, showing its ID, title, and published state.
     */
    private void loadAnnouncementList() {
        currentAnnouncements = new ArrayList<>();
        announcementsDisplayArea.clear();

        List<Announcement> allAnnouncements = Announcement.getAnnouncements();

        for (Announcement announcement : allAnnouncements) {
            currentAnnouncements.add(announcement);

            String publishedText;
            if (isPublished(announcement.getAnnouncementId())) {
                publishedText = "Published";
            } else {
                publishedText = "Unpublished";
            }

            announcementsDisplayArea.appendText(
                    announcement.getAnnouncementId() + " - "
                            + announcement.getTitle() + " - "
                            + publishedText + "\n"
            );
        }
    }

    @FXML
    private void postAnnouncement() {
        String title = titleField.getText();
        if (title == null || title.trim().isEmpty()) {
            statusLabel.setText("Title must not be empty.");
            return;
        }

        String content = contentArea.getText();
        if (content == null || content.trim().isEmpty()) {
            statusLabel.setText("Content must not be empty.");
            return;
        }

        String targetAudience = targetAudienceCombo.getValue();
        String postedBy = "Coach";

        String announcementId = Announcement.postAnnouncement(title, content, targetAudience, "Medium", postedBy);

        titleField.clear();
        contentArea.clear();

        statusLabel.setText("Announcement posted successfully. ID: " + announcementId);

        loadAnnouncementList();
    }

    @FXML
    private void togglePublishSelected() {
        String announcementId = announcementIdField.getText();

        if (announcementId == null || announcementId.trim().isEmpty()) {
            statusLabel.setText("Please enter an announcement ID.");
            return;
        }

        if (unpublishedIds.contains(announcementId)) {
            unpublishedIds.remove(announcementId);
        } else {
            unpublishedIds.add(announcementId);
        }

        announcementIdField.clear();
        statusLabel.setText("Announcement publish status updated.");

        loadAnnouncementList();
    }

    /*
     * Exports every announcement to a PDF file named Announcements.pdf
     * using the OpenPDF library.
     */
    @FXML
    private void exportToPdf() {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("Announcements.pdf"));
            document.open();

            for (Announcement announcement : currentAnnouncements) {
                String publishedText;
                if (isPublished(announcement.getAnnouncementId())) {
                    publishedText = "Published";
                } else {
                    publishedText = "Unpublished";
                }

                document.add(new Paragraph("Title: " + announcement.getTitle()));
                document.add(new Paragraph("Posted By: " + announcement.getPostedBy()));
                document.add(new Paragraph("Posted Date: " + announcement.getPostedDate()));
                document.add(new Paragraph("Status: " + publishedText));
                document.add(new Paragraph("Content: " + announcement.getContent()));
                document.add(new Paragraph(" "));
            }

            document.close();

            statusLabel.setText("Announcements exported to Announcements.pdf");

        } catch (DocumentException e) {
            e.printStackTrace();
            statusLabel.setText("Failed to create the PDF document.");
        } catch (java.io.IOException e) {
            e.printStackTrace();
            statusLabel.setText("Failed to write the PDF file.");
        }
    }
}
