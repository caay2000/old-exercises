package com.github.caay2000.metropolis.reporter;

import com.github.caay2000.metropolis.engine.Position;

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

    public class Location {
        private final double lat;
        private final double lng;

        private Location(double lat, double lng) {
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

    public long getTimestamp() {
        return timestamp;
    }

    public Location getLocation() {
        return location;
    }

    public String getLevel() {
        return level;
    }

    public String getSource() {
        return source;
    }
}

