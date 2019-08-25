package com.github.caay2000.metropolis.route;

import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.SystemEventBus;
import com.github.caay2000.metropolis.event.type.EventStationInRange;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StationRangeEventHandlerTest {

    private static final long ANY_TIME = 1234;
    private static final Position ANY_POSITION = new Position(0d, 0d);

    private static final Route ANY_ROUTE = new Route("");
    private static final int ANY_DISTANCE_RANGE = 1;

    private final EventBus eventBus = new SystemEventBus();

    @Mock
    private StationRange stationRange;

    @Test
    public void eventStoreRouteDataStoresData() {
        new StationRangeEventHandler(eventBus, stationRange);

        eventBus.publish(new EventStationInRange(ANY_TIME, ANY_POSITION, ANY_ROUTE, ANY_DISTANCE_RANGE));

        verify(stationRange).check(eq(ANY_POSITION), eq(ANY_ROUTE), eq(ANY_DISTANCE_RANGE));
    }

}