package com.kudakwashe.car;

import java.math.BigDecimal;
import java.util.UUID;

public class CarDao {
    private static final Car[] cars;

    static {
        cars = new Car[]{
                new Car(UUID.fromString("b64f2d89-1e37-45ac-97b0-e83c6512d4fa"),
                        "AZY-5078", BigDecimal.valueOf(100.0), Brand.TOYOTA, false),
                new Car(UUID.fromString("5a18c3f7-9d42-4be6-a071-24f8d693bc15"),
                        "AZY-5809", BigDecimal.valueOf(450.0), Brand.TESLA, true),
                new Car(UUID.fromString("e73b9614-2ac8-47d5-bf30-819c4e652da7"),
                        "AZY-1098", BigDecimal.valueOf(350.0), Brand.MERCEDES, false),
                new Car(UUID.fromString("296fd8a3-c715-4e92-86b4-f31a507dc968"),
                        "AZY-0033", BigDecimal.valueOf(600.0), Brand.AUDI, true)
        };
    }

    public static Car[] geCars() {
        return cars;
    }
}
