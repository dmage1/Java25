package com.example.model.comparator;

import com.example.model.Bicycle;

import java.util.Comparator;

public class BicycleFrameSizeComparator implements Comparator<Bicycle> {

    @Override
    public int compare(Bicycle a, Bicycle b) {
        return a.getFrameSize().compareTo(b.getFrameSize());
    }

}