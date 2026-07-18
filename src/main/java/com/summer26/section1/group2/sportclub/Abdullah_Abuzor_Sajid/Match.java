package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Match {
    private final String matchId;
    private final LocalDate matchDate;
    private final String opponentClubName;
    private final String venueName;

    public Match(String matchId, LocalDate matchDate, String opponentClubName, String venueName) {
        this.matchId = matchId;
        this.matchDate = matchDate;
        this.opponentClubName = opponentClubName;
        this.venueName = venueName;
    }

    public String getMatchId() {
        return matchId;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public String getOpponentClubName() {
        return opponentClubName;
    }

    public String getVenueName() {
        return venueName;
    }

    // --- Match schedule (all scheduled home matches) ---

    private static final List<Match> matches = new ArrayList<>();

    public static String scheduleMatch(LocalDate matchDate, String opponentClubName, String venueName) {
        String matchId = String.format("MCH-%04d", matches.size() + 1);
        matches.add(new Match(matchId, matchDate, opponentClubName, venueName));
        return matchId;
    }

    public static List<Match> getMatches() {
        return matches;
    }
}
