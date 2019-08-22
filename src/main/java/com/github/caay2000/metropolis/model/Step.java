package com.github.caay2000.metropolis.model;

public class Step {

    private final Position position;
    private final Position newPosition;
    private final double distance;
    private final int roundedUpTime;
    private final double speed;

    public Step(Position position, Position newPosition, double distance, int roundedUpTime, double speed) {

        this.position = position;
        this.newPosition = newPosition;
        this.distance = distance;
        this.roundedUpTime = roundedUpTime;
        this.speed = speed;
    }
}
