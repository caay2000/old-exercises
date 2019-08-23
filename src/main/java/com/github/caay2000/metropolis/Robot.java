package com.github.caay2000.metropolis;

import com.github.caay2000.metropolis.collector.DataCollector;
import com.github.caay2000.metropolis.collector.DataMeter;
import com.github.caay2000.metropolis.engine.MovementEngine;
import com.github.caay2000.metropolis.engine.Position;
import com.github.caay2000.metropolis.engine.Step;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.EventCollectData;
import com.github.caay2000.metropolis.event.EventPublishReport;
import com.github.caay2000.metropolis.reporter.SystemReporter;
import com.github.caay2000.metropolis.simulation.Simulation;
import com.github.caay2000.metropolis.storage.DataStorage;
import com.google.common.math.DoubleMath;

public class Robot {

    public static final double MAX_ROBOT_SPEED = 3d; // meters/second
    private static final double DELTA = 0.5d;
    private final int reportTime;
    private final EventBus eventBus;
    private final MovementEngine engine;
    private final double reportDistance;
    private final Route route;
    private int nextReportTime;
    private Position position;
    private final Simulation simulation;
    private double nextReportDistance;

    public Robot(Position position, double reportDistance, DataMeter dataMeter, int reportTime, Simulation simulation, SystemReporter reporter) {
        this.position = position;
        this.simulation = simulation;
        this.eventBus = EventBus.getInstance();
        this.reportDistance = reportDistance;
        this.nextReportDistance = reportDistance;

        this.reportTime = reportTime;
        this.nextReportTime = reportTime;
        this.route = new Route();
        this.engine = new MovementEngine(MAX_ROBOT_SPEED);
        initSystems(dataMeter, reporter);
    }

    private void initSystems(DataMeter dataMeter, SystemReporter reporter) {
        new DataCollector(dataMeter, simulation);
        new DataStorage(reporter);
    }

    public void moveTo(Position newPosition) {

        Step step = this.engine.move(this.position, newPosition, nextReportDistance);

        this.position = step.getDestination();
        this.route.addStep(step);
        this.nextReportDistance -= step.getDistance();

        if (DoubleMath.fuzzyCompare(nextReportDistance, 0d, DELTA) == 0) {
            this.eventBus.publish(new EventCollectData(simulation.getSimulationTime(), this.position));
            this.nextReportDistance = reportDistance;
        }

        this.nextReportTime -= step.getTime();

        this.simulation.updateSimulation(step.getTime());
        if (this.nextReportTime <= 0) {
            this.eventBus.publish(new EventPublishReport(simulation.getSimulationTime(), this.position, "robot"));
            this.nextReportTime = reportTime;
        }

        if (!this.position.equals(newPosition)) {
            this.moveTo(newPosition);
        }
    }

    public Route getRoute() {
        return route;
    }
}
