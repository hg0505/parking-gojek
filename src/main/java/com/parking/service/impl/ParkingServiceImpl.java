package com.parking.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.parking.model.ParkingSlot;
import com.parking.model.Vehicle;
import com.parking.service.ParkingService;

public class ParkingServiceImpl implements ParkingService{

    /**
     * Application Data
     */
    private List<ParkingSlot> parkingSlots = new ArrayList<ParkingSlot>();


    /**
     * Count of the parking slots
     */
    private int lastSlotNumber = 0;


    /**
     * Creates parking slots and prints the output
     * @param noOfSlots
     */
    public void createSlot(int noOfSlots) {

        for(int i=0; i < noOfSlots; i++) {

            int newSlotNumber = lastSlotNumber + 1;

            parkingSlots.add(new ParkingSlot(newSlotNumber, false));
            lastSlotNumber++;
        }

        System.out.println("Created a parking lot with " + noOfSlots + " slots");
    }

    /**
     * Generates a ticket by allocating slot to vehicle. Prints the output
     * @param regNumber
     * @param color
     */
    public void generateTicket(String regNumber, String color) {

        Optional<ParkingSlot> emptySlot = parkingSlots.stream().filter(p -> p.isOccupied() == false).findFirst()
                .map(slot -> {
                    slot.setVehicle(new Vehicle(regNumber, color));
                    slot.setOccupied(true);
                    System.out.println("Allocated slot number: "+ slot.getSlotNumber());
                    return slot;
                });

        if(!emptySlot.isPresent()) {
            System.out.println("Sorry, parking lot is full");
        }
    }

    /**
     * Removes the vehicle from slot and prints the output
     * @param slotNumber
     */
    public void vacateSlot(int slotNumber) {

        Optional<ParkingSlot> parkingSlot = parkingSlots.stream().filter(p -> p.getSlotNumber() == slotNumber).findFirst();
        parkingSlot.ifPresent(slot -> {
            slot.setOccupied(false);
            slot.setVehicle(null);
        });

        parkingSlots.removeIf(x -> x.getSlotNumber() == slotNumber);
        parkingSlots.add(parkingSlot.get());

        System.out.println("Slot number "+ slotNumber +" is free");
    }

    /**
     * Prints the registration numbers of vehicle with the color specified
     * @param color
     */
    public void registrationNumbers(String color) {

        List<String> slots = getRegistrationNumbersByColor(color);

        String slotsCommaSeparated = String.join(", ", slots);

        System.out.println(slotsCommaSeparated);
    }

    /**
     * Returns the registration numbers of vehicle based on Color
     * @param color
     * @return
     */
    public List<String> getRegistrationNumbersByColor(String color) {

        return parkingSlots.stream().filter(p -> (p.isOccupied() == true) && (p.getVehicle().getColor().equals(color)))
                .map(m -> m.getVehicle().getRegistrationNumber())
                .collect(Collectors.toList());
    }

    /**
     * Prints the vehicle slot of corresponding registration number
     * @param regNumber
     */
    public void checkVehicleSlot(String regNumber) {

        Optional<ParkingSlot> parkingSlot = parkingSlots.stream().filter(p -> (p.isOccupied() == true)
                && (p.getVehicle().getRegistrationNumber().equals(regNumber))).findFirst();

        parkingSlot.ifPresent(slot -> {
            System.out.println(slot.getSlotNumber());
        });

        if(!parkingSlot.isPresent()) {
            System.out.println("Not found");
        }
    }

    /**
     * Prints the vehicle slots based on the color
     * @param color
     */
    public void findVehicleWithColor(String color) {

        List<String> slots = getSlotsBasedOnVehicleColor(color);

        String slotsCommaSeparated = String.join(", ", slots);

        System.out.println(slotsCommaSeparated);
    }

    /**
     * Returns the raw data of slot based on the vehicle color
     * @param color
     * @return
     */
    public List<String> getSlotsBasedOnVehicleColor(String color) {

        List<String> slots = parkingSlots.stream().filter(p -> (p.isOccupied() == true) && (p.getVehicle().getColor().equals(color)))
                .map(m -> String.valueOf(m.getSlotNumber()))
                .collect(Collectors.toList());

        return slots;
    }

    /**
     * Prints the occupied slots
     */
    public void status() {
        String format = "%1$-10s  %2$-19s%3$-1s";
        System.out.printf(format, "Slot No.", "Registration No", "Colour");
        List<ParkingSlot> slots = parkingSlots.stream().filter(p -> (p.isOccupied() == true)).collect(Collectors.toList());

        for (ParkingSlot parkingSlot : slots) {
            int slotNumber = parkingSlot.getSlotNumber();
            String registrationNumber = parkingSlot.getVehicle().getRegistrationNumber();
            String colour = parkingSlot.getVehicle().getColor();

            System.out.printf("\n"+format, slotNumber, registrationNumber, colour);
        }
        System.out.println("");
    }

    /**
     * Returns raw data of all the occupied parking slots
     * @return
     */
    public List<ParkingSlot> occupiedSlots() {

        return parkingSlots.stream().filter(p -> (p.isOccupied() == true)).collect(Collectors.toList());
    }

    /**
     * Returns raw data of all parking slots
     * @return
     */
    public List<ParkingSlot> allParkingSlots() {

        return parkingSlots;
    }

}
