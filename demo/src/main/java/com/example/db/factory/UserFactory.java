package com.example.db.factory;

import com.example.db.UserDao;
import com.example.db.api.IUserDao;
import com.example.db.connection.EMFFactory;

public class UserFactory {
    private volatile static UserDao instance;

    private UserFactory() {
    }

    public static IUserDao getInstance() {
        if (instance == null) {
            synchronized (UserFactory.class) {
                if (instance == null) {

                    instance = new UserDao(EMFFactory.getInstance());

                }
            }
        }
        return instance;
    }
}
