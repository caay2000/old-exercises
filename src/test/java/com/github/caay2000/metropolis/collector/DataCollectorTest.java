package com.github.caay2000.metropolis.collector;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.github.caay2000.metropolis.engine.Position;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.EventCollectData;
import com.github.caay2000.metropolis.event.EventStoreCollectData;
import com.github.caay2000.metropolis.event.EventType;
import com.github.caay2000.metropolis.simulation.Simulation;

@RunWith(MockitoJUnitRunner.class)
public class DataCollectorTest {

    private static final long ANY_TIME = 1235l;
    private static final Position ANY_POSITION = new Position(0d, 0d);
    private static final EventCollectData ANY_COLLECT_DATA_EVENT = new EventCollectData(1l, ANY_POSITION);

    @Mock
    private Simulation simulation;

    @Mock
    private EventBus systemEventBus;

    @Mock
    private DataMeter dataMeter;

    @Captor
    private ArgumentCaptor<EventStoreCollectData> eventStoreCollectDataCaptor;

    private DataCollector testee;

    @Before
    public void setUp() {
        testee = new DataCollector(simulation, systemEventBus, dataMeter);
    }

    @Test
    public void collectDataEventPublishStoreCollectDataEvent() {

        when(dataMeter.getValue()).thenReturn(1);
        when(simulation.getSimulationTime()).thenReturn(ANY_TIME);

        testee.collectHandler(ANY_COLLECT_DATA_EVENT);

        EventStoreCollectData eventStoreCollectData = retrieveEventStoreCollectData();
        Assert.assertEquals(EventType.STORE_COLLECT_DATA, eventStoreCollectData.getType());
        Assert.assertEquals(ANY_TIME, eventStoreCollectData.getEventTime());
    }

    @Test
    public void collectDataEventGoodLevel() {

        int pollutionValue = 1;
        when(dataMeter.getValue()).thenReturn(pollutionValue);

        CollectedData collectedData = testee.collect(ANY_COLLECT_DATA_EVENT);

        assertCollectData(collectedData, ANY_POSITION, pollutionValue, PollutionLevel.GOOD);
    }

    @Test
    public void collectDataEventModerateLevel() {

        int pollutionValue = 51;
        when(dataMeter.getValue()).thenReturn(pollutionValue);

        CollectedData collectedData = testee.collect(ANY_COLLECT_DATA_EVENT);

        assertCollectData(collectedData, ANY_POSITION, pollutionValue, PollutionLevel.MODERATE);
    }

    @Test
    public void collectDataEventUSGLevel() {

        int pollutionValue = 101;
        when(dataMeter.getValue()).thenReturn(pollutionValue);

        CollectedData collectedData = testee.collect(ANY_COLLECT_DATA_EVENT);

        assertCollectData(collectedData, ANY_POSITION, pollutionValue, PollutionLevel.USG);
    }

    @Test
    public void collectDataEventUnhealthyLevel() {

        int pollutionValue = 151;
        when(dataMeter.getValue()).thenReturn(pollutionValue);

        CollectedData collectedData = testee.collect(ANY_COLLECT_DATA_EVENT);

        assertCollectData(collectedData, ANY_POSITION, pollutionValue, PollutionLevel.UNHEALTHY);
    }

    private EventStoreCollectData retrieveEventStoreCollectData() {
        verify(systemEventBus).publish(eventStoreCollectDataCaptor.capture());
        return eventStoreCollectDataCaptor.getValue();
    }

    private void assertCollectData(CollectedData collectedData, Position position, int pollutionValue, PollutionLevel pollutionLevel) {
        Assert.assertEquals(position, collectedData.getPosition());
        Assert.assertEquals(pollutionValue, collectedData.getPollutionValue());
        Assert.assertEquals(pollutionLevel, collectedData.getPollutionLevel());
    }
}