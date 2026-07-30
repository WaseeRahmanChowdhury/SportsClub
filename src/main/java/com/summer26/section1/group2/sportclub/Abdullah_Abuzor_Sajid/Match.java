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
import java.util.List;

public class Match implements Serializable {
    private static final long serialVersionUID = 1L;

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

    private static final String DATA_FILE = "Sajid_Data/Match.bin";
    private static final List<Match> matches = loadMatches();

    @SuppressWarnings("unchecked")
    private static List<Match> loadMatches() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Match>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static void saveMatches() {
        File file = new File(DATA_FILE);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(matches);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String scheduleMatch(LocalDate matchDate, String opponentClubName, String venueName) {
        String matchId = String.format("MCH-%04d", matches.size() + 1);
        matches.add(new Match(matchId, matchDate, opponentClubName, venueName));
        saveMatches();
        return matchId;
    }

    public static List<Match> getMatches() {
        return matches;
    }
}
