package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import java.time.LocalDate;

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
}
