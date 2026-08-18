package com.kudakwashe.booking;

import com.kudakwashe.car.Car;
import com.kudakwashe.car.CarDao;
import com.kudakwashe.car.CarService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CarBookingDao {
    private static CarBooking[] carsBooked = new CarBooking[CarDao.geCars().length];

    public static CarBooking[] getCarsBooked() {
        return carsBooked;
    }

    public static void setCarsBooked(CarBooking carBooked) {
        for (int i = 0; i < carsBooked.length; i++) {
            if (carsBooked[i] == null) {
                carsBooked[i] = carBooked;
                return;
            }
        }
    }

    public static boolean checkCarsAvailableForBooking() {
        int count = 0;
        for (CarBooking carBooking : carsBooked) {
            if (carBooking == null) {
                count++;
            }
        }
        return count != 0;
    }

    public static boolean checkElectricCarsAvailableForBooking() {
        int count = 0;
        int noBookingsCount = 0;
        for (CarBooking carBooking : carsBooked) {
            if (carBooking == null) {
                noBookingsCount++;
            }
            if (carBooking != null && carBooking.getCar().isElectric()) {
                count++;
            }
        }
        if (noBookingsCount == 4) {
            return true;
        }
        return count != retrieveElectricCarsCount();
    }

    private static int retrieveElectricCarsCount() {
        int count = 0;
        for (Car car : CarService.getAllCars()) {
            if (car == null) {
                continue;
            }
            if (car.isElectric()) {
                count++;
            }
        }
        return count;
    }

    public static CarBookingDto formatCarBookingDetails(CarBooking carBooking) {
        LocalDate startDate = carBooking.getStartDate();
        LocalDate endDate = carBooking.getEndDate();
        long rentalPeriodInDays = calculateRentalPeriod(startDate, endDate);
        CarBookingDto carBookingDto = new CarBookingDto();
        carBookingDto.setCarBookingId(carBooking.getCar().getId());
        carBookingDto.setName(carBooking.getUser().getName());
        carBookingDto.setBrandName(carBooking.getCar().getBrand().name());
        carBookingDto.setElectric(carBooking.getCar().isElectric() ? "Electric" : "Non-electric");
        carBookingDto.setBookingStatus(carBooking.getBookingStatus());
        carBookingDto.setStartDate(startDate);
        carBookingDto.setEndDate(endDate);
        carBookingDto.setRentalPeriodInDays(rentalPeriodInDays);
        carBookingDto.setRentalPrice(carBooking.getCar().getRentalPricePerDay());
        carBookingDto.setTotalPrice(carBooking.getCar().getRentalPricePerDay().multiply(BigDecimal.valueOf(rentalPeriodInDays)));
        return carBookingDto;
    }

    public static long calculateRentalPeriod(LocalDate startingDate, LocalDate endingDate) {
        return ChronoUnit.DAYS.between(startingDate, endingDate) + 1;
    }
}
