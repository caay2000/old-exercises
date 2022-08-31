package com.github.caay2000.metropolis.event.type;

import com.github.caay2000.metropolis.collector.CollectedData;
import com.github.caay2000.metropolis.event.Event;
import com.github.caay2000.metropolis.event.EventType;

public class EventStoreCollectData extends Event {

    private final CollectedData collectedData;

    public EventStoreCollectData(long time, CollectedData collectedData) {
        super(time);
        this.collectedData = collectedData;
    }

    @Override
    public EventType getType() {
        return EventType.STORE_COLLECT_DATA;
    }

    public CollectedData getCollectedData() {
        return collectedData;
    }
}
