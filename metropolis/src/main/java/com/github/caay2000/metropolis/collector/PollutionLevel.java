package com.github.caay2000.metropolis.collector;

import com.github.caay2000.metropolis.exception.MetropolisException;

public enum PollutionLevel {

    GOOD(0, 50),
    MODERATE(51, 100),
    USG(101, 150),
    UNHEALTHY(151);

    private final int minValue;
    private final int maxValue;

    PollutionLevel(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    PollutionLevel(int minValue) {
        this.minValue = minValue;
        this.maxValue = Integer.MAX_VALUE;
    }

    public static class Factory {

        public static PollutionLevel getLevel(int value) {
            for (PollutionLevel level : PollutionLevel.values()) {
                if (value >= level.minValue && value <= level.maxValue) {
                    return level;
                }
            }
            throw new MetropolisException(String.format("incorrect value %d", value));
        }
    }
}
