package com.github.caay2000.metropolis.storage;

import com.github.caay2000.metropolis.event.Event;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.EventHandler;
import com.github.caay2000.metropolis.event.EventType;
import com.github.caay2000.metropolis.event.type.EventPublishRouteReport;

public class RouteStorageEventHandler implements EventHandler {

    private final RouteStorage routeStorage;

    public RouteStorageEventHandler(EventBus eventBus, RouteStorage routeStorage) {
        this.routeStorage = routeStorage;

        eventBus.subscribe(EventType.PUBLISH_ROUTE_REPORT, this::publishReportHandler);
    }

    public void publishReportHandler(Event<EventPublishRouteReport> event) {
        EventPublishRouteReport eventPublishRouteReport = event.to(EventPublishRouteReport.class);
        this.routeStorage.publishReport(eventPublishRouteReport.getEventTime(), eventPublishRouteReport.getPosition());
    }
}
