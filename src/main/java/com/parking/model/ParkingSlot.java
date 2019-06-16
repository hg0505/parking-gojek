package com.parking.model;

public class ParkingSlot {

    int slotNumber;

    boolean isOccupied = false;

    Vehicle vehicle;



    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }


    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ParkingSlot(int slotNumber, boolean isOccupied) {
        super();
        this.slotNumber = slotNumber;
        this.isOccupied = isOccupied;
    }

    public ParkingSlot(int slotNumber, boolean isOccupied, Vehicle vehicle) {
        super();
        this.slotNumber = slotNumber;
        this.isOccupied = isOccupied;
        this.vehicle = vehicle;
    }

}
