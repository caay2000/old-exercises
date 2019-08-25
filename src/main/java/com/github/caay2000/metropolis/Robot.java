package com.github.caay2000.metropolis;

import com.github.caay2000.metropolis.collector.DataCollector;
import com.github.caay2000.metropolis.engine.MovementEngine;
import com.github.caay2000.metropolis.engine.Position;
import com.github.caay2000.metropolis.engine.Step;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.type.EventCollectData;
import com.github.caay2000.metropolis.event.type.EventPublishDataReport;
import com.github.caay2000.metropolis.event.type.EventPublishRouteReport;
import com.github.caay2000.metropolis.simulation.Simulation;
import com.github.caay2000.metropolis.storage.DataStorage;
import com.github.caay2000.metropolis.storage.RouteStorage;
import com.google.common.math.DoubleMath;

public class Robot implements Runnable {

    private final RobotConfiguration robotConfiguration;

    private final Simulation simulation;
    private final EventBus eventBus;
    private final MovementEngine engine;
    private final RouteStorage routeStorage;

    private Route route;
    private Position currentPosition;

    private int nextCollectDataDistance;
    private int nextPublishReportTime;

    private volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            moveTo(route.getNextStop());
        }
    }

    public void stop() {
        this.running = false;
    }

    public void restart() {
        this.running = true;
    }

    public Robot(Simulation simulation, EventBus eventBus, RobotConfiguration robotConfiguration) {

        this.simulation = simulation;
        this.eventBus = eventBus;
        this.robotConfiguration = robotConfiguration;

        this.engine = new MovementEngine(robotConfiguration.getMaxRobotSpeed());
        this.routeStorage = new RouteStorage(eventBus, robotConfiguration.getReporter());
        new DataCollector(simulation, eventBus, robotConfiguration.getDataMeter());
        new DataStorage(eventBus, robotConfiguration.getReporter());

        this.nextCollectDataDistance = robotConfiguration.getCollectDataDistance();
        this.nextPublishReportTime = robotConfiguration.getPublishReportTime();
    }

    public void start(String polyline) {
        this.route = new Route(polyline);
        this.currentPosition = route.getCurrentStop();
    }

    public void moveTo(Position nextStop) {

        Step step = this.engine.move(this.currentPosition, nextStop, this.nextCollectDataDistance);

        updateSimulation(step.getTime());
        updateRobot(step);
        checkCollectDataEvent();
        checkPublishDataReportEvent();

        if (notOnNextStop(nextStop)) {
            this.moveTo(nextStop);
        } else {
            checkPublishRouteReportEvent();
        }
    }

    private void checkPublishRouteReportEvent() {
        if (this.route.isEndOfRoute()) {
            this.eventBus.publish(new EventPublishRouteReport(simulation.getSimulationTime(), this.currentPosition));
        }
    }

    private boolean notOnNextStop(Position nextStop) {
        return !this.currentPosition.equals(nextStop);
    }

    private void checkPublishDataReportEvent() {
        if (this.nextPublishReportTime <= 0) {
            this.eventBus.publish(new EventPublishDataReport(simulation.getSimulationTime(), this.currentPosition, "robot"));
            this.nextPublishReportTime = robotConfiguration.getPublishReportTime();
        }
    }

    private void updateSimulation(int secondsElapsed) {
        this.simulation.updateSimulation(secondsElapsed);
    }

    private void updateRobot(Step step) {
        this.currentPosition = step.getDestination();
        this.nextCollectDataDistance -= step.getDistance();
        this.nextPublishReportTime -= step.getTime();
        this.routeStorage.addStep(step);
    }

    private void checkCollectDataEvent() {
        if (DoubleMath.fuzzyCompare(nextCollectDataDistance, 0d, robotConfiguration.getDistanceDelta()) == 0) {
            this.eventBus.publish(new EventCollectData(simulation.getSimulationTime(), this.currentPosition));
            this.nextCollectDataDistance = this.robotConfiguration.getCollectDataDistance();
        }
    }

    public RouteStorage getRouteStorage() {
        return routeStorage;
    }
}
