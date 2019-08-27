package com.github.caay2000.metropolis;

import com.github.caay2000.metropolis.collector.DataMeter;
import com.github.caay2000.metropolis.event.Event;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.EventType;
import com.github.caay2000.metropolis.event.type.*;
import com.github.caay2000.metropolis.reporter.Source;
import com.github.caay2000.metropolis.route.MovementEngine;
import com.github.caay2000.metropolis.route.Position;
import com.github.caay2000.metropolis.route.RouteData;
import com.github.caay2000.metropolis.simulation.Simulation;
import com.github.caay2000.metropolis.utils.ReflectionTestUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RobotTest {

    private static final Position POINT_1 = new Position(41.37721d, 2.08569d);
    private static final Position POINT_MIDDLE = new Position(41.38095d, 2.12572d);
    private static final Position POINT_2 = new Position(41.38802d, 2.16921d);
    private static final String POLYLINE_THREE_POINTS = "qnp{FqjvKqbA_iO";

    private static final double DELTA = 0.0d;

    private static final long ANY_TIME = 1234;
    private static final int ANY_DURATION = 1;
    private static final int REPORT_DURATION = 15 * 60 + 1;
    private static final double ANY_SPEED = 6d;
    private static final double SHORT_DISTANCE = 1d;
    private static final int SHORT_DISTANCE_INT = 1;
    private static final double LONG_DISTANCE = 100d;
    private static final double REALLY_LONG_DISTANCE = 1000000d;

    @Mock
    private Simulation simulation;

    @Mock
    private EventBus eventBus;

    @Mock
    private MovementEngine engine;

    @Mock
    private DataMeter dataMeter;

    @Mock
    private PrintStream printStream;

    @Captor
    private ArgumentCaptor<Event> eventCaptor;

    private RobotConfiguration robotConfiguration;
    private Robot testee;

    @Before
    public void setUp() throws Exception {
        robotConfiguration = new RobotConfiguration(dataMeter, printStream);
        testee = new Robot(simulation, eventBus, robotConfiguration);
        testee.start(POLYLINE_THREE_POINTS);
        ReflectionTestUtils.setField(testee, "engine", engine);

        when(simulation.getSimulationTime()).thenReturn(ANY_TIME);
    }

    @Test
    public void runMakesRobotStartMoving() throws Exception {

        run();

        verify(engine).move(eq(POINT_1), eq(POINT_2), eq(Integer.valueOf(robotConfiguration.getCollectDataDistance()).doubleValue()));
    }

    @Test
    public void eachMoveSimulationIsUpdated() throws Exception {

        when(engine.move(eq(POINT_1), eq(POINT_2), eq(Integer.valueOf(robotConfiguration.getCollectDataDistance()).doubleValue())))
                .thenReturn(new RouteData(POINT_1, POINT_2, LONG_DISTANCE, ANY_DURATION, ANY_SPEED));

        run();

        verify(simulation).updateSimulation(1);
    }

    @Test
    public void eachMoveRobotRouteIsUpdated() throws Exception {

        when(engine.move(eq(POINT_1), eq(POINT_2), eq(Integer.valueOf(robotConfiguration.getCollectDataDistance()).doubleValue())))
                .thenReturn(new RouteData(POINT_1, POINT_2, LONG_DISTANCE, ANY_DURATION, ANY_SPEED));

        run();

        EventStoreRouteData eventStoreRouteData = retrievePublishedEvent(EventType.STORE_ROUTE_DATA, EventStoreRouteData.class);
        Assert.assertEquals(ANY_TIME, eventStoreRouteData.getEventTime());
        Assert.assertEquals(POINT_1, eventStoreRouteData.getRouteData().getOrigin());
        Assert.assertEquals(POINT_2, eventStoreRouteData.getRouteData().getDestination());
        Assert.assertEquals(LONG_DISTANCE, eventStoreRouteData.getRouteData().getDistance(), DELTA);
        Assert.assertEquals(ANY_DURATION, eventStoreRouteData.getRouteData().getTime());
        Assert.assertEquals(ANY_SPEED, eventStoreRouteData.getRouteData().getSpeed(), DELTA);
    }

    @Test
    public void shortDistanceDoesNotCollectData() throws Exception {

        when(engine.move(eq(POINT_1), eq(POINT_2), eq(Integer.valueOf(robotConfiguration.getCollectDataDistance()).doubleValue())))
                .thenReturn(new RouteData(POINT_1, POINT_2, SHORT_DISTANCE, ANY_DURATION, ANY_SPEED));

        run();

        EventCollectData eventCollectData = retrievePublishedEvent(EventType.COLLECT_DATA, EventCollectData.class);
        Assert.assertNull(eventCollectData);

    }

    @Test
    public void longDistanceDoCollectData() throws Exception {

        when(engine.move(eq(POINT_1), eq(POINT_2), eq(Integer.valueOf(robotConfiguration.getCollectDataDistance()).doubleValue())))
                .thenReturn(new RouteData(POINT_1, POINT_2, LONG_DISTANCE, ANY_DURATION, ANY_SPEED));

        run();

        EventCollectData eventCollectData = retrievePublishedEvent(EventType.COLLECT_DATA, EventCollectData.class);
        Assert.assertEquals(ANY_TIME, eventCollectData.getEventTime());
        Assert.assertNotNull(eventCollectData.getPosition());
    }

    @Test
    public void eachPeriodReportIsPublished() throws Exception {

        when(engine.move(eq(POINT_1), eq(POINT_2), eq(Integer.valueOf(robotConfiguration.getCollectDataDistance()).doubleValue())))
                .thenReturn(new RouteData(POINT_1, POINT_2, LONG_DISTANCE, REPORT_DURATION, ANY_SPEED));

        run();

        EventPublishDataReport eventPublishDataReport = retrievePublishedEvent(EventType.PUBLISH_DATA_REPORT, EventPublishDataReport.class);
        Assert.assertEquals(ANY_TIME, eventPublishDataReport.getEventTime());
        Assert.assertNotNull(eventPublishDataReport.getPosition());
        Assert.assertEquals(Source.ROBOT.getValue(), eventPublishDataReport.getSource());
    }

    @Test
    public void eachMovePublishStationReportEvent() throws Exception {

        when(engine.move(eq(POINT_1), eq(POINT_2), eq(Integer.valueOf(robotConfiguration.getCollectDataDistance()).doubleValue())))
                .thenReturn(new RouteData(POINT_1, POINT_2, LONG_DISTANCE, ANY_DURATION, ANY_SPEED));

        run();

        EventStationInRange eventStationInRange = retrievePublishedEvent(EventType.STATION_IN_RANGE, EventStationInRange.class);
        Assert.assertEquals(ANY_TIME, eventStationInRange.getEventTime());
        Assert.assertNotNull(eventStationInRange.getPosition());
        Assert.assertNotNull(eventStationInRange.getRoute());
        Assert.assertEquals(robotConfiguration.getDistanceRangeStation(), eventStationInRange.getDistanceRange());
    }

    @Test
    public void movementNotFinishedInDestinationRecursiveCall() throws Exception {

        robotConfiguration.setCollectDataDistance(SHORT_DISTANCE_INT);
        ReflectionTestUtils.setField(testee, "nextCollectDataDistance", SHORT_DISTANCE_INT);

        when(engine.move(eq(POINT_1), eq(POINT_2), eq(SHORT_DISTANCE)))
                .thenReturn(new RouteData(POINT_1, POINT_MIDDLE, SHORT_DISTANCE, ANY_DURATION, ANY_SPEED));
        when(engine.move(eq(POINT_MIDDLE), eq(POINT_2), eq(SHORT_DISTANCE)))
                .thenReturn(new RouteData(POINT_MIDDLE, POINT_2, SHORT_DISTANCE, ANY_DURATION, ANY_SPEED));

        run();

        verify(engine).move(eq(POINT_1), eq(POINT_2), eq(SHORT_DISTANCE));
        verify(engine).move(eq(POINT_MIDDLE), eq(POINT_2), eq(SHORT_DISTANCE));
    }

    @Test
    public void movementFinishedInDestinationPublishRouteReportEvent() throws Exception {

        when(engine.move(eq(POINT_1), eq(POINT_2), eq(LONG_DISTANCE)))
                .thenReturn(new RouteData(POINT_1, POINT_2, LONG_DISTANCE, ANY_DURATION, ANY_SPEED));

        run();

        EventPublishRouteReport eventPublishRouteReport = retrievePublishedEvent(EventType.PUBLISH_ROUTE_REPORT, EventPublishRouteReport.class);
        Assert.assertEquals(ANY_TIME, eventPublishRouteReport.getEventTime());
        Assert.assertNotNull(eventPublishRouteReport.getPosition());
    }

    @Test
    public void publishInstantReportPublishesEvent() throws Exception {


        testee.publishInstantReport();

        EventCollectInstantData eventCollectInstantData = retrievePublishedEvent(EventType.COLLECT_INSTANT_DATA, EventCollectInstantData.class);
        Assert.assertEquals(ANY_TIME, eventCollectInstantData.getEventTime());
        Assert.assertNotNull(eventCollectInstantData.getPosition());
    }

    private int retrievePublishedEventCount(EventType eventType) {
        verify(eventBus, atLeastOnce()).publish(eventCaptor.capture());
        return (int) eventCaptor.getAllValues().stream()
                .filter(event -> eventType.equals(event.getType()))
                .count();
    }

    private <T> T retrievePublishedEvent(EventType eventType, Class<T> clazz) {
        verify(eventBus, atLeastOnce()).publish(eventCaptor.capture());
        List<Event> collect = eventCaptor.getAllValues().stream()
                .filter(event -> eventType.equals(event.getType()))
                .collect(Collectors.toList());
        if (collect.size() == 0) {
            return null;
        }
        return (T) collect.get(0).to(clazz);
    }

    private void run() {
        try {
            testee.run();
        } catch (Exception e) {
            //swallow
        }
    }

}