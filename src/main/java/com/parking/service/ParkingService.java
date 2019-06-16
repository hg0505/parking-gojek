package com.parking.service;


import java.util.List;

import com.parking.model.ParkingSlot;

public interface ParkingService {
    /**
     * Creates parking slots and prints the output
     * @param noOfSlots
     */
    void createSlot(int noOfSlots);

    /**
     * Generates a ticket by allocating slot to vehicle. Prints the output
     * @param regNumber
     * @param color
     */
    void generateTicket(String regNumber, String color);

    /**
     * Removes the vehicle from slot and prints the output
     * @param slotNumber
     */
    void vacateSlot(int slotNumber);

    /**
     * Prints the registration numbers of vehicle with the color specified
     * @param color
     */
    void registrationNumbers(String color);

    /**
     * Prints the vehicle slot of corresponding registration number
     * @param regNumber
     */
    void checkVehicleSlot(String regNumber);

    /**
     * Prints the vehicle slots based on the color
     * @param color
     */
    void findVehicleWithColor(String color);

    /**
     * Prints the occupied slots
     */
    void status();

    /**
     * Returns raw data of all the occupied parking slots
     * @return
     */
    List<ParkingSlot> occupiedSlots();

    /**
     * Returns raw data of all parking slots
     * @return
     */
    List<ParkingSlot> allParkingSlots();

    /**
     * Returns the raw data of slot based on the vehicle color
     * @param color
     * @return
     */
    List<String> getSlotsBasedOnVehicleColor(String color);

    /**
     * Returns the registration numbers of vehicle based on Color
     * @param color
     * @return
     */
    List<String> getRegistrationNumbersByColor(String color);

}
