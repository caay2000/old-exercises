package com.github.caay2000.metropolis.event.type;

import com.github.caay2000.metropolis.event.Event;
import com.github.caay2000.metropolis.event.EventType;
import com.github.caay2000.metropolis.route.Position;
import com.github.caay2000.metropolis.route.Route;

public class EventStationInRange extends Event {

    private final Position position;
    private final Route route;
    private final int distanceRange;

    public EventStationInRange(long time, Position position, Route route, int distanceRange) {
        super(time);
        this.position = position;
        this.route = route;
        this.distanceRange = distanceRange;
    }

    @Override
    public EventType getType() {
        return EventType.STATION_IN_RANGE;
    }

    public Position getPosition() {
        return position;
    }

    public Route getRoute() {
        return route;
    }

    public int getDistanceRange() {
        return distanceRange;
    }
}
