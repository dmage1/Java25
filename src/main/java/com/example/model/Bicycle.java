package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Bicycle {

    private String brand;
    private Integer frameSize;

    public Bicycle(String brand) {
        this.brand = brand;
        this.frameSize = 0;
    }
}
