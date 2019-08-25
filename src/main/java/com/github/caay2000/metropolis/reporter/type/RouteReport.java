package com.github.caay2000.metropolis.reporter.type;

import com.github.caay2000.metropolis.reporter.Report;
import com.github.caay2000.metropolis.route.Position;

import java.text.DecimalFormat;
import java.util.Objects;
import java.util.StringJoiner;

public class RouteReport implements Report {

    private static final DecimalFormat FORMATTER = new DecimalFormat("###,###.##");

    private final long timestamp;
    private final Location location;
    private final String distanceTravelled;
    private final String timeElapsed;
    private final String averageSpeed;
    private final String source;

    public RouteReport(long timestamp, Position position, double distanceTravelled, int timeElapsed, double averageSpeed, String source) {
        this.timestamp = timestamp;
        this.location = new Location(position.getLat(), position.getLng());
        this.distanceTravelled = FORMATTER.format(distanceTravelled);
        this.timeElapsed = String.valueOf(timeElapsed);
        this.averageSpeed = FORMATTER.format(averageSpeed);
        this.source = source;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Location getLocation() {
        return location;
    }

    public String getDistanceTravelled() {
        return distanceTravelled;
    }

    public String getTimeElapsed() {
        return timeElapsed;
    }

    public String getAverageSpeed() {
        return averageSpeed;
    }

    public String getSource() {
        return source;
    }

}
