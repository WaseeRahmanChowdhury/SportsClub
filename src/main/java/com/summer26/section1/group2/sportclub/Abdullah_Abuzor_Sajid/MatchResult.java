package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MatchResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String resultId;
    private final String competition;
    private final LocalDate matchDate;
    private final String homeTeam;
    private final String awayTeam;
    private final int homeScore;
    private final int awayScore;
    private final String venue;
    private final String referee;
    private final int attendance;
    private final List<MatchEvent> events;

    public MatchResult(String resultId, String competition, LocalDate matchDate, String homeTeam, String awayTeam,
                       int homeScore, int awayScore, String venue, String referee, int attendance,
                       List<MatchEvent> events) {
        this.resultId = resultId;
        this.competition = competition;
        this.matchDate = matchDate;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.venue = venue;
        this.referee = referee;
        this.attendance = attendance;
        this.events = events;
    }

    public String getResultId() {
        return resultId;
    }

    public String getCompetition() {
        return competition;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public String getVenue() {
        return venue;
    }

    public String getReferee() {
        return referee;
    }

    public int getAttendance() {
        return attendance;
    }

    public List<MatchEvent> getEvents() {
        return events;
    }

    public String getScoreLine() {
        return homeScore + " - " + awayScore;
    }

    // --- Match result registry (all completed match results + standings) ---

    public static final String OUR_CLUB = "Bashundhara Kings";

    public static final String COMPETITION_BPL = "Bangladesh Premier League";
    public static final String COMPETITION_FEDERATION_CUP = "Federation Cup";
    public static final String COMPETITION_SUPER_CUP = "Super Cup";

    private static final String DATA_FILE = "Sajid_Data/MatchResult.bin";

    // event-5: completed match results - dummy/seed data, no other feature currently populates real results.
    // Written to MatchResult.bin once on first run, then loaded from that file on every run after.
    private static final List<MatchResult> results = loadOrSeedResults();

    private static List<MatchResult> loadOrSeedResults() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            List<MatchResult> loaded = loadResults(file);
            if (loaded != null) {
                return loaded;
            }
        }
        List<MatchResult> seedData = buildSeedResults();
        saveResults(seedData);
        return seedData;
    }

    @SuppressWarnings("unchecked")
    private static List<MatchResult> loadResults(File file) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<MatchResult>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void saveResults(List<MatchResult> data) {
        File file = new File(DATA_FILE);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<MatchResult> buildSeedResults() {
        return new ArrayList<>(List.of(
                new MatchResult("RES-0001", COMPETITION_BPL, LocalDate.of(2026, 3, 6),
                        "Bashundhara Kings", "Abahani Limited", 3, 1,
                        "Bashundhara Kings Arena", "Mahmudul Islam", 15000, List.of(
                        new GoalEvent(12, "Sabbir Ahmed (Home)"),
                        new CardEvent(34, "Jamal Bhuiyan (Home)", "Yellow"),
                        new GoalEvent(51, "Away Forward (Away)"),
                        new GoalEvent(67, "Rakib Hossain (Home)"),
                        new GoalEvent(80, "Sabbir Ahmed (Home)"))),

                new MatchResult("RES-0002", COMPETITION_BPL, LocalDate.of(2026, 3, 13),
                        "Mohammedan SC", "Bashundhara Kings", 0, 0,
                        "Bir Shreshtha Stadium", "Anisur Rahman", 8000, List.of(
                        new CardEvent(29, "Emeka Obi (Away)", "Yellow"),
                        new CardEvent(72, "Home Defender (Home)", "Yellow"))),

                new MatchResult("RES-0003", COMPETITION_BPL, LocalDate.of(2026, 3, 20),
                        "Bashundhara Kings", "Sheikh Russel KC", 2, 2,
                        "Bashundhara Kings Arena", "Kamrul Hasan", 12000, List.of(
                        new GoalEvent(8, "Rakib Hossain (Home)"),
                        new GoalEvent(22, "Away Forward (Away)"),
                        new GoalEvent(58, "Emeka Obi (Home)"),
                        new GoalEvent(85, "Away Midfielder (Away)"),
                        new CardEvent(90, "Away Defender (Away)", "Red"))),

                new MatchResult("RES-0004", COMPETITION_BPL, LocalDate.of(2026, 3, 27),
                        "Abahani Limited", "Mohammedan SC", 1, 0,
                        "Bangabandhu National Stadium", "Zahid Hossain", 20000, List.of(
                        new GoalEvent(44, "Home Striker (Home)"))),

                new MatchResult("RES-0005", COMPETITION_BPL, LocalDate.of(2026, 4, 3),
                        "Sheikh Russel KC", "Rahmatganj MFS", 1, 3,
                        "Sheikh Russel Stadium", "Nasir Uddin", 5000, List.of(
                        new GoalEvent(15, "Away Striker (Away)"),
                        new GoalEvent(38, "Home Forward (Home)"),
                        new GoalEvent(63, "Away Striker (Away)"),
                        new GoalEvent(77, "Away Midfielder (Away)"))),

                new MatchResult("RES-0006", COMPETITION_BPL, LocalDate.of(2026, 4, 10),
                        "Rahmatganj MFS", "Bashundhara Kings", 0, 2,
                        "Rahmatganj Stadium", "Faisal Ahmed", 6000, List.of(
                        new GoalEvent(40, "Sabbir Ahmed (Away)"),
                        new GoalEvent(70, "Rakib Hossain (Away)"))),

                new MatchResult("RES-0007", COMPETITION_FEDERATION_CUP, LocalDate.of(2026, 2, 18),
                        "Bashundhara Kings", "Chittagong Abahani", 4, 0,
                        "Bashundhara Kings Arena", "Mahmudul Islam", 10000, List.of(
                        new GoalEvent(10, "Sabbir Ahmed (Home)"),
                        new GoalEvent(31, "Emeka Obi (Home)"),
                        new GoalEvent(55, "Rakib Hossain (Home)"),
                        new GoalEvent(89, "Sabbir Ahmed (Home)"))),

                new MatchResult("RES-0008", COMPETITION_SUPER_CUP, LocalDate.of(2026, 1, 22),
                        "Abahani Limited", "Bashundhara Kings", 2, 1,
                        "Bangabandhu National Stadium", "Kamrul Hasan", 18000, List.of(
                        new GoalEvent(19, "Home Forward (Home)"),
                        new GoalEvent(48, "Sabbir Ahmed (Away)"),
                        new GoalEvent(84, "Home Forward (Home)")))
        ));
    }

    // event-5/6: fetch all completed match results, optionally filtered by competition
    public static List<MatchResult> getResults(String competition) {
        if (competition == null || competition.isBlank()) {
            return new ArrayList<>(results);
        }
        List<MatchResult> filtered = new ArrayList<>();
        for (MatchResult result : results) {
            if (result.getCompetition().equals(competition)) {
                filtered.add(result);
            }
        }
        return filtered;
    }

    // event-6: compute standings for each team in the selected competition
    public static List<TeamStanding> computeStandings(String competition) {
        Map<String, TeamStanding> standingsByTeam = new LinkedHashMap<>();

        for (MatchResult result : getResults(competition)) {
            TeamStanding home = standingsByTeam.computeIfAbsent(result.getHomeTeam(), TeamStanding::new);
            TeamStanding away = standingsByTeam.computeIfAbsent(result.getAwayTeam(), TeamStanding::new);
            home.recordResult(result.getHomeScore(), result.getAwayScore());
            away.recordResult(result.getAwayScore(), result.getHomeScore());
        }

        List<TeamStanding> standings = new ArrayList<>(standingsByTeam.values());
        // event-7: sort by points descending, then goal difference as tiebreaker
        standings.sort((a, b) -> {
            if (b.getPoints() != a.getPoints()) {
                return b.getPoints() - a.getPoints();
            }
            return b.getGoalDifference() - a.getGoalDifference();
        });
        return standings;
    }

    // event-9: fetch a team's match results (won/lost/drawn) for the season
    public static List<MatchResult> getResultsForTeam(String teamName) {
        List<MatchResult> teamResults = new ArrayList<>();
        for (MatchResult result : results) {
            if (result.getHomeTeam().equals(teamName) || result.getAwayTeam().equals(teamName)) {
                teamResults.add(result);
            }
        }
        return teamResults;
    }
}
