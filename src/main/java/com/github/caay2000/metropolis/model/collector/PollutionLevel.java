package com.github.caay2000.metropolis.model.collector;

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


}
