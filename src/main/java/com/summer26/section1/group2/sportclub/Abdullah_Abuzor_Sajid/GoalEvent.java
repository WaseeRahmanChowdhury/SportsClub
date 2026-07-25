package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

public class GoalEvent extends MatchEvent {
    private static final long serialVersionUID = 1L;

    public GoalEvent(int minute, String description) {
        super(minute, description);
    }

    @Override
    public String getEventLabel() {
        return "Goal";
    }
}
