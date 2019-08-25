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
import com.github.caay2000.metropolis.event.type.EventStoreCollectData;
import com.github.caay2000.metropolis.event.EventType;
import com.github.caay2000.metropolis.simulation.Simulation;

@RunWith(MockitoJUnitRunner.class)
public class DataCollectorTest {

    private static final long ANY_TIME = 1234l;
    private static final Position ANY_POSITION = new Position(0d, 0d);

    @Mock
    private Simulation simulation;

    @Mock
    private EventBus eventBus;

    @Mock
    private DataMeter dataMeter;

    @Captor
    private ArgumentCaptor<EventStoreCollectData> eventStoreCollectDataCaptor;

    private DataCollector testee;

    @Before
    public void setUp() {
        testee = new DataCollector(simulation, eventBus, dataMeter);
    }

    @Test
    public void collectDataEventPublishStoreCollectedDataEvent() {

        int pollutionValue = 1;
        when(simulation.getSimulationTime()).thenReturn(ANY_TIME);
        when(dataMeter.getValue()).thenReturn(pollutionValue);

        testee.collect(ANY_POSITION);

        EventStoreCollectData eventStoreCollectData = retrieveEventStoreCollectData();
        Assert.assertEquals(ANY_TIME, eventStoreCollectData.getEventTime());
        Assert.assertEquals(EventType.STORE_COLLECT_DATA, eventStoreCollectData.getType());
    }

    @Test
    public void collectDataGoodLevel() {

        int pollutionValue = 1;
        when(dataMeter.getValue()).thenReturn(pollutionValue);

        testee.collect(ANY_POSITION);

        assertCollectData(retrieveEventStoreCollectData().getCollectedData(), ANY_POSITION, pollutionValue, PollutionLevel.GOOD);
    }

    @Test
    public void collectDataModerateLevel() {

        int pollutionValue = 51;
        when(dataMeter.getValue()).thenReturn(pollutionValue);

        testee.collect(ANY_POSITION);

        assertCollectData(retrieveEventStoreCollectData().getCollectedData(), ANY_POSITION, pollutionValue, PollutionLevel.MODERATE);
    }

    @Test
    public void collectDataUSGLevel() {

        int pollutionValue = 101;
        when(dataMeter.getValue()).thenReturn(pollutionValue);

        testee.collect(ANY_POSITION);

        assertCollectData(retrieveEventStoreCollectData().getCollectedData(), ANY_POSITION, pollutionValue, PollutionLevel.USG);
    }

    @Test
    public void collectDataUnhealthyLevel() {

        int pollutionValue = 151;
        when(dataMeter.getValue()).thenReturn(pollutionValue);

        testee.collect(ANY_POSITION);

        assertCollectData(retrieveEventStoreCollectData().getCollectedData(), ANY_POSITION, pollutionValue, PollutionLevel.UNHEALTHY);
    }

    private EventStoreCollectData retrieveEventStoreCollectData() {
        verify(eventBus).publish(eventStoreCollectDataCaptor.capture());
        return eventStoreCollectDataCaptor.getValue();
    }

    private void assertCollectData(CollectedData collectedData, Position position, int pollutionValue, PollutionLevel pollutionLevel) {
        Assert.assertEquals(position, collectedData.getPosition());
        Assert.assertEquals(pollutionValue, collectedData.getPollutionValue());
        Assert.assertEquals(pollutionLevel, collectedData.getPollutionLevel());
    }
}