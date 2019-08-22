package com.github.caay2000.metropolis.model;

import com.github.caay2000.metropolis.model.collector.DataCollector;
import com.github.caay2000.metropolis.model.event.CollectDataEvent;
import com.github.caay2000.metropolis.model.event.EventBus;
import com.google.common.math.DoubleMath;

public class Robot {

    public static final double MAX_ROBOT_SPEED = 3d; // meters/second
    private static final double DELTA = 0.5d;

    private Position position;

    private final EventBus eventBus;
    private final MovementEngine engine;
    private double nextReportDistance;
    private final Route route;
    private final DataCollector dataCollector;
    private final DataStorage dataStorage;

    public Robot(Position position, double reportDistance, DataCollector dataCollector) {
        this.position = position;
        this.nextReportDistance = reportDistance;
        this.dataCollector = dataCollector;
        this.eventBus = new EventBus();
        this.route = new Route();
        this.dataStorage = new DataStorage();
        this.engine = new MovementEngine(MAX_ROBOT_SPEED);
    }

    public void moveTo(Position newPosition) {

        Step step = this.engine.move(this.position, newPosition, nextReportDistance);

        this.position = step.getDestination();
        this.route.addStep(step);
        this.nextReportDistance -= step.getDistance();

        if (DoubleMath.fuzzyCompare(nextReportDistance, 0d, DELTA) == 0) {
            this.eventBus.publish(new CollectDataEvent(this.position));
            //System.out.println("report");
            this.nextReportDistance = 100d;
        }

        if (!this.position.equals(newPosition)) {
            this.moveTo(newPosition);
        }
    }

}
