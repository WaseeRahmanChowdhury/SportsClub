//package com.summer26.section1.group2.sportclub.Mahidul;

//import java.time.LocalDate;

package com.summer26.section1.group2.sportclub.Mahidul;

import java.io.Serializable;
import java.time.LocalDate;

public class Equipment implements Serializable {

    private static final long serialVersionUID = 1L;

    // Inventory Information
    private String itemID;
    private String itemName;
    private String category;
    private int quantity;
    private double unitCost;
    private String supplier;
    private LocalDate purchaseDate;

    // Equipment Issue Information
    private String recipientID;

    // Equipment Request Information
    private int quantityNeeded;
    private double estimatedUnitCost;
    private String reason;
    private String urgencyLevel;

    // Maintenance Information
    private LocalDate maintenanceDate;
    private String maintenanceType;
    private String description;
    private double maintenanceCost;
    private String technicianName;

    public Equipment(String itemName, int quantity, double cost, String reason, String urgency) {
    }

    public Equipment(String itemID,
                     String itemName,
                     String category,
                     int quantity,
                     double unitCost,
                     String supplier,
                     LocalDate purchaseDate,
                     String recipientID,
                     int quantityNeeded,
                     double estimatedUnitCost,
                     String reason,
                     String urgencyLevel,
                     LocalDate maintenanceDate,
                     String maintenanceType,
                     String description,
                     double maintenanceCost,
                     String technicianName) {

        this.itemID = itemID;
        this.itemName = itemName;
        this.category = category;
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.supplier = supplier;
        this.purchaseDate = purchaseDate;

        this.recipientID = recipientID;

        this.quantityNeeded = quantityNeeded;
        this.estimatedUnitCost = estimatedUnitCost;
        this.reason = reason;
        this.urgencyLevel = urgencyLevel;

        this.maintenanceDate = maintenanceDate;
        this.maintenanceType = maintenanceType;
        this.description = description;
        this.maintenanceCost = maintenanceCost;
        this.technicianName = technicianName;
    }

    // Inventory Getters and Setters

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    // Issue Getters and Setters

    public String getRecipientID() {
        return recipientID;
    }

    public void setRecipientID(String recipientID) {
        this.recipientID = recipientID;
    }

    // Request Getters and Setters

    public int getQuantityNeeded() {
        return quantityNeeded;
    }

    public void setQuantityNeeded(int quantityNeeded) {
        this.quantityNeeded = quantityNeeded;
    }

    public double getEstimatedUnitCost() {
        return estimatedUnitCost;
    }

    public void setEstimatedUnitCost(double estimatedUnitCost) {
        this.estimatedUnitCost = estimatedUnitCost;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUrgencyLevel() {
        return urgencyLevel;
    }

    public void setUrgencyLevel(String urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }

    // Maintenance Getters and Setters

    public LocalDate getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(LocalDate maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public String getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(String maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMaintenanceCost() {
        return maintenanceCost;
    }

    public void setMaintenanceCost(double maintenanceCost) {
        this.maintenanceCost = maintenanceCost;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    // Utility Method

    public boolean isMaintenanceOverdue() {
        return maintenanceDate != null &&
                maintenanceDate.isBefore(LocalDate.now());
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "itemID='" + itemID + '\'' +
                ", itemName='" + itemName + '\'' +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                ", unitCost=" + unitCost +
                ", supplier='" + supplier + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", recipientID='" + recipientID + '\'' +
                ", quantityNeeded=" + quantityNeeded +
                ", estimatedUnitCost=" + estimatedUnitCost +
                ", reason='" + reason + '\'' +
                ", urgencyLevel='" + urgencyLevel + '\'' +
                ", maintenanceDate=" + maintenanceDate +
                ", maintenanceType='" + maintenanceType + '\'' +
                ", description='" + description + '\'' +
                ", maintenanceCost=" + maintenanceCost +
                ", technicianName='" + technicianName + '\'' +
                '}';
    }
}