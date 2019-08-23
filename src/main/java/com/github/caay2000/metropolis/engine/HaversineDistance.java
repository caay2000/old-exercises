package com.github.caay2000.metropolis.engine;

class HaversineDistance {

    private static final double EARTH_RADIUS = 6371d;

    public double distanceBetween(Position origin, Position destination) {
        return haversine(origin, destination);
    }

    private double haversine(Position origin, Position destination) {

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