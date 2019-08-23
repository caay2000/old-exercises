package com.github.caay2000.metropolis.model.reporter;

import com.github.caay2000.metropolis.model.engine.Position;

public class Report {

    private final long timestamp;
    private final Location location;
    private final String level;
    private final String source;

    public Report(long timestamp, Position position, String level, String source) {
        this.timestamp = timestamp;
        this.location = new Location(position.getLat(), position.getLng());
        this.level = level;
        this.source = source;
    }

    private class Location {
        private final double lat;
        private final double lng;

        private Location(double lat, double lng) {
            this.lat = lat;
            this.lng = lng;
        }
    }

}

