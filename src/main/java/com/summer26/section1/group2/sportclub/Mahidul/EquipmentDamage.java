package com.summer26.section1.group2.sportclub.Mahidul;

public class EquipmentDamage {
    private static final long serialVersionUID = 1L;
    private String recipientID;
    private String itemID;
    private int quantity;
    private String condition;

    public EquipmentDamage(String recipientID,
                           String itemID,
                           int quantity,
                           String condition) {

        this.recipientID = recipientID;
        this.itemID = itemID;
        this.quantity = quantity;
        this.condition = condition;
    }

    public String getRecipientID() {
        return recipientID;
    }

    public void setRecipientID(String recipientID) {
        this.recipientID = recipientID;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "EquipmentDamage{" +
                "recipientID='" + recipientID + '\'' +
                ", itemID='" + itemID + '\'' +
                ", quantity=" + quantity +
                ", condition='" + condition + '\'' +
                '}';
    }
}
