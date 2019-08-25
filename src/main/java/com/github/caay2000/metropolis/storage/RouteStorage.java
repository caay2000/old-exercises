package com.github.caay2000.metropolis.storage;

import java.util.ArrayList;
import java.util.List;
import com.github.caay2000.metropolis.engine.Position;
import com.github.caay2000.metropolis.engine.Step;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.EventHandler;
import com.github.caay2000.metropolis.reporter.Reporter;
import com.github.caay2000.metropolis.reporter.RouteReport;

public class RouteStorage {

    private final EventHandler eventHandler;

    private final Reporter reporter;

    private final List<Step> steps;
    private double distanceTraveled;
    private int timeElapsed;
    private double averageSpeed;

    public RouteStorage(EventBus eventBus, Reporter reporter) {

        this.eventHandler = new RouteStorageEventHandler(eventBus, this);
        this.reporter = reporter;
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

    public void publishReport(long eventTime, Position position) {
        reporter.publishReport(new RouteReport(eventTime, position, distanceTraveled, timeElapsed, averageSpeed, "route"));
    }
}
