package com.gdms.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Equipment functionality.
 */
class EquipmentTest {
    private Equipment equipment;

    @BeforeEach
    void setUp() {
        equipment = new Equipment(
            1,
            "Treadmill",
            "Available",
            "Cardio Area",
            "Commercial grade treadmill with incline"
        );
    }

    @Test
    void testEquipmentCreation() {
        assertNotNull(equipment);
        assertEquals(1, equipment.getEquipmentId());
        assertEquals("Treadmill", equipment.getName());
        assertEquals("Available", equipment.getStatus());
        assertEquals("Cardio Area", equipment.getLocation());
        assertEquals("Commercial grade treadmill with incline", equipment.getDescription());
        assertEquals(LocalDate.now(), equipment.getLastMaintenanceDate());
        assertEquals(LocalDate.now().plusMonths(1), equipment.getNextMaintenanceDate());
    }

    @Test
    void testPerformMaintenance() {
        LocalDate beforeMaintenance = equipment.getLastMaintenanceDate();
        equipment.performMaintenance(3);
        
        assertEquals(LocalDate.now(), equipment.getLastMaintenanceDate());
        assertTrue(equipment.getLastMaintenanceDate().isAfter(beforeMaintenance) || 
                  equipment.getLastMaintenanceDate().isEqual(beforeMaintenance));
        assertEquals(LocalDate.now().plusMonths(3), equipment.getNextMaintenanceDate());
        assertEquals("Available", equipment.getStatus());
    }

    @Test
    void testIsMaintenanceDue() {
        // Set next maintenance date to today
        equipment.setNextMaintenanceDate(LocalDate.now());
        assertTrue(equipment.isMaintenanceDue());

        // Set next maintenance date to tomorrow
        equipment.setNextMaintenanceDate(LocalDate.now().plusDays(1));
        assertFalse(equipment.isMaintenanceDue());

        // Set next maintenance date to yesterday
        equipment.setNextMaintenanceDate(LocalDate.now().minusDays(1));
        assertTrue(equipment.isMaintenanceDue());
    }

    @Test
    void testReportIssue() {
        String issue = "Motor making unusual noise";
        equipment.reportIssue(issue);
        
        assertEquals("Out of Order", equipment.getStatus());
        assertEquals(issue, equipment.getDescription());
    }

    @Test
    void testEqualsAndHashCode() {
        Equipment sameEquipment = new Equipment(
            1,
            "Treadmill",
            "Available",
            "Cardio Area",
            "Commercial grade treadmill with incline"
        );
        Equipment differentEquipment = new Equipment(
            2,
            "Exercise Bike",
            "Available",
            "Cardio Area",
            "Stationary bike with resistance levels"
        );

        assertEquals(equipment, sameEquipment);
        assertNotEquals(equipment, differentEquipment);
        assertEquals(equipment.hashCode(), sameEquipment.hashCode());
        assertNotEquals(equipment.hashCode(), differentEquipment.hashCode());
    }

    @Test
    void testToString() {
        String equipmentString = equipment.toString();
        assertTrue(equipmentString.contains("equipmentId=1"));
        assertTrue(equipmentString.contains("name='Treadmill'"));
        assertTrue(equipmentString.contains("status='Available'"));
        assertTrue(equipmentString.contains("location='Cardio Area'"));
    }

    @Test
    void testStatusUpdate() {
        equipment.setStatus("In Use");
        assertEquals("In Use", equipment.getStatus());
        
        equipment.setStatus("Under Maintenance");
        assertEquals("Under Maintenance", equipment.getStatus());
    }

    @Test
    void testLocationUpdate() {
        String newLocation = "Weight Area";
        equipment.setLocation(newLocation);
        assertEquals(newLocation, equipment.getLocation());
    }
} 