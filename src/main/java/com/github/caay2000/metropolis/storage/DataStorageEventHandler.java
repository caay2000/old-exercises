package com.github.caay2000.metropolis.storage;

import com.github.caay2000.metropolis.event.Event;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.EventHandler;
import com.github.caay2000.metropolis.event.type.EventPublishDataReport;
import com.github.caay2000.metropolis.event.type.EventStoreCollectData;
import com.github.caay2000.metropolis.event.EventType;

class DataStorageEventHandler implements EventHandler {

    private final DataStorage dataStorage;

    public DataStorageEventHandler(EventBus eventBus, DataStorage dataStorage) {
        this.dataStorage = dataStorage;

        eventBus.subscribe(EventType.STORE_COLLECT_DATA, this::storeHandler);
        eventBus.subscribe(EventType.PUBLISH_DATA_REPORT, this::publishReportHandler);
    }

    public void storeHandler(Event<EventStoreCollectData> event) {
        this.dataStorage.store(event.to(EventStoreCollectData.class).getCollectedData());
    }

    public void publishReportHandler(Event<EventPublishDataReport> event) {
        EventPublishDataReport eventPublishDataReport = event.to(EventPublishDataReport.class);
        this.dataStorage.publishReport(eventPublishDataReport.getEventTime(), eventPublishDataReport.getPosition(), eventPublishDataReport.getSource());
    }
}
