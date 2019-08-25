package com.github.caay2000.metropolis.reporter;

import com.github.caay2000.metropolis.engine.Position;

public class RouteReport implements Report {

    private final long timestamp;
    private final Location location;
    private final double distanceTravelled;
    private final int timeElapsed;
    private final double averageSpeed;
    private final String source;

    public RouteReport(long timestamp, Position position, double distanceTravelled, int timeElapsed, double averageSpeed, String source) {
        this.timestamp = timestamp;
        this.location = new Location(position.getLat(), position.getLng());
        this.distanceTravelled = distanceTravelled;
        this.timeElapsed = timeElapsed;
        this.averageSpeed = averageSpeed;
        this.source = source;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Location getLocation() {
        return location;
    }

    public double getDistanceTravelled() {
        return distanceTravelled;
    }

    public int getTimeElapsed() {
        return timeElapsed;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public String getSource() {
        return source;
    }
}
