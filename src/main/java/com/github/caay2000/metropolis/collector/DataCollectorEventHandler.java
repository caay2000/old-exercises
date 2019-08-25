package com.github.caay2000.metropolis.collector;

import com.github.caay2000.metropolis.event.Event;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.EventHandler;
import com.github.caay2000.metropolis.event.EventType;
import com.github.caay2000.metropolis.event.type.EventCollectData;
import com.github.caay2000.metropolis.event.type.EventCollectInstantData;

class DataCollectorEventHandler implements EventHandler {

    private final DataCollector dataCollector;

    DataCollectorEventHandler(EventBus eventBus, DataCollector dataCollector) {
        this.dataCollector = dataCollector;

        eventBus.subscribe(EventType.COLLECT_DATA, this::collectHandler);
        eventBus.subscribe(EventType.COLLECT_INSTANT_DATA, this::collectInstantHandler);
    }


    private void collectHandler(Event<EventCollectData> event) {

        EventCollectData eventCollectData = event.to(EventCollectData.class);
        dataCollector.collect(eventCollectData.getPosition());
    }

    private void collectInstantHandler(Event<EventCollectInstantData> event) {
        EventCollectInstantData eventCollectInstantData = event.to(EventCollectInstantData.class);
        dataCollector.collectInstant(eventCollectInstantData.getPosition(), eventCollectInstantData.getSource());
    }
}
