package com.github.caay2000.metropolis.model;

import com.github.caay2000.metropolis.model.collector.DataCollector;
import com.github.caay2000.metropolis.model.collector.DataMeter;
import com.github.caay2000.metropolis.model.engine.MovementEngine;
import com.github.caay2000.metropolis.model.engine.Position;
import com.github.caay2000.metropolis.model.engine.Step;
import com.github.caay2000.metropolis.model.event.EventBus;
import com.github.caay2000.metropolis.model.event.EventCollectData;
import com.github.caay2000.metropolis.model.event.EventPublishReport;
import com.github.caay2000.metropolis.model.provider.DateProvider;
import com.github.caay2000.metropolis.model.reporter.SystemReporter;
import com.github.caay2000.metropolis.model.storage.DataStorage;
import com.google.common.math.DoubleMath;

public class Robot {

    public static final double MAX_ROBOT_SPEED = 3d; // meters/second
    private static final double DELTA = 0.5d;
    private final int reportTime;
    private final DateProvider dateProvider;
    private final EventBus eventBus;
    private final MovementEngine engine;
    private final double reportDistance;
    private final Route route;
    private int nextReportTime;
    private Position position;
    private double nextReportDistance;


    public Robot(Position position, double reportDistance, DataMeter dataMeter, int reportTime, DateProvider dateProvider, SystemReporter reporter) {
        this.position = position;
        this.dateProvider = dateProvider;
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
        new DataCollector(dataMeter, dateProvider);
        new DataStorage(reporter);
    }

    public void moveTo(Position newPosition) {

        Step step = this.engine.move(this.position, newPosition, nextReportDistance);

        this.position = step.getDestination();
        this.route.addStep(step);
        this.nextReportDistance -= step.getDistance();

        if (DoubleMath.fuzzyCompare(nextReportDistance, 0d, DELTA) == 0) {
            this.eventBus.publish(new EventCollectData(dateProvider.getEpoch(), this.position));
            this.nextReportDistance = reportDistance;
        }

        this.nextReportTime -= step.getTime();
        this.dateProvider.forward(step.getTime() * 1000);
        if (this.nextReportTime <= 0) {
            this.eventBus.publish(new EventPublishReport(dateProvider.getEpoch(), this.position, "robot"));
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
