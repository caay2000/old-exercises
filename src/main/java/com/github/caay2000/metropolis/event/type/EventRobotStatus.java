package com.github.caay2000.metropolis.event.type;

import com.github.caay2000.metropolis.event.Event;
import com.github.caay2000.metropolis.event.EventType;
import com.github.caay2000.metropolis.reporter.Report;
import com.github.caay2000.metropolis.reporter.type.RobotStatusReport;
import com.github.caay2000.metropolis.route.Position;

public class EventRobotStatus extends Event {

    private final Position position;
    private final Status status;

    public EventRobotStatus(long time, Position position, Status status) {
        super(time);
        this.position = position;
        this.status = status;
    }

    @Override
    public EventType getType() {
        return EventType.ROBOT_STATUS;
    }

    public Report getReport() {
        return new RobotStatusReport(status, position);
    }

    public enum Status {
        START,
        STOP,
        RESTART
    }
}
