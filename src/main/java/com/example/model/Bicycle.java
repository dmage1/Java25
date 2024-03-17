package com.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@ToString
public class Bicycle implements Comparable<Bicycle> {

    private String brand;
    private Integer frameSize;

    public Bicycle(String brand, Integer frameSize) {
        this.brand = brand;
        this.frameSize = frameSize;
    }

    public Bicycle(String brand) {
        this(brand, 0);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getFrameSize() {
        return frameSize;
    }

    public void setFrameSize(Integer frameSize) {
        this.frameSize = frameSize;
    }

    @Override
    public int compareTo(Bicycle otherBicycle) {
        return Integer.compare(getFrameSize(), otherBicycle.getFrameSize());
    }

    public static List<Bicycle> getBicycleList() {
        return Arrays.asList(
                new Bicycle("Small Bike", 50),
                new Bicycle("Big Bike", 100),
                new Bicycle("Medium Bike", 75),
                new Bicycle("Tiny Bike", 10));
    }

    public static void main(String[] args) {
        List<Bicycle> bicycleList = Bicycle.getBicycleList();

        System.out.println("Before Sorting : " + bicycleList);
        Collections.sort(bicycleList);
        System.out.println("After Sorting : " + bicycleList);
    }
}
