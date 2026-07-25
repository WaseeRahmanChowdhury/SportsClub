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

public class MerchOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String orderId;
    private final String fanMembershipId;
    private final String itemName;
    private final String size;
    private final int quantity;
    private final double totalAmount;
    private final String deliveryAddress;
    private final String paymentMethod;

    public MerchOrder(String orderId, String fanMembershipId, String itemName, String size, int quantity,
                      double totalAmount, String deliveryAddress, String paymentMethod) {
        this.orderId = orderId;
        this.fanMembershipId = fanMembershipId;
        this.itemName = itemName;
        this.size = size;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.deliveryAddress = deliveryAddress;
        this.paymentMethod = paymentMethod;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getFanMembershipId() {
        return fanMembershipId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getSize() {
        return size;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    // --- Merch order registry (all placed club shop orders) ---

    private static final String DATA_FILE = "MerchOrder.bin";
    private static final List<MerchOrder> orders = loadOrders();

    @SuppressWarnings("unchecked")
    private static List<MerchOrder> loadOrders() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<MerchOrder>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static void saveOrders() {
        File file = new File(DATA_FILE);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(orders);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // event-11: process payment, deduct items from inventory, save order record
    public static MerchOrder placeOrder(String fanMembershipId, MerchandiseItem item, int quantity,
                                        String deliveryAddress, String paymentMethod) {
        if (quantity > item.getStockQuantity()) {
            return null;
        }
        item.setStockQuantity(item.getStockQuantity() - quantity);

        double totalAmount = item.getPrice() * quantity;
        String orderId = String.format("ORD-%04d", orders.size() + 1);
        MerchOrder order = new MerchOrder(orderId, fanMembershipId, item.getName(), item.getSize(), quantity,
                totalAmount, deliveryAddress, paymentMethod);
        orders.add(order);
        saveOrders();
        return order;
    }

    public static List<MerchOrder> getOrders() {
        return orders;
    }
}
