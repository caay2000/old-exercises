package com.github.caay2000.metropolis.event;

import com.github.caay2000.metropolis.collector.CollectedData;

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
