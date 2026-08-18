package com.kudakwashe;// TODO 1. create a new branch called initial-implementation
// TODO 2. create a package with your name. i.e com.franco and move this file inside the new package
// TODO 3. implement https://amigoscode.com/learn/java-cli-build/lectures/3a83ecf3-e837-4ae5-85a8-f8ae3f60f7f5

import com.kudakwashe.booking.CarBookingService;
import com.kudakwashe.cli.CarBookingInterface;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        CarBookingInterface carBookingInterface = new CarBookingInterface();
        CarBookingService carBookingService = new CarBookingService();

        int menuOption;

        boolean running = true;

        while (running) {
            carBookingInterface.displayMenu();

            String input = scanner.nextLine();

            try {
                menuOption = Integer.parseInt(input);


                switch (menuOption) {
                    case 1 -> carBookingService.bookACar();
                    case 2 -> carBookingService.deleteBooking();
                    case 3 -> carBookingService.viewUserBookedCars();
                    case 4 -> carBookingService.viewAllBookings();
                    case 5 -> carBookingService.viewAvailableCars();
                    case 6 -> carBookingService.viewAvailableElectricCars();
                    case 7 -> carBookingService.viewAllUsers();
                    case 8 -> {
                        System.out.println("Thanks for using our service, call again");
                        running = false;
                        scanner.close();
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 8, try again.");
            }
        }
    }
}
