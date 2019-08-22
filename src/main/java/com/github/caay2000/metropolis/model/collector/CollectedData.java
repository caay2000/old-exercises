package com.github.caay2000.metropolis.model.collector;

import com.github.caay2000.metropolis.model.Position;

public class CollectedData {

    private final Position position;
    private final PollutionLevel pollutionLevel;
    private final int pollutionValue;

    public CollectedData(Position position, PollutionLevel pollutionLevel, int pollutionValue) {
        this.position = position;
        this.pollutionLevel = pollutionLevel;
        this.pollutionValue = pollutionValue;
    }
}
