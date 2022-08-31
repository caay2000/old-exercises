package com.github.caay2000.metropolis.reporter.type;

import com.github.caay2000.metropolis.event.type.EventRobotStatus;
import com.github.caay2000.metropolis.reporter.Report;
import com.github.caay2000.metropolis.route.Position;

public class RobotStatusReport extends Report {

    private final Location location;
    private final EventRobotStatus.Status status;

    public RobotStatusReport(long timestamp, Position position, EventRobotStatus.Status status) {
        super(timestamp);
        this.status = status;
        this.location = new Location(position.getLat(), position.getLng());
    }

    public Location getLocation() {
        return location;
    }

    public EventRobotStatus.Status getStatus() {
        return status;
    }
}
