package com.parking.RunMode;

import java.util.Scanner;

public class Boot {
    public static void main(String[] args) {
        RunMod runMode;
        Scanner scanner = new Scanner(System.in);

        String fileName = "";
        if (null != args && args.length > 0) {
            fileName = args[0];
        }

        if (fileName.isEmpty())
            runMode = new InteractiveMode();
        else
            runMode = new FileMode();

        // running the application here
        runMode.run(scanner, fileName);

        scanner.close();
    }
}
