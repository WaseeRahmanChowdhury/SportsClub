package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class MyPerformanceStatsController {

    private static final String FILE_NAME = "PerformanceStat.bin";

    @javafx.fxml.FXML
    private TableView<PerformanceRow> statsTable;

    @javafx.fxml.FXML
    private TableColumn<PerformanceRow, Number> colAppearances;
    @javafx.fxml.FXML
    private TableColumn<PerformanceRow, Number> colGoals;
    @javafx.fxml.FXML
    private TableColumn<PerformanceRow, Number> colMinutes;

    @javafx.fxml.FXML
    private TextField appearancesField;
    @javafx.fxml.FXML
    private TextField goalsField;
    @javafx.fxml.FXML
    private TextField minutesField;

    private ArrayList<PerformanceRow> statsData = new ArrayList<>();

    public void initialize() {
        colAppearances.setCellValueFactory(new PropertyValueFactory<>("appearances"));
        colGoals.setCellValueFactory(new PropertyValueFactory<>("goals"));
        colMinutes.setCellValueFactory(new PropertyValueFactory<>("minutesPlayed"));

        loadPerformanceStats();
    }

    /*
     * Player enters stats for match and saves it.
     * The new stat is appended to the list already stored in bin file,
     * then whole list is written back to file.
     */
    @javafx.fxml.FXML
    public void saveStat(ActionEvent actionEvent) {
        int appearances = Integer.parseInt(appearancesField.getText());
        int goals = Integer.parseInt(goalsField.getText());
        int minutesPlayed = Integer.parseInt(minutesField.getText());

        ArrayList<PerformanceRow> currentStats = readStatsFromFile();
        currentStats.add(new PerformanceRow(appearances, goals, minutesPlayed));

        try (FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject(currentStats);

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        appearancesField.clear();
        goalsField.clear();
        minutesField.clear();

        loadPerformanceStats();
    }

    /*
     * event-5: Display performance statistics in a table.
     * Loads the stats saved so far from the bin file and shows them in the table.
     */
    private void loadPerformanceStats() {
        statsData = readStatsFromFile();
        statsTable.getItems().setAll(statsData);
    }

    private ArrayList<PerformanceRow> readStatsFromFile() {
        ArrayList<PerformanceRow> stats = new ArrayList<>();

        try (FileInputStream fileIn = new FileInputStream(FILE_NAME);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            stats = (ArrayList<PerformanceRow>) objectIn.readObject();

        }
        catch (EOFException e) {
        }
        catch (IOException e) {
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (ClassCastException e) {
            e.printStackTrace();
        }

        return stats;
    }


    //Reprresentation of one row of performance stats.

    public static class PerformanceRow implements Serializable {
        private int appearances;
        private int goals;
        private int minutesPlayed;

        public PerformanceRow() {
        }

        public PerformanceRow(int appearances, int goals, int minutesPlayed) {
            this.appearances = appearances;
            this.goals = goals;
            this.minutesPlayed = minutesPlayed;
        }

        public int getAppearances() {
            return appearances;
        }

        public void setAppearances(int appearances) {
            this.appearances = appearances;
        }

        public int getGoals() {
            return goals;
        }

        public void setGoals(int goals) {
            this.goals = goals;
        }

        public int getMinutesPlayed() {
            return minutesPlayed;
        }

        public void setMinutesPlayed(int minutesPlayed) {
            this.minutesPlayed = minutesPlayed;
        }
    }
}
