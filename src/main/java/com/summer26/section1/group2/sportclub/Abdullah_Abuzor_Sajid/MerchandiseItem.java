package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import java.util.ArrayList;
import java.util.List;

public class MerchandiseItem {
    private final String itemId;
    private final String name;
    private final String size;
    private final double price;
    private int stockQuantity;

    public MerchandiseItem(String itemId, String name, String size, double price, int stockQuantity) {
        this.itemId = itemId;
        this.name = name;
        this.size = size;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    @Override
    public String toString() {
        return name + " (" + size + ") - " + price + " BDT [" + stockQuantity + " in stock]";
    }

    // --- Merchandise catalog (all club shop items) ---

    // Seed data - no other feature currently populates the merchandise catalog.
    private static final List<MerchandiseItem> items = new ArrayList<>(List.of(
            new MerchandiseItem("MER-0001", "Home Jersey", "M", 2500.0, 40),
            new MerchandiseItem("MER-0002", "Home Jersey", "L", 2500.0, 35),
            new MerchandiseItem("MER-0003", "Away Jersey", "M", 2500.0, 25),
            new MerchandiseItem("MER-0004", "Away Jersey", "L", 2500.0, 20),
            new MerchandiseItem("MER-0005", "Club Scarf", "One Size", 500.0, 100),
            new MerchandiseItem("MER-0006", "Club Cap", "One Size", 450.0, 60)
    ));

    public static List<MerchandiseItem> getItems() {
        return items;
    }

    public static MerchandiseItem findByItemId(String itemId) {
        for (MerchandiseItem item : items) {
            if (item.getItemId().equals(itemId)) {
                return item;
            }
        }
        return null;
    }
}
