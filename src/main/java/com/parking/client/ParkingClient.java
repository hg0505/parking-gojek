package com.parking.client;

import com.parking.service.ParkingService;
import com.parking.service.impl.ParkingServiceImpl;

/**
 *
 * @author avijayvargiy
 * Parking client
 */
public class ParkingClient
{

    ParkingService parkingService;

    public ParkingClient() {
        parkingService = new ParkingServiceImpl();
    }

    /**
     * Creates parking lots
     * @param noOfSlots
     * @return
     */
    public void createParkingLot(int noOfSlots) {

        parkingService.createSlot(noOfSlots);
    }

    /**
     * Generated parking ticket for the vehicle
     * @param regNumber
     * @param color
     * @return
     */
    public void generateTicket(String regNumber, String color) {

        parkingService.generateTicket(regNumber, color);
    }

    /**
     * Marks the parking slot empty
     * @param slotNumber
     */
    public void vacateSlot(int slotNumber) {

        parkingService.vacateSlot(slotNumber);
    }

    /**
     * Returns the list of vehicle registration numbers with the color
     * @param color
     * @return
     */
    public void registrationNumbers(String color) {

        parkingService.registrationNumbers(color);
    }

    /**
     * Returns the parking slot of the registration number of vehicle
     * @param regNumber
     * @return
     */
    public void checkVehiclePosition(String regNumber) {

        parkingService.checkVehicleSlot(regNumber);
    }

    /**
     *
     * @param color
     * @return
     */
    public void findVehicleWithColor(String color) {

        parkingService.findVehicleWithColor(color);
    }

    /**
     * Prints the status of occupied parking slots
     */
    public void status() {

        parkingService.status();
    }
}
