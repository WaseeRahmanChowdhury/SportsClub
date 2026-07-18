package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MatchResult {
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

    // event-5: completed match results - seed data, no other feature currently populates real results.
    private static final List<MatchResult> results = new ArrayList<>(List.of(
            new MatchResult("RES-0001", COMPETITION_BPL, LocalDate.of(2026, 3, 6),
                    "Bashundhara Kings", "Abahani Limited", 3, 1,
                    "Bashundhara Kings Arena", "Mahmudul Islam", 15000, List.of(
                    new MatchEvent(12, "Goal", "Sabbir Ahmed (Home)"),
                    new MatchEvent(34, "Yellow Card", "Jamal Bhuiyan (Home)"),
                    new MatchEvent(51, "Goal", "Away Forward (Away)"),
                    new MatchEvent(67, "Goal", "Rakib Hossain (Home)"),
                    new MatchEvent(80, "Goal", "Sabbir Ahmed (Home)"))),

            new MatchResult("RES-0002", COMPETITION_BPL, LocalDate.of(2026, 3, 13),
                    "Mohammedan SC", "Bashundhara Kings", 0, 0,
                    "Bir Shreshtha Stadium", "Anisur Rahman", 8000, List.of(
                    new MatchEvent(29, "Yellow Card", "Emeka Obi (Away)"),
                    new MatchEvent(72, "Yellow Card", "Home Defender (Home)"))),

            new MatchResult("RES-0003", COMPETITION_BPL, LocalDate.of(2026, 3, 20),
                    "Bashundhara Kings", "Sheikh Russel KC", 2, 2,
                    "Bashundhara Kings Arena", "Kamrul Hasan", 12000, List.of(
                    new MatchEvent(8, "Goal", "Rakib Hossain (Home)"),
                    new MatchEvent(22, "Goal", "Away Forward (Away)"),
                    new MatchEvent(58, "Goal", "Emeka Obi (Home)"),
                    new MatchEvent(85, "Goal", "Away Midfielder (Away)"),
                    new MatchEvent(90, "Red Card", "Away Defender (Away)"))),

            new MatchResult("RES-0004", COMPETITION_BPL, LocalDate.of(2026, 3, 27),
                    "Abahani Limited", "Mohammedan SC", 1, 0,
                    "Bangabandhu National Stadium", "Zahid Hossain", 20000, List.of(
                    new MatchEvent(44, "Goal", "Home Striker (Home)"))),

            new MatchResult("RES-0005", COMPETITION_BPL, LocalDate.of(2026, 4, 3),
                    "Sheikh Russel KC", "Rahmatganj MFS", 1, 3,
                    "Sheikh Russel Stadium", "Nasir Uddin", 5000, List.of(
                    new MatchEvent(15, "Goal", "Away Striker (Away)"),
                    new MatchEvent(38, "Goal", "Home Forward (Home)"),
                    new MatchEvent(63, "Goal", "Away Striker (Away)"),
                    new MatchEvent(77, "Goal", "Away Midfielder (Away)"))),

            new MatchResult("RES-0006", COMPETITION_BPL, LocalDate.of(2026, 4, 10),
                    "Rahmatganj MFS", "Bashundhara Kings", 0, 2,
                    "Rahmatganj Stadium", "Faisal Ahmed", 6000, List.of(
                    new MatchEvent(40, "Goal", "Sabbir Ahmed (Away)"),
                    new MatchEvent(70, "Goal", "Rakib Hossain (Away)"))),

            new MatchResult("RES-0007", COMPETITION_FEDERATION_CUP, LocalDate.of(2026, 2, 18),
                    "Bashundhara Kings", "Chittagong Abahani", 4, 0,
                    "Bashundhara Kings Arena", "Mahmudul Islam", 10000, List.of(
                    new MatchEvent(10, "Goal", "Sabbir Ahmed (Home)"),
                    new MatchEvent(31, "Goal", "Emeka Obi (Home)"),
                    new MatchEvent(55, "Goal", "Rakib Hossain (Home)"),
                    new MatchEvent(89, "Goal", "Sabbir Ahmed (Home)"))),

            new MatchResult("RES-0008", COMPETITION_SUPER_CUP, LocalDate.of(2026, 1, 22),
                    "Abahani Limited", "Bashundhara Kings", 2, 1,
                    "Bangabandhu National Stadium", "Kamrul Hasan", 18000, List.of(
                    new MatchEvent(19, "Goal", "Home Forward (Home)"),
                    new MatchEvent(48, "Goal", "Sabbir Ahmed (Away)"),
                    new MatchEvent(84, "Goal", "Home Forward (Home)")))
    ));

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
