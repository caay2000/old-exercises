package com.github.caay2000.metropolis.storage;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.github.caay2000.metropolis.collector.CollectedData;
import com.github.caay2000.metropolis.engine.Position;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.type.EventPublishDataReport;
import com.github.caay2000.metropolis.event.type.EventStoreCollectData;
import com.github.caay2000.metropolis.event.SystemEventBus;

@RunWith(MockitoJUnitRunner.class)
public class DataStorageEventHandlerTest {

    private static final long ANY_TIME = 1234l;
    private static final String ANY_SOURCE = "testSource";
    private static final Position ANY_POSITION = new Position(0d, 0d);
    private static final CollectedData SOME_COLLECTED_DATA = new CollectedData(ANY_POSITION, 1);

    private final EventBus eventBus = new SystemEventBus();

    @Mock
    private DataStorage dataStorage;

    @Test
    public void eventStoreCollectDataStoresData() {

        new DataStorageEventHandler(eventBus, dataStorage);

        eventBus.publish(new EventStoreCollectData(ANY_TIME, SOME_COLLECTED_DATA));

        verify(dataStorage).store(eq(SOME_COLLECTED_DATA));
    }

    @Test
    public void eventPublishReport() {
        new DataStorageEventHandler(eventBus, dataStorage);

        eventBus.publish(new EventPublishDataReport(ANY_TIME, ANY_POSITION, ANY_SOURCE));

        verify(dataStorage).publishReport(eq(ANY_TIME), eq(ANY_POSITION), eq(ANY_SOURCE));
    }
}