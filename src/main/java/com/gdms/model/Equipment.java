package com.gdms.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents gym equipment in the system.
 * Manages equipment details, status, and maintenance schedule.
 */
public class Equipment {
    private int equipmentId;
    private String name;
    private String status; // Available, In Use, Under Maintenance, Out of Order
    private LocalDate lastMaintenanceDate;
    private LocalDate nextMaintenanceDate;
    private String location;
    private String description;

    /**
     * Constructor for creating new equipment.
     * @param equipmentId Unique identifier for the equipment
     * @param name Name of the equipment
     * @param status Current status
     * @param location Location in the gym
     * @param description Detailed description
     */
    public Equipment(int equipmentId, String name, String status, String location, String description) {
        this.equipmentId = equipmentId;
        this.name = name;
        this.status = status;
        this.location = location;
        this.description = description;
        this.lastMaintenanceDate = LocalDate.now();
        this.nextMaintenanceDate = LocalDate.now().plusMonths(1); // Default maintenance interval
    }

    // Getters and Setters
    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }

    public void setLastMaintenanceDate(LocalDate lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    public LocalDate getNextMaintenanceDate() {
        return nextMaintenanceDate;
    }

    public void setNextMaintenanceDate(LocalDate nextMaintenanceDate) {
        this.nextMaintenanceDate = nextMaintenanceDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Records maintenance performed on the equipment.
     * @param nextMaintenanceInterval Number of months until next maintenance
     */
    public void performMaintenance(int nextMaintenanceInterval) {
        this.lastMaintenanceDate = LocalDate.now();
        this.nextMaintenanceDate = LocalDate.now().plusMonths(nextMaintenanceInterval);
        this.status = "Available";
    }

    /**
     * Checks if maintenance is due.
     * @return true if maintenance is due, false otherwise
     */
    public boolean isMaintenanceDue() {
        return LocalDate.now().isAfter(nextMaintenanceDate) || 
               LocalDate.now().isEqual(nextMaintenanceDate);
    }

    /**
     * Reports an issue with the equipment.
     * @param issue Description of the issue
     */
    public void reportIssue(String issue) {
        this.status = "Out of Order";
        this.description = issue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Equipment equipment)) return false;
        return equipmentId == equipment.equipmentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipmentId);
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "equipmentId=" + equipmentId +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", location='" + location + '\'' +
                ", lastMaintenanceDate=" + lastMaintenanceDate +
                ", nextMaintenanceDate=" + nextMaintenanceDate +
                '}';
    }
} 