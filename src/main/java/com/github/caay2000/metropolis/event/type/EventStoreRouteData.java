package com.github.caay2000.metropolis.event.type;

import com.github.caay2000.metropolis.collector.CollectedData;
import com.github.caay2000.metropolis.engine.Step;
import com.github.caay2000.metropolis.event.Event;
import com.github.caay2000.metropolis.event.EventType;

public class EventStoreRouteData extends Event {

    private final Step step;

    public EventStoreRouteData(long time, Step step) {
        super(time);
        this.step = step;
    }

    @Override
    public EventType getType() {
        return EventType.STORE_COLLECT_DATA;
    }

    public Step getStep() {
        return step;
    }
}
