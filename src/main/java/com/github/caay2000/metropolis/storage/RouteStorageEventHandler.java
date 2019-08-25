package com.github.caay2000.metropolis.storage;

import com.github.caay2000.metropolis.event.Event;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.EventHandler;
import com.github.caay2000.metropolis.event.EventType;
import com.github.caay2000.metropolis.event.type.EventPublishRouteReport;
import com.github.caay2000.metropolis.event.type.EventStoreRouteData;

class RouteStorageEventHandler implements EventHandler {

    private final RouteStorage routeStorage;

    RouteStorageEventHandler(EventBus eventBus, RouteStorage routeStorage) {
        this.routeStorage = routeStorage;

        eventBus.subscribe(EventType.STORE_ROUTE_DATA, this::storeHandler);
        eventBus.subscribe(EventType.PUBLISH_ROUTE_REPORT, this::publishReportHandler);
    }

    private void storeHandler(Event<EventStoreRouteData> event) {
        this.routeStorage.store(event.to(EventStoreRouteData.class).getRouteData());
    }

    private void publishReportHandler(Event<EventPublishRouteReport> event) {
        EventPublishRouteReport eventPublishRouteReport = event.to(EventPublishRouteReport.class);
        this.routeStorage.publishReport(eventPublishRouteReport.getEventTime(), eventPublishRouteReport.getPosition());
    }
}
