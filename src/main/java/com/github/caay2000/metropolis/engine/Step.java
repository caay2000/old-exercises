package com.github.caay2000.metropolis.engine;

public class Step {

    private final Position origin;
    private final Position destination;
    private final double distance;
    private final int time;
    private final double speed;

    public Step(Position origin, Position destination, double distance, int time, double speed) {

        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.time = time;
        this.speed = speed;
    }

    public Position getOrigin() {
        return origin;
    }

    public Position getDestination() {
        return destination;
    }

    public double getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }

    public double getSpeed() {
        return speed;
    }
}
