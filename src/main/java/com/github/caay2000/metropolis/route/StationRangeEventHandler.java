package com.github.caay2000.metropolis.route;

import com.github.caay2000.metropolis.event.Event;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.EventHandler;
import com.github.caay2000.metropolis.event.EventType;
import com.github.caay2000.metropolis.event.type.EventStationInRange;

class StationRangeEventHandler implements EventHandler {

    private final StationRange stationRange;

    StationRangeEventHandler(EventBus eventBus, StationRange stationRange) {
        this.stationRange = stationRange;

        eventBus.subscribe(EventType.STATION_IN_RANGE, this::inRangeHandler);
    }

    private void inRangeHandler(Event<EventStationInRange> event) {
        EventStationInRange eventStationInRange = event.to();
        this.stationRange.check(eventStationInRange.getPosition(), eventStationInRange.getRoute(), eventStationInRange.getDistanceRange());
    }
}
