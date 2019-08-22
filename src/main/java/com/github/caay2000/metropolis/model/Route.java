package com.github.caay2000.metropolis.model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Route {

    private double averageSpeed;
    private double timeElapsed;
    private double distanceTraveled;

    private final List<Step> steps;

    public Route() {
        this.steps = new ArrayList<>();
    }

    public void addStep(Position position, Position newPosition, double distance, int time, double speed) {

        this.steps.add(new Step(position, newPosition, distance, time, speed));

        this.distanceTraveled += distance;
        this.timeElapsed += time;
        this.averageSpeed = distanceTraveled / timeElapsed;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Route.class.getSimpleName() + "[", "]")
                .add("averageSpeed=" + averageSpeed)
                .add("timeElapsed=" + timeElapsed)
                .add("distanceTraveled=" + distanceTraveled)
                .add("stepsSize=" + steps.size())
                .toString();
    }
}
