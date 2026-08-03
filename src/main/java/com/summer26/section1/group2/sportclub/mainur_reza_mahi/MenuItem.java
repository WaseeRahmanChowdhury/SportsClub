package com.summer26.section1.group2.sportclub.mainur_reza_mahi;

import java.io.Serializable;

public class MenuItem implements Serializable {
    private String itemId, itemName, category, availability;
    private double price;
    private int stockQty;

    public MenuItem(String itemId, String itemName, String category, String availability, double price, int stockQty) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.category = category;
        this.availability = availability;
        this.price = price;
        this.stockQty = stockQty;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQty() {
        return stockQty;
    }

    public void setStockQty(int stockQty) {
        this.stockQty = stockQty;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", category='" + category + '\'' +
                ", availability='" + availability + '\'' +
                ", price=" + price +
                ", stockQty=" + stockQty +
                '}';
    }
}