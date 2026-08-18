package com.kudakwashe.cli;

import java.text.MessageFormat;
import java.util.Scanner;

public class CarBookingInterface {
    private static final Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        String menuDisplay = """
                1 - Book Car
                2 - Delete Booking
                3 - View All User Booked Cars
                4 - View All Bookings
                5 - View Available Cars
                6 - View Available Electric Cars
                7 - View All Users
                8 - Exit
                """;
        System.out.println("Welcome to Kuda's Car Booking Service. \n Select an option below to enjoy our service:\n");
        System.out.println(menuDisplay);
    }

    public static int validateUserInput(int totalOptionsAvailable) {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while (!userInput.matches("\\d+")
                || Integer.parseInt(userInput) <= 0
                || Integer.parseInt(userInput) > totalOptionsAvailable) {
            System.out.println(MessageFormat
                    .format("Invalid input. Enter a valid number between the range of 1 and {0}",
                            totalOptionsAvailable));
            userInput = scanner.nextLine();
        }
        return Integer.parseInt(userInput);
    }
}


