package com.github.caay2000.metropolis.event.type;

import com.github.caay2000.metropolis.route.Position;
import com.github.caay2000.metropolis.event.Event;
import com.github.caay2000.metropolis.event.EventType;

public class EventPublishRouteReport extends Event {

    private final Position position;

    public EventPublishRouteReport(long time, Position position) {
        super(time);
        this.position = position;
    }

    @Override
    public EventType getType() {
        return EventType.PUBLISH_ROUTE_REPORT;
    }

    public Position getPosition() {
        return this.position;
    }
}
