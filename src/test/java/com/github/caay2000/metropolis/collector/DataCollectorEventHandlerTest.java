package com.github.caay2000.metropolis.collector;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.github.caay2000.metropolis.engine.Position;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.type.EventCollectData;
import com.github.caay2000.metropolis.event.SystemEventBus;

@RunWith(MockitoJUnitRunner.class)
public class DataCollectorEventHandlerTest {

    private static final long ANY_TIME = 1234l;
    private static final Position ANY_POSITION = new Position(0d, 0d);

    private final EventBus eventBus = new SystemEventBus();

    @Mock
    private DataCollector dataCollector;

    @Test
    public void collectDataHandlerPublishStoreCollectDataEvent() {

        new DataCollectorEventHandler(eventBus, dataCollector);

        eventBus.publish(new EventCollectData(ANY_TIME, ANY_POSITION));

        verify(dataCollector).collect(eq(ANY_POSITION));
    }
}