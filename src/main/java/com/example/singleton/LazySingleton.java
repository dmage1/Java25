package com.example.singleton;

public class LazySingleton {

    /**
     * In the second example (Lazy Initialization), the instance is created only when the getInstance() method is called
     * for the first time. This can save resources if the instance is not always needed. However, it's important to note
     * that this approach isn't thread-safe and might lead to issues in a multithreaded environment.
     */
    private static LazySingleton instance;

    private LazySingleton() {
        // Private constructor to prevent external instantiation
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
