package com.github.caay2000.metropolis.route;

public class MovementEngine {

    private final double maxRobotSpeed;

    public MovementEngine(double maxRobotSpeed) {
        this.maxRobotSpeed = maxRobotSpeed;
    }

    public RouteData move(Position origin, Position destination, double maxDistance) {

        if (origin.equals(destination)) {
            return new RouteData(origin, origin, 0d, 0, 0d);
        }

        double distance = HaversineDistance.distanceBetween(origin, destination);

        if (distance > maxDistance) {
            destination = getDeltaPosition(origin, destination, distance, maxDistance);
            distance = maxDistance;
        }
        int roundedUpTime = (int) Math.ceil(distance / this.maxRobotSpeed);
        double speed = distance / roundedUpTime;

        return new RouteData(origin, destination, distance, roundedUpTime, speed);
    }

    private Position getDeltaPosition(Position origin, Position destination, double distance, double maxDistance) {

        double factor = maxDistance / distance;

        double deltaLat = destination.getLat() - origin.getLat();
        double deltaLng = destination.getLng() - origin.getLng();

        return new Position(origin.getLat() + deltaLat * factor,
                origin.getLng() + deltaLng * factor);
    }
}
