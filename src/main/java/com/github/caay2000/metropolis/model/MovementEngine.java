package com.github.caay2000.metropolis.model;

public class MovementEngine {

    private final double maxRobotSpeed;

    public MovementEngine(double maxRobotSpeed) {
        this.maxRobotSpeed = maxRobotSpeed;
    }

    public Step move(Position origin, Position destination, double maxDistance) {

        if (origin.equals(destination)) {
            return new Step(origin, origin, 0d, 0, 0d);
        }

        double distance = DistanceCalculator.distanceBetween(origin, destination);

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

    public static class DistanceCalculator {

        private static final double EARTH_RADIUS = 6371d;

        public static double distanceBetween(Position origin, Position destination) {
            return haversine(origin, destination);
        }

        private static double haversine(Position origin, Position destination) {

            double dLat = Math.toRadians(destination.getLat() - origin.getLat());
            double dLon = Math.toRadians(destination.getLng() - origin.getLng());

            double a = Math.pow(Math.sin(dLat / 2), 2) +
                    Math.pow(Math.sin(dLon / 2), 2) *
                            Math.cos(Math.toRadians(origin.getLat())) *
                            Math.cos(Math.toRadians(destination.getLat()));
            double c = 2 * Math.asin(Math.sqrt(a));
            return EARTH_RADIUS * c * 1000;
        }
    }
}
