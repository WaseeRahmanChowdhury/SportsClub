package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MatchSchedule {

    private static final List<Match> matches = new ArrayList<>();

    private MatchSchedule() {
    }

    public static String scheduleMatch(LocalDate matchDate, String opponentClubName, String venueName) {
        String matchId = String.format("MCH-%04d", matches.size() + 1);
        matches.add(new Match(matchId, matchDate, opponentClubName, venueName));
        return matchId;
    }

    public static List<Match> getMatches() {
        return matches;
    }
}
