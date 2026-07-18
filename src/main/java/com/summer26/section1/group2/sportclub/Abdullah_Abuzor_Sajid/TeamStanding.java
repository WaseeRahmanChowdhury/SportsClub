package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

public class TeamStanding {
    private final String teamName;
    private int played;
    private int wins;
    private int draws;
    private int losses;
    private int goalsFor;
    private int goalsAgainst;

    public TeamStanding(String teamName) {
        this.teamName = teamName;
    }

    public void recordResult(int goalsScored, int goalsConceded) {
        played++;
        goalsFor += goalsScored;
        goalsAgainst += goalsConceded;
        if (goalsScored > goalsConceded) {
            wins++;
        } else if (goalsScored == goalsConceded) {
            draws++;
        } else {
            losses++;
        }
    }

    public String getTeamName() {
        return teamName;
    }

    public int getPlayed() {
        return played;
    }

    public int getWins() {
        return wins;
    }

    public int getDraws() {
        return draws;
    }

    public int getLosses() {
        return losses;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public int getGoalDifference() {
        return goalsFor - goalsAgainst;
    }

    // Win=3, Draw=1, Loss=0
    public int getPoints() {
        return wins * 3 + draws;
    }
}
