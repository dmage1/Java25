package com.example.singleton;

public enum EnumSingleton {

    /**
     * You can use an Enum to implement the singleton pattern in Java. Enum values are inherently single instances by
     * design, making them a straightforward and thread-safe way to create Singleton objects.
     */
    INSTANCE;  // The single instance of the SingletonEnum class

    // Add your methods and instance variables here

    public void doSomething() {
        // Your code here
    }
}
