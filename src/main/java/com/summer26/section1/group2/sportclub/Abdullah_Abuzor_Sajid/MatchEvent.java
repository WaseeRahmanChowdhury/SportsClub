package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

public class MatchEvent {
    private final int minute;
    private final String type;
    private final String description;

    public MatchEvent(int minute, String type, String description) {
        this.minute = minute;
        this.type = type;
        this.description = description;
    }

    public int getMinute() {
        return minute;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return minute + "' " + type + " - " + description;
    }
}
