package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MerchandiseItem implements Serializable {
    private static final long serialVersionUID = 1L;

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

    private static final String DATA_FILE = "MerchandiseItem.bin";

    // Dummy/seed data - no feature creates real merchandise records.
    // Written to MerchandiseItem.bin once on first run, then loaded from that file on every run after.
    private static final List<MerchandiseItem> items = loadOrSeedItems();

    private static List<MerchandiseItem> loadOrSeedItems() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            List<MerchandiseItem> loaded = loadItems(file);
            if (loaded != null) {
                return loaded;
            }
        }
        List<MerchandiseItem> seedData = buildSeedItems();
        saveItems(seedData);
        return seedData;
    }

    @SuppressWarnings("unchecked")
    private static List<MerchandiseItem> loadItems(File file) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<MerchandiseItem>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void saveItems(List<MerchandiseItem> data) {
        File file = new File(DATA_FILE);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<MerchandiseItem> buildSeedItems() {
        return new ArrayList<>(List.of(
                new MerchandiseItem("MER-0001", "Home Jersey", "M", 2500.0, 40),
                new MerchandiseItem("MER-0002", "Home Jersey", "L", 2500.0, 35),
                new MerchandiseItem("MER-0003", "Away Jersey", "M", 2500.0, 25),
                new MerchandiseItem("MER-0004", "Away Jersey", "L", 2500.0, 20),
                new MerchandiseItem("MER-0005", "Club Scarf", "One Size", 500.0, 100),
                new MerchandiseItem("MER-0006", "Club Cap", "One Size", 450.0, 60)
        ));
    }

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
