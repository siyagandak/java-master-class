package com.kudakwashe.booking;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class CarBookingDto {
    private UUID carBookingId;
    private String name;
    private String brandName;
    private String electric;
    private BookingStatus bookingStatus;
    private LocalDate startDate;
    private LocalDate endDate;
    private long rentalPeriodInDays;
    private BigDecimal rentalPrice;
    private BigDecimal totalPrice;

    public UUID getCarBookingId() {
        return carBookingId;
    }

    public void setCarBookingId(UUID carBookingId) {
        this.carBookingId = carBookingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getElectric() {
        return electric;
    }

    public void setElectric(String electric) {
        this.electric = electric;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(BigDecimal rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getRentalPeriodInDays() {
        return rentalPeriodInDays;
    }

    public void setRentalPeriodInDays(long rentalPeriodInDays) {
        this.rentalPeriodInDays = rentalPeriodInDays;
    }

    @Override
    public String toString() {
        return "CarBookingDto{" +
                "carBookingId=" + carBookingId +
                ", name='" + name + '\'' +
                ", brandName='" + brandName + '\'' +
                ", electric='" + electric + '\'' +
                ", bookingStatus=" + bookingStatus +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", rentalPeriodInDays=" + rentalPeriodInDays +
                ", rentalPrice=" + rentalPrice +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
