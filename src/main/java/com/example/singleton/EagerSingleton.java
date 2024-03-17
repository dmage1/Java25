package com.example.singleton;

public class EagerSingleton {

    /**
     * In the first example (Eager Initialization), the instance is created as soon as the class is loaded, ensuring
     * that the instance is always available when needed.
     */
    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {
        // Private constructor to prevent external instantiation
    }

    public static EagerSingleton getInstance() {
        return instance;
    }
}
