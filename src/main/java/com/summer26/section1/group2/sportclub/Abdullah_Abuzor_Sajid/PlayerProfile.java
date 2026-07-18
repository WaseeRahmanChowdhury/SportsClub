package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlayerProfile {
    private final String playerId;
    private final String fullName;
    private final int squadNumber;
    private final String position;
    private final String nationality;
    private final LocalDate dateOfBirth;
    private final int heightCm;
    private final int weightKg;
    private final int careerMatches;
    private final int careerGoals;
    private final int careerAssists;
    private final int currentSeasonMatches;
    private final int currentSeasonGoals;
    private final String achievements;

    public PlayerProfile(String playerId, String fullName, int squadNumber, String position, String nationality,
                          LocalDate dateOfBirth, int heightCm, int weightKg, int careerMatches, int careerGoals,
                          int careerAssists, int currentSeasonMatches, int currentSeasonGoals, String achievements) {
        this.playerId = playerId;
        this.fullName = fullName;
        this.squadNumber = squadNumber;
        this.position = position;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
        this.heightCm = heightCm;
        this.weightKg = weightKg;
        this.careerMatches = careerMatches;
        this.careerGoals = careerGoals;
        this.careerAssists = careerAssists;
        this.currentSeasonMatches = currentSeasonMatches;
        this.currentSeasonGoals = currentSeasonGoals;
        this.achievements = achievements;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getFullName() {
        return fullName;
    }

    public int getSquadNumber() {
        return squadNumber;
    }

    public String getPosition() {
        return position;
    }

    public String getNationality() {
        return nationality;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public int getHeightCm() {
        return heightCm;
    }

    public int getWeightKg() {
        return weightKg;
    }

    public int getCareerMatches() {
        return careerMatches;
    }

    public int getCareerGoals() {
        return careerGoals;
    }

    public int getCareerAssists() {
        return careerAssists;
    }

    public int getCurrentSeasonMatches() {
        return currentSeasonMatches;
    }

    public int getCurrentSeasonGoals() {
        return currentSeasonGoals;
    }

    public String getAchievements() {
        return achievements;
    }

    // --- Player profile directory (public player profiles) ---

    // event-4: fetch all active players' public profile data
    // Seed data - no other feature currently populates real player records.
    private static final List<PlayerProfile> players = new ArrayList<>(List.of(
            new PlayerProfile("PLY-000001", "Rakib Hossain", 10, "MID", "Bangladesh",
                    LocalDate.of(1998, 4, 12), 175, 68, 142, 28, 34, 18, 6,
                    "BPL Top Assist Provider 2024"),
            new PlayerProfile("PLY-000002", "Sabbir Ahmed", 9, "FWD", "Bangladesh",
                    LocalDate.of(1999, 9, 3), 180, 74, 130, 61, 20, 19, 12,
                    "BPL Top Scorer 2023"),
            new PlayerProfile("PLY-000003", "Jamal Bhuiyan", 4, "DEF", "Bangladesh",
                    LocalDate.of(1990, 11, 25), 183, 79, 210, 8, 5, 17, 1,
                    "Club Captain, 200+ appearances"),
            new PlayerProfile("PLY-000004", "Anisur Rahman", 1, "GK", "Bangladesh",
                    LocalDate.of(1995, 2, 17), 188, 82, 165, 0, 0, 18, 0,
                    "Golden Glove BPL 2022"),
            new PlayerProfile("PLY-000005", "Emeka Obi", 7, "FWD", "Nigeria",
                    LocalDate.of(1997, 6, 30), 178, 72, 95, 47, 15, 18, 14,
                    "Federation Cup Top Scorer 2024")
    ));

    public static List<PlayerProfile> getPlayers() {
        return players;
    }

    public static PlayerProfile findByPlayerId(String playerId) {
        for (PlayerProfile player : players) {
            if (player.getPlayerId().equals(playerId)) {
                return player;
            }
        }
        return null;
    }
}
