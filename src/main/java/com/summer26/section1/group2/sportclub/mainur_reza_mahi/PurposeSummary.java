package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

public class PurposeSummary {
    private String purpose;
    private int count;
    private String percentage;

    public PurposeSummary(String purpose, int count, String percentage) {
        this.purpose = purpose;
        this.count = count;
        this.percentage = percentage;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}
