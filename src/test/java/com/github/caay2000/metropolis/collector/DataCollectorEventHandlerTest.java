package com.github.caay2000.metropolis.collector;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import com.github.caay2000.metropolis.event.type.EventCollectInstantData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.github.caay2000.metropolis.route.Position;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.type.EventCollectData;
import com.github.caay2000.metropolis.event.SystemEventBus;

@RunWith(MockitoJUnitRunner.class)
public class DataCollectorEventHandlerTest {

    private static final long ANY_TIME = 1234;
    private static final Position ANY_POSITION = new Position(0d, 0d);
    private static final String ANY_SOURCE = "test_source";

    private final EventBus eventBus = new SystemEventBus();

    @Mock
    private DataCollector dataCollector;

    @Test
    public void collectDataHandler() {

        new DataCollectorEventHandler(eventBus, dataCollector);

        eventBus.publish(new EventCollectData(ANY_TIME, ANY_POSITION));

        verify(dataCollector).collect(eq(ANY_POSITION));
    }

    @Test
    public void collectInstantDataHandler() {

        new DataCollectorEventHandler(eventBus, dataCollector);

        eventBus.publish(new EventCollectInstantData(ANY_TIME, ANY_POSITION, ANY_SOURCE));

        verify(dataCollector).collectInstant(eq(ANY_POSITION), eq(ANY_SOURCE));
    }
}