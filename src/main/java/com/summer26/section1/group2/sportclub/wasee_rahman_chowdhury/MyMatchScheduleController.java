package com.summer26.section1.group2.sportclub.wasee_rahman_chowdhury;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class MyMatchScheduleController {

    private static final String FILE_NAME = "MatchSchedule.bin";

    @javafx.fxml.FXML
    private TableView<MatchRow> matchTable;
    @javafx.fxml.FXML
    private TableColumn<MatchRow, LocalDate> colDate;
    @javafx.fxml.FXML
    private TableColumn<MatchRow, String> colOpponent;
    @javafx.fxml.FXML
    private TableColumn<MatchRow, String> colVenue;
    @javafx.fxml.FXML
    private DatePicker datePicker;
    @javafx.fxml.FXML
    private TextField opponentField;
    @javafx.fxml.FXML
    private TextField venueField;

    private ArrayList<MatchRow> matchData = new ArrayList<>();

    public void initialize() {
        colDate.setCellValueFactory(new PropertyValueFactory<>("matchDate"));
        colOpponent.setCellValueFactory(new PropertyValueFactory<>("opponentClub"));
        colVenue.setCellValueFactory(new PropertyValueFactory<>("venue"));

        loadMatchSchedule(null);
    }

    @javafx.fxml.FXML
    public void saveMatch(ActionEvent actionEvent) {
        LocalDate date = datePicker.getValue();
        String opponent = opponentField.getText();
        String venue = venueField.getText();

        if (date == null || opponent.isEmpty() || venue.isEmpty()) {
            return;
        }

        ArrayList<MatchRow> currentMatches = readMatchesFromFile();
        currentMatches.add(new MatchRow(date, opponent, venue));

        try (FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject(currentMatches);

        } catch (IOException e) {
            e.printStackTrace();
        }

        datePicker.setValue(null);
        opponentField.clear();
        venueField.clear();

        loadMatchSchedule(null);
    }

    @javafx.fxml.FXML
    public void loadMatchSchedule(ActionEvent actionEvent) {
        matchData = readMatchesFromFile();
        matchTable.getItems().setAll(matchData);
    }

    private ArrayList<MatchRow> readMatchesFromFile() {
        ArrayList<MatchRow> matches = new ArrayList<>();

        try (FileInputStream fileIn = new FileInputStream(FILE_NAME);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            matches = (ArrayList<MatchRow>) objectIn.readObject();

        } catch (EOFException e) {
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        return matches;
    }

    public static class MatchRow implements Serializable {
        private LocalDate matchDate;
        private String opponentClub;
        private String venue;

        public MatchRow() {
        }

        public MatchRow(LocalDate matchDate, String opponentClub, String venue) {
            this.matchDate = matchDate;
            this.opponentClub = opponentClub;
            this.venue = venue;
        }

        public LocalDate getMatchDate() {
            return matchDate;
        }

        public void setMatchDate(LocalDate matchDate) {
            this.matchDate = matchDate;
        }

        public String getOpponentClub() {
            return opponentClub;
        }

        public void setOpponentClub(String opponentClub) {
            this.opponentClub = opponentClub;
        }

        public String getVenue() {
            return venue;
        }

        public void setVenue(String venue) {
            this.venue = venue;
        }
    }
}
