package com.kudakwashe.car;

import java.math.BigDecimal;
import java.util.UUID;

public class Car {
    private UUID id;
    private String regNumber;
    private BigDecimal rentalPricePerDay;
    private Brand brand;
    private boolean isElectric;

    public Car(UUID id, String regNumber, BigDecimal rentalPricePerDay, Brand brand, boolean isElectric) {
        this.id = id;
        this.regNumber = regNumber;
        this.rentalPricePerDay = rentalPricePerDay;
        this.brand = brand;
        this.isElectric = isElectric;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public Brand getBrand() {
        return brand;
    }

    public BigDecimal getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", regNumber='" + regNumber + '\'' +
                ", rentalPricePerDay=" + rentalPricePerDay +
                ", brand=" + brand +
                ", isElectric=" + isElectric +
                '}';
    }
}
