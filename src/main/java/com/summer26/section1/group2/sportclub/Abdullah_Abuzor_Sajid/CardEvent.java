package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

public class CardEvent extends MatchEvent {
    private static final long serialVersionUID = 1L;

    private final String cardColor;

    public CardEvent(int minute, String description, String cardColor) {
        super(minute, description);
        this.cardColor = cardColor;
    }

    public String getCardColor() {
        return cardColor;
    }

    @Override
    public String getEventLabel() {
        return cardColor + " Card";
    }
}
