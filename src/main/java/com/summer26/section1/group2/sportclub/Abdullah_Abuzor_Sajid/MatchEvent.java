package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import java.io.Serializable;

public abstract class MatchEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    protected final int minute;
    protected final String description;

    public MatchEvent(int minute, String description) {
        this.minute = minute;
        this.description = description;
    }

    public int getMinute() {
        return minute;
    }

    public String getDescription() {
        return description;
    }

    public abstract String getEventLabel();

    @Override
    public String toString() {
        return minute + "' " + getEventLabel() + " - " + description;
    }
}
