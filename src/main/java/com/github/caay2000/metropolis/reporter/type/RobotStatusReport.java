package com.github.caay2000.metropolis.reporter.type;

import com.github.caay2000.metropolis.event.type.EventRobotStatus;
import com.github.caay2000.metropolis.reporter.Report;
import com.github.caay2000.metropolis.route.Position;

public class RobotStatusReport implements Report {

    private final EventRobotStatus.Status status;
    private final Location location;

    public RobotStatusReport(EventRobotStatus.Status status, Position position) {
        this.status = status;
        this.location = new Location(position.getLat(), position.getLng());
    }

    public EventRobotStatus.Status getStatus() {
        return status;
    }

    public Location getLocation() {
        return location;
    }
}
