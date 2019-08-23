package com.github.caay2000.metropolis.collector;

import java.util.Random;

public class RandomDataMeter implements DataMeter {

    private final Random random;

    public RandomDataMeter() {
        this.random = new Random();
    }

    @Override
    public int getValue() {

        return Math.min(random.nextInt(200), random.nextInt(200));
    }
}
