package com.kudakwashe.user;

import java.util.UUID;

public class UserDao {
    private static final User[] users;

    static {
        users = new User[]{
                new User(UUID.fromString("7f3a1c92-8b46-4d17-a5e9-2c6f83b104de"), "Samantha"),
                new User(UUID.fromString("c91e7a35-2f84-4b60-9d12-76a5e3c8f421"), "Pend"),
                new User(UUID.fromString("4a6d92e1-7c35-48fb-b019-53e8d2a764cf"), "John"),
                new User(UUID.fromString("d2b7184f-63a9-4e25-8c71-f0953ba642de"), "David"),
                new User(UUID.fromString("81c4e6a2-5d73-49bf-a826-17f3d095ce64"), "Heather"),
                new User(UUID.fromString("3e95b7c1-a462-4f80-bd35-6c2189e74fa3"), "Esther")
        };
    }

    public static User[] getUsers() {
        return users;
    }

}
