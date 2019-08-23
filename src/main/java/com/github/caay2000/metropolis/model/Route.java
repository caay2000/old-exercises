package com.github.caay2000.metropolis.model;

import com.github.caay2000.metropolis.model.engine.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Route {

    private final List<Step> steps;
    private double averageSpeed;
    private double timeElapsed;
    private double distanceTraveled;

    public Route() {
        this.steps = new ArrayList<>();
    }

    public void addStep(Step step) {

        this.steps.add(step);

        this.distanceTraveled += step.getDistance();
        this.timeElapsed += step.getTime();
        this.averageSpeed = distanceTraveled / timeElapsed;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public double getTimeElapsed() {
        return timeElapsed;
    }

    public double getDistanceTraveled() {
        return distanceTraveled;
    }

    public List<Step> getSteps() {
        return steps;
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
