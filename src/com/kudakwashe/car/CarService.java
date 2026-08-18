package com.kudakwashe.car;

import com.kudakwashe.user.User;
import com.kudakwashe.user.UserDao;

public class CarService {
    public static Car[] getAllCars() {
        return CarDao.geCars();
    }
}
