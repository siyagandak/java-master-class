package com.kudakwashe.user;

public class UserService {
    public static User[] getAllUsers() {
        return UserDao.getUsers();
    }
}
