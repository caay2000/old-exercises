package com.github.caay2000.metropolis.reporter.type;

public class Location {
    private final double lat;
    private final double lng;

    Location(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

}