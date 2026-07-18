package com.summer26.section1.group2.sportclub.Abdullah_Abuzor_Sajid;

import java.util.ArrayList;
import java.util.List;

public class MerchOrder {
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

    private static final List<MerchOrder> orders = new ArrayList<>();

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
        return order;
    }

    public static List<MerchOrder> getOrders() {
        return orders;
    }
}
