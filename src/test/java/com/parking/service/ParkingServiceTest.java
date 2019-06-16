package com.parking.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.parking.model.ParkingSlot;
import com.parking.model.Vehicle;
import com.parking.service.impl.ParkingServiceImpl;

public class ParkingServiceTest {

    private ParkingService parkingService;

    /**
     * Test Data
     */
    static String TEST_REGN_NUMBER_1 = "KA-01-HH-1234";
    static String TEST_REGN_NUMBER_2 = "KA-01-HH-9999";
    static String TEST_REGN_NUMBER_3 = "KA-01-BB-0001";
    static String TEST_REGN_NUMBER_4 = "KA-01-HH-2701";

    static enum TestVehicleColor {
        RED,
        GREEN,
        BLUE,
        YELLOW,
        ORANGE;
    }
    static Vehicle TEST_VEHICLE_1 = new Vehicle(TEST_REGN_NUMBER_1, TestVehicleColor.RED.toString());
    static Vehicle TEST_VEHICLE_2 = new Vehicle(TEST_REGN_NUMBER_2, TestVehicleColor.GREEN.toString());

    @Before
    public void setUp() {

        parkingService = new ParkingServiceImpl();
    }

    @Test
    public void testCreateSlot() {

        // Add new parking slots
        parkingService.createSlot(6);

        List<ParkingSlot> actualSlotList = parkingService.allParkingSlots();
        assertEquals(6, actualSlotList.size());
    }

    @Test
    public void testGenerateTicket() {

        // Add a new parking slot
        parkingService.createSlot(1);

        List<ParkingSlot> actualSlotList = parkingService.allParkingSlots();

        // Check if that parking slot is successfully added
        assertEquals(1, actualSlotList.size());

        // Generate the parking ticket
        parkingService.generateTicket(TEST_REGN_NUMBER_1, TestVehicleColor.RED.toString());

        List<ParkingSlot> occupiedSlotList = parkingService.occupiedSlots();

        int slotNumber = occupiedSlotList.get(0).getSlotNumber();
        String vehicleRegNumber = occupiedSlotList.get(0).getVehicle().getRegistrationNumber();
        String color = occupiedSlotList.get(0).getVehicle().getColor();

        assertEquals(1, slotNumber);
        assertEquals(TEST_REGN_NUMBER_1, vehicleRegNumber);
        assertEquals(TestVehicleColor.RED.toString(), color);
    }

    @Test
    public void testVacateSlot() {

        // Add a new parking slot
        parkingService.createSlot(1);

        List<ParkingSlot> actualSlotList = parkingService.allParkingSlots();

        // Check if that parking slot is successfully added
        assertEquals(1, actualSlotList.size());

        // Generate the parking ticket
        parkingService.generateTicket(TEST_REGN_NUMBER_1, TestVehicleColor.RED.toString());

        List<ParkingSlot> occupiedSlotList = parkingService.occupiedSlots();

        int slotNumber = occupiedSlotList.get(0).getSlotNumber();
        String vehicleRegNumber = occupiedSlotList.get(0).getVehicle().getRegistrationNumber();
        String color = occupiedSlotList.get(0).getVehicle().getColor();

        // Check if vehicle is successfully allotted a slot
        assertEquals(1, slotNumber);
        assertEquals(TEST_REGN_NUMBER_1, vehicleRegNumber);
        assertEquals(TestVehicleColor.RED.toString(), color);


        parkingService.vacateSlot(1);

        List<ParkingSlot> slotStatus = parkingService.occupiedSlots();

        // Check if any slot is alloted
        assertTrue(slotStatus.isEmpty());
    }

    @Test
    public void testRegistrationNumbers() {

        // Add a new parking slot
        parkingService.createSlot(3);

        parkingService.generateTicket(TEST_REGN_NUMBER_1, TestVehicleColor.RED.toString());
        parkingService.generateTicket(TEST_REGN_NUMBER_2, TestVehicleColor.GREEN.toString());
        parkingService.generateTicket(TEST_REGN_NUMBER_3, TestVehicleColor.RED.toString());

        List<String> actualVehicleNumbers = parkingService.getRegistrationNumbersByColor(TestVehicleColor.RED.toString());

        List<String> expectedVehicleNumbers = new ArrayList<String>();
        expectedVehicleNumbers.add(TEST_REGN_NUMBER_1);
        expectedVehicleNumbers.add(TEST_REGN_NUMBER_3);

        assertEquals(actualVehicleNumbers, expectedVehicleNumbers);
    }

    @Test
    public void testFindVehicleWithColor() {

        // Add a new parking slot
        parkingService.createSlot(3);

        parkingService.generateTicket(TEST_REGN_NUMBER_1, TestVehicleColor.RED.toString());
        parkingService.generateTicket(TEST_REGN_NUMBER_2, TestVehicleColor.GREEN.toString());
        parkingService.generateTicket(TEST_REGN_NUMBER_3, TestVehicleColor.RED.toString());

        List<String> actualSlots = parkingService.getSlotsBasedOnVehicleColor(TestVehicleColor.RED.toString());

        List<String> expectedSlots = new ArrayList<String>();
        expectedSlots.add("1");
        expectedSlots.add("3");

        assertThat(actualSlots, is(expectedSlots));
    }

    @Test
    public void testStatus() {

        // Add a new parking slot
        parkingService.createSlot(3);

        // Created 3 slots, but assigned slots are 2
        parkingService.generateTicket(TEST_REGN_NUMBER_1, TestVehicleColor.RED.toString());
        parkingService.generateTicket(TEST_REGN_NUMBER_2, TestVehicleColor.GREEN.toString());

        List<ParkingSlot> actualParkingSlot = parkingService.occupiedSlots();

        assertEquals(2, actualParkingSlot.size());
    }
}