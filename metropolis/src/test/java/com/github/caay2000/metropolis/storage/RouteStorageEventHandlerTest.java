package com.github.caay2000.metropolis.storage;

import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.SystemEventBus;
import com.github.caay2000.metropolis.event.type.EventPublishRouteReport;
import com.github.caay2000.metropolis.event.type.EventStoreRouteData;
import com.github.caay2000.metropolis.route.Position;
import com.github.caay2000.metropolis.route.RouteData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RouteStorageEventHandlerTest {

    private static final long ANY_TIME = 1234;
    private static final String ANY_SOURCE = "test_source";
    private static final Position ANY_POSITION = new Position(0d, 0d);

    private static final RouteData SOME_ROUTE_DATA = new RouteData(ANY_POSITION, ANY_POSITION, 10, 10, 1);

    private final EventBus eventBus = new SystemEventBus();

    @Mock
    private RouteStorage routeStorage;

    @Test
    public void eventStoreRouteDataStoresData() {

        new RouteStorageEventHandler(eventBus, routeStorage);

        eventBus.publish(new EventStoreRouteData(ANY_TIME, SOME_ROUTE_DATA));

        verify(routeStorage).store(eq(SOME_ROUTE_DATA));
    }

    @Test
    public void eventPublishReport() {
        new RouteStorageEventHandler(eventBus, routeStorage);

        eventBus.publish(new EventPublishRouteReport(ANY_TIME, ANY_POSITION));

        verify(routeStorage).publishReport(eq(ANY_TIME), eq(ANY_POSITION));
    }

}