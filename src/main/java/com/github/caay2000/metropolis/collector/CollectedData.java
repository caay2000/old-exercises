package com.github.caay2000.metropolis.collector;

import com.github.caay2000.metropolis.engine.Position;

public class CollectedData {

    private final Position position;
    private final PollutionLevel pollutionLevel;
    private final int pollutionValue;

    public CollectedData(Position position, int pollutionValue) {
        this.position = position;
        this.pollutionLevel = PollutionLevel.Factory.getLevel(pollutionValue);
        this.pollutionValue = pollutionValue;
    }

    public Position getPosition() {
        return position;
    }

    public PollutionLevel getPollutionLevel() {
        return pollutionLevel;
    }

    public int getPollutionValue() {
        return pollutionValue;
    }
}
