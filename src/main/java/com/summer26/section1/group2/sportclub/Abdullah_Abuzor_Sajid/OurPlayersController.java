package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class OurPlayersController {

    @FXML
    private ListView<PlayerProfile> playerListView;

    @FXML
    private Label nameLabel;
    @FXML
    private Label dobLabel;
    @FXML
    private Label nationalityLabel;
    @FXML
    private Label heightWeightLabel;
    @FXML
    private Label careerStatsLabel;
    @FXML
    private Label currentSeasonStatsLabel;
    @FXML
    private Label achievementsLabel;

    private final ObservableList<PlayerProfile> playerRows = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // event-4/5: fetch all active players and display the player gallery
        playerRows.setAll(PlayerProfile.getPlayers());
        playerListView.setItems(playerRows);

        playerListView.setCellFactory(new Callback<ListView<PlayerProfile>, ListCell<PlayerProfile>>() {
            @Override
            public ListCell<PlayerProfile> call(ListView<PlayerProfile> list) {
                return new ListCell<PlayerProfile>() {
                    @Override
                    protected void updateItem(PlayerProfile player, boolean empty) {
                        super.updateItem(player, empty);
                        if (empty || player == null) {
                            setText(null);
                        } else {
                            setText("#" + player.getSquadNumber() + "  " + player.getFullName()
                                    + "  (" + player.getPosition() + ", " + player.getNationality() + ")");
                        }
                    }
                };
            }
        });

        playerListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PlayerProfile>() {
            @Override
            public void changed(ObservableValue<? extends PlayerProfile> observable, PlayerProfile oldValue,
                                PlayerProfile newValue) {
                showProfile(newValue);
            }
        });
    }

    // event-7/8: fan clicks a player card, display full profile
    private void showProfile(PlayerProfile player) {
        if (player == null) {
            nameLabel.setText("");
            dobLabel.setText("");
            nationalityLabel.setText("");
            heightWeightLabel.setText("");
            careerStatsLabel.setText("");
            currentSeasonStatsLabel.setText("");
            achievementsLabel.setText("");
            return;
        }

        nameLabel.setText("#" + player.getSquadNumber() + "  " + player.getFullName() + " (" + player.getPosition() + ")");
        dobLabel.setText("DOB: " + player.getDateOfBirth());
        nationalityLabel.setText("Nationality: " + player.getNationality());
        heightWeightLabel.setText("Height: " + player.getHeightCm() + " cm   Weight: " + player.getWeightKg() + " kg");
        careerStatsLabel.setText("Career: " + player.getCareerMatches() + " matches, "
                + player.getCareerGoals() + " goals, " + player.getCareerAssists() + " assists");
        currentSeasonStatsLabel.setText("This Season: " + player.getCurrentSeasonMatches() + " matches, "
                + player.getCurrentSeasonGoals() + " goals");
        achievementsLabel.setText("Achievements: " + player.getAchievements());
    }
}
