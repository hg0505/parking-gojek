package com.parking.util;

public class constants {


        public static final String EXIT = "exit";
        public static final String CREATE_PARKING_LOT = "create_parking_lot";
        public static final String PARK = "park";
        public static final String LEAVE = "leave";
        public static final String STATUS = "status";
        public static final String REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOR = "registration_numbers_for_cars_with_colour";
        public static final String SLOT_NUMBERS_FOR_CARS_WITH_COLOR = "slot_numbers_for_cars_with_colour";
        public static final String SLOT_NUMBER_FOR_REGISTRATION_NUMBER = "slot_number_for_registration_number";
        public static final String EMPTY = "";

        public static int convertRawToInt(String value) {

            try {
                return Integer.valueOf(value);
            } catch (NumberFormatException e) {
                return -1;
            }
        }

}
