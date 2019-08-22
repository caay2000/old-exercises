package com.github.caay2000.metropolis.model.collector;

import com.github.caay2000.metropolis.model.Position;

import java.util.Random;

public class RandomDataCollector implements DataCollector {

    private final Random random;

    public RandomDataCollector(Random random) {
        this.random = random;
    }

    @Override
    public CollectedData collect(Position position) {

        return null;
    }
}
