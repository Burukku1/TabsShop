package com.example.db.connection;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMFFactory {
    private final static EntityManagerFactory instance;

    static {

            instance =
                    Persistence.createEntityManagerFactory("test");

    }

    public static EntityManagerFactory getInstance() {
        return instance;
    }
}
