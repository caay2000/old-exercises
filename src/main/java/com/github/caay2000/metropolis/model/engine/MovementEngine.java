package com.github.caay2000.metropolis.model.engine;

public class MovementEngine {

    private final HaversineDistance haversineDistance;
    private final double maxRobotSpeed;

    public MovementEngine(double maxRobotSpeed) {
        this.haversineDistance = new HaversineDistance();
        this.maxRobotSpeed = maxRobotSpeed;
    }

    public Step move(Position origin, Position destination, double maxDistance) {

        if (origin.equals(destination)) {
            return new Step(origin, origin, 0d, 0, 0d);
        }

        double distance = this.haversineDistance.distanceBetween(origin, destination);

        if (distance > maxDistance) {
            destination = getDeltaPosition(origin, destination, distance, maxDistance);
            distance = maxDistance;
        }
        int roundedUpTime = (int) Math.ceil(distance / this.maxRobotSpeed);
        double speed = distance / roundedUpTime;

        return new Step(origin, destination, distance, roundedUpTime, speed);
    }

    private Position getDeltaPosition(Position origin, Position destination, double distance, double maxDistance) {

        double factor = maxDistance / distance;

        double deltaLat = destination.getLat() - origin.getLat();
        double deltaLng = destination.getLng() - origin.getLng();

        return new Position(origin.getLat() + deltaLat * factor,
                origin.getLng() + deltaLng * factor);
    }

}
