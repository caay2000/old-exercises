package com.github.caay2000.metropolis.route;

import com.github.caay2000.metropolis.utils.ReflectionTestUtils;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.type.EventCollectInstantData;
import com.github.caay2000.metropolis.simulation.Simulation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StationRangeTest {

    private static final Position NOT_IN_RANGE_POSITION = new Position(0d, 0d);
    private static final Position IN_RANGE_POSITION = Station.BUCKINGHAM.getPosition();
    private static final int ANY_DISTANCE_RANGE = 100;
    private static final Station VISITED_STATION = Station.BUCKINGHAM;
    private static final long ANY_TIME = 1234;

    @Mock
    private Simulation simulation;

    @Mock
    private EventBus eventBus;

    @Mock
    private Route route;

    @Captor
    private ArgumentCaptor<EventCollectInstantData> eventCollectInstantDataCaptor;

    @Test
    public void notInRange() {

        StationRange testee = new StationRange(simulation, eventBus);

        testee.check(NOT_IN_RANGE_POSITION, route, ANY_DISTANCE_RANGE);

        verify(eventBus, never()).publish(eventCollectInstantDataCaptor.capture());
    }

    @Test
    public void alreadyVisited() throws Exception {
        StationRange testee = new StationRange(simulation, eventBus);
        ReflectionTestUtils.setField(testee, "stationsVisited", Stream.of(VISITED_STATION).collect(Collectors.toSet()));

        testee.check(IN_RANGE_POSITION, route, ANY_DISTANCE_RANGE);

        verify(eventBus, never()).publish(eventCollectInstantDataCaptor.capture());
    }

    @Test
    public void stationInRangePublishCollectInstantDataEvent() {
        when(simulation.getSimulationTime()).thenReturn(ANY_TIME);
        StationRange testee = new StationRange(simulation, eventBus);

        testee.check(IN_RANGE_POSITION, route, ANY_DISTANCE_RANGE);

        verify(eventBus).publish(eventCollectInstantDataCaptor.capture());
        EventCollectInstantData eventCollectInstantData = eventCollectInstantDataCaptor.getValue();
        Assert.assertEquals(ANY_TIME, eventCollectInstantData.getEventTime());
        Assert.assertEquals(IN_RANGE_POSITION, eventCollectInstantData.getPosition());
        Assert.assertEquals(VISITED_STATION.getName(), eventCollectInstantData.getSource());
    }

    @Test
    public void endOfRouteClearStationsVisited() throws Exception {
        StationRange testee = new StationRange(simulation, eventBus);
        ReflectionTestUtils.setField(testee, "stationsVisited", Stream.of(VISITED_STATION).collect(Collectors.toSet()));
        when(route.isEndOfRoute()).thenReturn(true);

        testee.check(IN_RANGE_POSITION, route, ANY_DISTANCE_RANGE);

        HashSet stationsvisited = ReflectionTestUtils.getField(testee, "stationsVisited", HashSet.class);
        Assert.assertEquals(0, stationsvisited.size());
    }


}