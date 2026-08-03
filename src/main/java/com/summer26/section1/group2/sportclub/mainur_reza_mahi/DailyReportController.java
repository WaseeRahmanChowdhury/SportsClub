package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DailyReportController
{
    @javafx.fxml.FXML
    private TableColumn<PurposeSummary,String> percentageTC;
    @javafx.fxml.FXML
    private Label commonPurposeLabel;
    @javafx.fxml.FXML
    private TableView<PurposeSummary> purposeBreakdownTC;
    @javafx.fxml.FXML
    private Label currentlyInsideLabel;
    @javafx.fxml.FXML
    private TableColumn<PurposeSummary,Integer> countTC;
    @javafx.fxml.FXML
    private Label totalLeftLabel;
    @javafx.fxml.FXML
    private Label totalVisitorsLabel;
    @javafx.fxml.FXML
    private TableColumn<PurposeSummary,String> purposeTC;
    @javafx.fxml.FXML
    private DatePicker reportDateDP;

    private static final String FILE_NAME = "visitors.bin";
    private ArrayList<Visitor> visitorList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        purposeTC.setCellValueFactory(new PropertyValueFactory<>("purpose"));
        countTC.setCellValueFactory(new PropertyValueFactory<>("count"));
        percentageTC.setCellValueFactory(new PropertyValueFactory<>("percentage"));
    }

    @javafx.fxml.FXML
    public void generateReportButtonOA(ActionEvent actionEvent) {

        // Step 1: check a date was picked
        if (reportDateDP.getValue() == null) {
            return;
        }

        // Step 2: load the visitor list from file
        visitorList.clear();

        File file = new File(FILE_NAME);
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(FILE_NAME);
                ObjectInputStream ois = new ObjectInputStream(fis);
                visitorList = (ArrayList<Visitor>) ois.readObject();
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Step 3: convert picked date to text
        String selectedDate = reportDateDP.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        // Step 4: simple counters, one variable per thing we're counting
        int totalVisitors = 0;
        int currentlyInside = 0;
        int totalLeft = 0;
        int meetingCount = 0;
        int deliveryCount = 0;
        int trainingCount = 0;
        int otherCount = 0;

        // Step 5: go through every visitor and count matching ones
        for (Visitor v : visitorList) {
            if (v.getVisitDate().equals(selectedDate)) {

                totalVisitors++;

                if (v.getStatus().equals("inside")) {
                    currentlyInside++;
                }
                if (v.getStatus().equals("left")) {
                    totalLeft++;
                }

                if (v.getPurpose().equals("Meeting")) {
                    meetingCount++;
                }
                if (v.getPurpose().equals("Delivery")) {
                    deliveryCount++;
                }
                if (v.getPurpose().equals("Training")) {
                    trainingCount++;
                }
                if (v.getPurpose().equals("Other")) {
                    otherCount++;
                }
            }
        }

        // Step 6: update the top labels
        totalVisitorsLabel.setText(String.valueOf(totalVisitors));
        currentlyInsideLabel.setText(String.valueOf(currentlyInside));
        totalLeftLabel.setText(String.valueOf(totalLeft));

        // Step 7: find the most common purpose using simple if-else comparisons
        String mostCommonPurpose = "Meeting";
        int highestCount = meetingCount;

        if (deliveryCount > highestCount) {
            mostCommonPurpose = "Delivery";
            highestCount = deliveryCount;
        }
        if (trainingCount > highestCount) {
            mostCommonPurpose = "Training";
            highestCount = trainingCount;
        }
        if (otherCount > highestCount) {
            mostCommonPurpose = "Other";
            highestCount = otherCount;
        }

        commonPurposeLabel.setText(mostCommonPurpose);

        // Step 8: calculate percentage for each purpose
        double meetingPercent = (meetingCount * 100.0) / totalVisitors;
        double deliveryPercent = (deliveryCount * 100.0) / totalVisitors;
        double trainingPercent = (trainingCount * 100.0) / totalVisitors;
        double otherPercent = (otherCount * 100.0) / totalVisitors;

        // Step 9: build the table rows manually, one line per purpose
        ArrayList<PurposeSummary> summaryList = new ArrayList<>();
        summaryList.add(new PurposeSummary("Meeting", meetingCount, String.format("%.1f%%", meetingPercent)));
        summaryList.add(new PurposeSummary("Delivery", deliveryCount, String.format("%.1f%%", deliveryPercent)));
        summaryList.add(new PurposeSummary("Training", trainingCount, String.format("%.1f%%", trainingPercent)));
        summaryList.add(new PurposeSummary("Other", otherCount, String.format("%.1f%%", otherPercent)));

        purposeBreakdownTC.getItems().clear();
        purposeBreakdownTC.getItems().addAll(summaryList);
    }
}