package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

public class CategorySummary {
    private String category;
    private int itemSold;
    private String revenue;

    public CategorySummary(String category, int itemSold, String revenue) {
        this.category = category;
        this.itemSold = itemSold;
        this.revenue = revenue;
    }

    public String getCategory() {
        return category;
    }

    public int getItemSold() {
        return itemSold;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setItemSold(int itemSold) {
        this.itemSold = itemSold;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    @Override
    public String toString() {
        return "CategorySummary{" +
                "category='" + category + '\'' +
                ", itemSold=" + itemSold +
                ", revenue='" + revenue + '\'' +
                '}';
    }
}