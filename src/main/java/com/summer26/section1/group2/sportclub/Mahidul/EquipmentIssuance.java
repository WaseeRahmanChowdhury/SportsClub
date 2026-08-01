package com.summer26.section1.group2.sportclub.Mahidul;

public class EquipmentIssuance {
    private String recipientID;
    private String itemID;
    private int issuedQuantity;

    public EquipmentIssuance(String recipientID, String itemID, int issuedQuantity) {
        this.recipientID = recipientID;
        this.itemID = itemID;
        this.issuedQuantity = issuedQuantity;
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

    public int getIssuedQuantity() {
        return issuedQuantity;
    }

    public void setIssuedQuantity(int issuedQuantity) {
        this.issuedQuantity = issuedQuantity;
    }

    @Override
    public String toString() {
        return "EquipmentIssuance{" +
                "recipientID='" + recipientID + '\'' +
                ", itemID='" + itemID + '\'' +
                ", issuedQuantity=" + issuedQuantity +
                '}';
    }
}
