package com.github.caay2000.metropolis.model;

import com.github.caay2000.metropolis.DistanceCalculator;

public class Robot {

    public static final double MAX_ROBOT_SPEED = 3d; // meters/second

    private Position position;
    private final double nextReportDistance;
    private final Route route;

    public Robot(Position position, double reportDistance) {
        this.position = position;
        this.nextReportDistance = reportDistance;
        this.route = new Route();
    }

    public void moveTo(Position newPosition) {

        if (newPosition.equals(this.position)) {
            return;
        }

        System.out.println("movingTo:" + newPosition);

        double distance = DistanceCalculator.distanceBetween(position, newPosition);
        if (distance < nextReportDistance) {
            fullMovementTo(newPosition);
        } else {
            moveToReportLocation(newPosition);
        }
    }

    private void moveToReportLocation(Position newPosition) {

        Position nextPosition = DistanceCalculator.getPositionOnRoute(this.position, newPosition, nextReportDistance);
        fullMovementTo(nextPosition);
        moveTo(newPosition);
    }

    private void fullMovementTo(Position newPosition) {
        double distance = DistanceCalculator.distanceBetween(position, newPosition);
        int roundedUpTime = (int) Math.ceil(distance / Robot.MAX_ROBOT_SPEED);
        double speed = distance / roundedUpTime;

        route.addStep(this.position, newPosition, distance, roundedUpTime, speed);
        this.position = newPosition;
    }

    public Route getRoute() {
        return route;
    }
}
