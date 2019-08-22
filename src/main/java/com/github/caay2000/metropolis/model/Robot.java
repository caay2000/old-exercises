package com.github.caay2000.metropolis.model;

public class Robot {

    public static final double MAX_ROBOT_SPEED = 3d; // meters/second

    private Position position;

    private final MovementEngine engine;
    private double nextReportDistance;
    private final Route route;
    private final DataCollector dataCollector;
    private final DataStorage dataStorage;

    public Robot(Position position, double reportDistance, DataCollector dataCollector) {
        this.position = position;
        this.nextReportDistance = reportDistance;
        this.dataCollector = dataCollector;
        this.route = new Route();
        this.dataStorage = new DataStorage();
        this.engine = new MovementEngine(MAX_ROBOT_SPEED);
    }

    public void moveTo(Position newPosition) {

        System.out.println("moving from " + this.position + " to " + newPosition);
        Step step = this.engine.move(this.position, newPosition, nextReportDistance);

        this.position = step.getDestination();
        this.route.addStep(step);
        this.nextReportDistance -= step.getDistance();

        if (nextReportDistance == 0d) {
            System.out.println("report");
            this.nextReportDistance = 100d;
        }

        if (!this.position.equals(newPosition)) {
            this.moveTo(newPosition);
        }
    }

    public Route getRoute() {
        return route;
    }
}
