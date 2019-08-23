package com.github.caay2000.metropolis.model.collector;

import com.github.caay2000.metropolis.model.event.*;
import com.github.caay2000.metropolis.model.provider.DateProvider;

public class DataCollector {

    private final EventBus eventBus;
    private final DataMeter meter;
    private final DateProvider dateProvider;

    public DataCollector(DataMeter meter, DateProvider dateProvider) {
        this.dateProvider = dateProvider;

        this.eventBus = EventBus.getInstance();
        this.eventBus.subscribe(EventType.COLLECT_DATA, this::collect);
        this.meter = meter;
    }

    public void collect(Event<EventCollectData> event) {

        EventCollectData eventCollectData = event.to(EventCollectData.class);
        CollectedData collectedData = new CollectedData(eventCollectData.getPosition(), this.meter.getValue());
        this.eventBus.publish(new EventStoreCollectData(dateProvider.getEpoch(), collectedData));
    }
}
