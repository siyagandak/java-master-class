package com.kudakwashe.booking;

import com.kudakwashe.car.Car;
import com.kudakwashe.car.CarService;
import com.kudakwashe.cli.CarBookingInterface;
import com.kudakwashe.user.User;
import com.kudakwashe.user.UserService;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.UUID;

public class CarBookingService {

    public void bookACar() {
        if (!CarBookingDao.checkCarsAvailableForBooking()) {
            System.out.println("No cars available for booking");
            return;
        }

        System.out.print("Select option with respective user id: ");
        User[] users = UserService.getAllUsers();
        listUserIds(users);
        int userChoice = CarBookingInterface.validateUserInput(users.length);

        User user = users[userChoice - 1];

        System.out.print("Select option with desired car: ");
        Car[] cars = listCarsNotCurrentlyBooked();
        for (
                int i = 0;
                i < cars.length; i++) {
            if (cars[i] == null) {
                continue;
            }
            System.out.println(MessageFormat
                    .format("{0}. Car name: {1}\t Price: {2}\t Electric?: {3}",
                            (i + 1), cars[i].getBrand().name(), cars[i].getRentalPricePerDay(),
                            isVehicleElectric(cars[i].isElectric())));
        }

        int carChoice = CarBookingInterface.validateUserInput(cars.length);

        LocalDate startingDate;
        LocalDate endingDate;

        try {
            startingDate = getBookingStartDate();
        } catch (
                DateTimeParseException e) {
            System.out.println("Please enter a valid start date");
            startingDate = getBookingStartDate();
        }

        try {
            endingDate = getBookingEndDate(startingDate);
        } catch (
                DateTimeParseException e) {
            System.out.println("Please enter a valid end date");
            endingDate = getBookingEndDate(startingDate);
        }

        Car car = cars[carChoice - 1];
        long rentalPeriodInDays = CarBookingDao.calculateRentalPeriod(startingDate, endingDate);
        BigDecimal bookingPrice = car.getRentalPricePerDay().multiply(BigDecimal.valueOf(rentalPeriodInDays));

        CarBooking carBooking = new CarBooking();
        carBooking.setUuid(UUID.randomUUID());
        carBooking.setCar(car);
        carBooking.setUser(user);
        carBooking.setStartDate(startingDate);
        carBooking.setEndDate(endingDate);
        carBooking.setPrice(bookingPrice);
        carBooking.setBookedAt(LocalDateTime.now());
        carBooking.setBookingStatus(BookingStatus.ACTIVE);

        CarBookingDao.setCarsBooked(carBooking);
        System.out.println("Car booking successful");
    }

    private String isVehicleElectric(boolean electric) {
        if (electric) {
            return "Yes";
        }
        return "No";
    }

    private LocalDate getBookingStartDate() throws DateTimeParseException {
        System.out.print("Select start date (yyyy-MM-dd): ");
        Scanner input = new Scanner(System.in);
        String startDateInput = input.nextLine();
        while (LocalDate.parse(startDateInput).isBefore(LocalDate.now())) {
            System.out.println("Please enter today's date or a date in the future");
            startDateInput = input.nextLine();
        }
        return LocalDate.parse(startDateInput);
    }

    private LocalDate getBookingEndDate(LocalDate startingDate) throws DateTimeParseException {
        System.out.print("Select end date (yyyy-MM-dd): ");
        Scanner input = new Scanner(System.in);
        String endDateInput = input.nextLine();
        while (LocalDate.parse(endDateInput).isBefore(startingDate)) {
            System.out.println(MessageFormat
                    .format("Please enter a date after start date {0}: ", startingDate));
            endDateInput = input.nextLine();
        }
        return LocalDate.parse(endDateInput);
    }

    private void listUserIds(User[] users) {
        for (int i = 0; i < users.length; i++) {
            System.out.println(MessageFormat.format("{0}. User id: {1} \tname: {2}",
                    (i + 1), users[i].getId(), users[i].getName()));
        }
    }

    public void viewAllUsers() {
        int count = 0;
        User[] users = UserService.getAllUsers();
        System.out.println("List of available users:");
        for (User user : users) {
            System.out.println(MessageFormat.format("{0}. User id: {1} name: {2}", ++count, user.getId(), user.getName()));
        }
    }

    public void viewAllBookings() {
        int nullCount = 0;
        int indexForCarBookingToDisplay = 0;
        int carBookingLength = CarBookingDao.getCarsBooked().length;
        CarBooking[] carBookingsToDisplay = new CarBooking[carBookingLength];
        for (CarBooking carBooking : CarBookingDao.getCarsBooked()) {
            if (carBooking == null) {
                if (++nullCount == carBookingLength) {
                    System.out.println("No active bookings to display");
                    return;
                }
                continue;
            }
            carBookingsToDisplay[indexForCarBookingToDisplay++] = carBooking;
        }
        indexForCarBookingToDisplay = 0;
        for (CarBooking booking : carBookingsToDisplay) {
            if (booking == null) {
                continue;
            }
            System.out.println(MessageFormat.format("{0}. {1}", ++indexForCarBookingToDisplay,
                    CarBookingDao.formatCarBookingDetails(booking)));
        }
    }

    public void viewAvailableCars() {
        if (!CarBookingDao.checkCarsAvailableForBooking()) {
            System.out.println("No cars available for booking");
            return;
        }
        int count = 0;
        System.out.println("Available Cars:\n");
        for (Car car : listCarsNotCurrentlyBooked()) {
            if (car == null) {
                continue;
            }
            System.out.println(MessageFormat.format("{0}. {1}", ++count, car));
        }
    }

    public Car[] listCarsNotCurrentlyBooked() {
        Car[] cars = CarService.getAllCars();
        CarBooking[] carBookings = CarBookingDao.getCarsBooked();
        Car[] carsAvailable = new Car[cars.length];
        int index = 0;
        for (Car car : cars) {
            boolean isCarAvailableForBookingInCarBookingList = true;
            for (CarBooking carBooking : carBookings) {
                if (carBooking == null) {
                    continue;
                }
                if (car.getBrand().equals(carBooking.getCar().getBrand()) && carBooking.getBookingStatus().equals(BookingStatus.ACTIVE)) {
                    isCarAvailableForBookingInCarBookingList = false;
                }
            }
            if (isCarAvailableForBookingInCarBookingList) {
                carsAvailable[index++] = car;
            }
        }
        return carsAvailable;
    }

    public void viewAvailableElectricCars() {
        if (!CarBookingDao.checkElectricCarsAvailableForBooking()) {
            System.out.println("No electric cars available for booking");
            return;
        }
        Car[] cars = listCarsNotCurrentlyBooked();
        Car[] electricCarsAvailable = new Car[cars.length];
        int index = 0;
        int count = 0;
        System.out.println("Electric Cars Available:\n");
        for (Car car : cars) {
            if (car == null) {
                continue;
            }
            if (car.isElectric()) {
                electricCarsAvailable[index++] = car;
            }
        }
        for (Car car : electricCarsAvailable) {
            if (car == null) {
                continue;
            }
            System.out.println(MessageFormat.format("{0}. {1}", ++count, car));
        }
    }

    public void deleteBooking() {
        int bookingCount = 0;
        CarBooking[] carBookings = CarBookingDao.getCarsBooked();
        for (int i = 0; i < carBookings.length; i++) {
            if (carBookings[i] == null) {
                bookingCount++;
            }

            if (bookingCount == carBookings.length) {
                System.out.println("No bookings available to delete");
                return;
            }
        }
        System.out.println("Select car booking to delete:\n");
        int indexForDisplay = 0;
        for (int i = 0; i < carBookings.length; i++) {
            if (carBookings[i] == null) {
                continue;
            }
            if (carBookings[i] != null) {
                System.out.println(MessageFormat.format("{0}. {1}", ++indexForDisplay, carBookings[i]));
            }
        }
        int bookingOptionToDelete = CarBookingInterface.validateUserInput(carBookings.length);

        carBookings[bookingOptionToDelete - 1] = null;
        System.out.println("Booking deleted successfully\n");
    }

    public void viewUserBookedCars() {
        User[] users = UserService.getAllUsers();
        System.out.println("Select user to view their active bookings:\n");
        int count = 0;
        for (User user : users) {
            System.out.println(MessageFormat.format("{0}. User id: {1} name: {2}", ++count, user.getId(), user.getName()));
        }
        int userSelectedToDisplayActiveBookings = CarBookingInterface.validateUserInput(users.length);

        User user = users[userSelectedToDisplayActiveBookings - 1];
        CarBooking[] carBookings = CarBookingDao.getCarsBooked();
        CarBooking[] activeCarBookingsForUser = new CarBooking[carBookings.length];
        int indexForUsersActiveBookings = 0;
        int nullCount = 0;
        for (int i = 0; i < carBookings.length; i++) {
            if (carBookings[i] == null) {
                continue;
            }
            if (carBookings[i].getUser().getId().equals(user.getId())) {
                activeCarBookingsForUser[indexForUsersActiveBookings++] = carBookings[i];
            }
        }

        int countForDisplay = 0;
        for (int i = 0; i < activeCarBookingsForUser.length; i++) {
            if (activeCarBookingsForUser[i] == null) {
                nullCount++;
                continue;
            }
            System.out.println(MessageFormat.format("{0}. {1}", ++countForDisplay,
                    CarBookingDao.formatCarBookingDetails(activeCarBookingsForUser[i])));
        }

        if (nullCount == activeCarBookingsForUser.length) {
            System.out.println(MessageFormat.format("No active bookings for {0} to display", user.getName()));
        }
    }
}
