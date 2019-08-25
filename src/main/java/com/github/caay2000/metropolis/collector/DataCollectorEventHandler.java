package com.github.caay2000.metropolis.collector;

import com.github.caay2000.metropolis.event.Event;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.type.EventCollectData;
import com.github.caay2000.metropolis.event.EventHandler;
import com.github.caay2000.metropolis.event.EventType;

public class DataCollectorEventHandler implements EventHandler {

    private final EventBus eventBus;
    private final DataCollector dataCollector;

    public DataCollectorEventHandler(EventBus eventBus, DataCollector dataCollector) {
        this.eventBus = eventBus;
        this.dataCollector = dataCollector;

        this.eventBus.subscribe(EventType.COLLECT_DATA, this::collectHandler);
    }

    public void collectHandler(Event<EventCollectData> event) {

        EventCollectData eventCollectData = event.to(EventCollectData.class);
        dataCollector.collect(eventCollectData.getPosition());
    }
}
