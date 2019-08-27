package com.github.caay2000.metropolis.reporter.type;

import com.github.caay2000.metropolis.reporter.Report;
import com.github.caay2000.metropolis.route.Position;

import java.text.DecimalFormat;

public class RouteReport extends Report {

    private static final DecimalFormat DISTANCE_FORMATTER = new DecimalFormat("###,###.##");

    private final Location location;
    private final String distanceTravelled;
    private final String timeElapsed;
    private final String averageSpeed;
    private final String source;

    public RouteReport(long timestamp, Position position, double distanceTravelled, int timeElapsed, double averageSpeed, String source) {
        super(timestamp);
        this.location = new Location(position.getLat(), position.getLng());
        this.distanceTravelled = DISTANCE_FORMATTER.format(distanceTravelled);
        this.timeElapsed = String.valueOf(timeElapsed);
        this.averageSpeed = DISTANCE_FORMATTER.format(averageSpeed);
        this.source = source;
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
