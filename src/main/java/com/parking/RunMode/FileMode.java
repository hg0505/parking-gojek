package com.parking.RunMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.parking.client.ParkingClient;
import com.parking.util.Constants;

public class FileMode implements RunMod {

    public void run(Scanner scanner, String fileName) {

        // For File Mode
        File file = new File(fileName);
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {

            e.printStackTrace();
            scanner.close();
        }

        ParkingClient parkingClient = new ParkingClient();

        while (scanner.hasNextLine()) {
            String inputLine = scanner.nextLine();
            String command = "";

            if (inputLine.equals(Constants.EXIT)) {
                break;
            } else if (inputLine.equals(Constants.EMPTY)) {
                System.out.println("Please enter correct input. \n");
            } else {

                String[] splitValues = inputLine.split(" ");
                command = inputLine.split(" ")[0];
                if (command.equals(Constants.CREATE_PARKING_LOT)) {

                    int convertedInt = Constants.convertRawToInt(splitValues[1]);
                    if (convertedInt > 0) {

                        parkingClient.createParkingLot(convertedInt);
                    } else {

                        System.out.println("Incorrect command format. Please try again with correct format.");
                    }
                } else if (command.equals(Constants.PARK)) {

                    parkingClient.generateTicket(splitValues[1], splitValues[2]);
                } else if (command.equals(Constants.LEAVE)) {

                    int convertedInt = Constants.convertRawToInt(splitValues[1]);
                    if (convertedInt > 0) {
                        parkingClient.vacateSlot(convertedInt);
                    } else {
                        System.out.println("Incorrect command format. Please try again with correct format.");
                    }
                } else if (command.equals(Constants.STATUS)) {

                    parkingClient.status();
                } else if (command.equals(Constants.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOR)) {

                    parkingClient.registrationNumbers(splitValues[1]);
                } else if (command.equals(Constants.SLOT_NUMBER_FOR_REGISTRATION_NUMBER)) {

                    parkingClient.checkVehiclePosition(splitValues[1]);
                } else if (command.equals(Constants.SLOT_NUMBERS_FOR_CARS_WITH_COLOR)) {

                    parkingClient.findVehicleWithColor(splitValues[1]);
                } else {

                    System.out.println("Please enter correct input. \n");
                }
            }
        }
    }
}
