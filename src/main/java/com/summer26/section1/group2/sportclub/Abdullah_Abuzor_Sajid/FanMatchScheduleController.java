package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.List;

public class FanMatchScheduleController {

    @FXML
    private DatePicker fromDateField;
    @FXML
    private DatePicker toDateField;
    @FXML
    private TableView<Match> matchTable;
    @FXML
    private TableColumn<Match, String> dateColumn;
    @FXML
    private TableColumn<Match, String> opponentColumn;
    @FXML
    private TableColumn<Match, String> venueColumn;

    private final ObservableList<Match> matchRows = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("matchDate"));
        opponentColumn.setCellValueFactory(new PropertyValueFactory<>("opponentClubName"));
        venueColumn.setCellValueFactory(new PropertyValueFactory<>("venueName"));

        matchTable.setItems(matchRows);

        // event-4/5: fetch and display all upcoming fixtures
        matchRows.setAll(Match.getMatches());
    }

    // event-6/7: fan selects a date range filter, re-fetch and display the filtered fixture list
    @FXML
    private void applyFilter() {
        LocalDate from = fromDateField.getValue();
        LocalDate to = toDateField.getValue();

        List<Match> all = Match.getMatches();
        matchRows.clear();
        for (Match match : all) {
            if (from != null && match.getMatchDate().isBefore(from)) {
                continue;
            }
            if (to != null && match.getMatchDate().isAfter(to)) {
                continue;
            }
            matchRows.add(match);
        }
    }

    @FXML
    private void clearFilter() {
        fromDateField.setValue(null);
        toDateField.setValue(null);
        matchRows.setAll(Match.getMatches());
    }
}
