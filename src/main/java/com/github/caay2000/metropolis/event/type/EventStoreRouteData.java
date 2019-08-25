package com.github.caay2000.metropolis.event.type;

import com.github.caay2000.metropolis.route.RouteData;
import com.github.caay2000.metropolis.event.Event;
import com.github.caay2000.metropolis.event.EventType;

public class EventStoreRouteData extends Event {

    private final RouteData routeData;

    public EventStoreRouteData(long time, RouteData routeData) {
        super(time);
        this.routeData = routeData;
    }

    @Override
    public EventType getType() {
        return EventType.STORE_ROUTE_DATA;
    }

    public RouteData getRouteData() {
        return routeData;
    }
}
