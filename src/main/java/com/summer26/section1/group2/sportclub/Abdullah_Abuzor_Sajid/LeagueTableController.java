package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.List;

public class LeagueTableController {

    @FXML
    private ComboBox<String> competitionCombo;
    @FXML
    private TableView<TeamStanding> standingsTable;
    @FXML
    private TableColumn<TeamStanding, String> teamColumn;
    @FXML
    private TableColumn<TeamStanding, Integer> playedColumn;
    @FXML
    private TableColumn<TeamStanding, Integer> winsColumn;
    @FXML
    private TableColumn<TeamStanding, Integer> drawsColumn;
    @FXML
    private TableColumn<TeamStanding, Integer> lossesColumn;
    @FXML
    private TableColumn<TeamStanding, Integer> goalsForColumn;
    @FXML
    private TableColumn<TeamStanding, Integer> goalsAgainstColumn;
    @FXML
    private TableColumn<TeamStanding, Integer> goalDiffColumn;
    @FXML
    private TableColumn<TeamStanding, Integer> pointsColumn;

    @FXML
    private Label fixtureHistoryLabel;
    @FXML
    private ListView<String> fixtureHistoryListView;
    @FXML
    private BarChart<String, Number> pointsChart;

    private final ObservableList<TeamStanding> standingsRows = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        competitionCombo.getItems().addAll(
                MatchResult.COMPETITION_BPL,
                MatchResult.COMPETITION_FEDERATION_CUP,
                MatchResult.COMPETITION_SUPER_CUP
        );
        competitionCombo.setValue(MatchResult.COMPETITION_BPL);

        teamColumn.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        playedColumn.setCellValueFactory(new PropertyValueFactory<>("played"));
        winsColumn.setCellValueFactory(new PropertyValueFactory<>("wins"));
        drawsColumn.setCellValueFactory(new PropertyValueFactory<>("draws"));
        lossesColumn.setCellValueFactory(new PropertyValueFactory<>("losses"));
        goalsForColumn.setCellValueFactory(new PropertyValueFactory<>("goalsFor"));
        goalsAgainstColumn.setCellValueFactory(new PropertyValueFactory<>("goalsAgainst"));
        goalDiffColumn.setCellValueFactory(new PropertyValueFactory<>("goalDifference"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));

        standingsTable.setItems(standingsRows);

        // event-7: highlight the club's own row
        standingsTable.setRowFactory(new Callback<TableView<TeamStanding>, TableRow<TeamStanding>>() {
            @Override
            public TableRow<TeamStanding> call(TableView<TeamStanding> table) {
                return new TableRow<TeamStanding>() {
                    @Override
                    protected void updateItem(TeamStanding standing, boolean empty) {
                        super.updateItem(standing, empty);
                        if (empty || standing == null) {
                            setStyle("");
                        } else if (standing.getTeamName().equals(MatchResult.OUR_CLUB)) {
                            setStyle("-fx-background-color: #d6ecff; -fx-font-weight: bold;");
                        } else {
                            setStyle("");
                        }
                    }
                };
            }
        });

        standingsTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TeamStanding>() {
            @Override
            public void changed(ObservableValue<? extends TeamStanding> observable, TeamStanding oldValue,
                                TeamStanding newValue) {
                showFixtureHistory(newValue);
            }
        });

        loadStandings();
    }

    // event-4: fan selects competition
    @FXML
    private void onCompetitionChanged() {
        loadStandings();
    }

    private void loadStandings() {
        standingsRows.setAll(MatchResult.computeStandings(competitionCombo.getValue()));
        fixtureHistoryListView.getItems().clear();
        fixtureHistoryLabel.setText("");
        updatePointsChart();
    }

    private void updatePointsChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Points");
        for (TeamStanding standing : standingsRows) {
            series.getData().add(new XYChart.Data<>(standing.getTeamName(), standing.getPoints()));
        }
        pointsChart.getData().setAll(series);
    }

    // event-8/9: fan clicks on a team name to view that team's fixture history
    private void showFixtureHistory(TeamStanding standing) {
        if (standing == null) {
            fixtureHistoryLabel.setText("");
            fixtureHistoryListView.getItems().clear();
            return;
        }

        fixtureHistoryLabel.setText("Fixture History: " + standing.getTeamName());

        List<MatchResult> teamResults = MatchResult.getResultsForTeam(standing.getTeamName());
        fixtureHistoryListView.getItems().clear();
        for (MatchResult result : teamResults) {
            String outcome = describeOutcome(result, standing.getTeamName());
            fixtureHistoryListView.getItems().add(result.getMatchDate() + "  " + result.getHomeTeam() + " "
                    + result.getScoreLine() + " " + result.getAwayTeam() + "  (" + outcome + ")");
        }
    }

    private String describeOutcome(MatchResult result, String teamName) {
        boolean isHome = result.getHomeTeam().equals(teamName);
        int scored = isHome ? result.getHomeScore() : result.getAwayScore();
        int conceded = isHome ? result.getAwayScore() : result.getHomeScore();
        if (scored > conceded) {
            return "Won";
        } else if (scored < conceded) {
            return "Lost";
        } else {
            return "Drawn";
        }
    }
}
