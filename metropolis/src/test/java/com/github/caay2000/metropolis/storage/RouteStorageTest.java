package com.github.caay2000.metropolis.storage;

import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.EventType;
import com.github.caay2000.metropolis.event.type.EventOutputReport;
import com.github.caay2000.metropolis.reporter.Reporter;
import com.github.caay2000.metropolis.reporter.type.RouteReport;
import com.github.caay2000.metropolis.route.Position;
import com.github.caay2000.metropolis.route.RouteData;
import com.github.caay2000.metropolis.simulation.Simulation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.github.caay2000.metropolis.utils.ReflectionTestUtils.getField;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RouteStorageTest {

    private static final long ANY_TIME = 1234;
    private static final String ANY_SOURCE = "route";
    private static final Position ANY_POSITION = new Position(0d, 0d);
    private static final double DELTA = 0d;

    @Mock
    private EventBus eventBus;

    @Mock
    private Simulation simulation;

    @Mock
    private Reporter reporter;

    @Captor
    private ArgumentCaptor<EventOutputReport> eventOutputReportCaptor;

    @Test
    public void routeDataStoredCorrectlyOneValue() throws Exception {

        RouteStorage testee = new RouteStorage(simulation, eventBus);

        testee.store(new RouteData(ANY_POSITION, ANY_POSITION, 10, 10, 1));

        Object data = getField(testee, "data", Object.class);
        Assert.assertEquals(10d, (double) getField(data, "distanceTraveled", Double.class), 0d);
        Assert.assertEquals(10, (int) getField(data, "timeElapsed", Integer.class));
    }

    @Test
    public void routeDataStoredCorrectlyMultipleValues() throws Exception {

        RouteStorage testee = new RouteStorage(simulation, eventBus);

        testee.store(new RouteData(ANY_POSITION, ANY_POSITION, 10, 10, 1));
        testee.store(new RouteData(ANY_POSITION, ANY_POSITION, 10, 10, 1));

        Object data = getField(testee, "data", Object.class);
        Assert.assertEquals(20d, (double) getField(data, "distanceTraveled", Double.class), DELTA);
        Assert.assertEquals(20, (int) getField(data, "timeElapsed", Integer.class));
    }

    @Test
    public void publishReportPublishedCorrectly() {

        when(simulation.getSimulationTime()).thenReturn(ANY_TIME);

        RouteStorage testee = new RouteStorage(simulation, eventBus);
        testee.store(new RouteData(ANY_POSITION, ANY_POSITION, 10, 10, 1));
        testee.store(new RouteData(ANY_POSITION, ANY_POSITION, 10, 10, 1));

        testee.publishReport(ANY_TIME, ANY_POSITION);

        EventOutputReport eventOutputReport = retrieveEventOutputReport();
        Assert.assertEquals(ANY_TIME, eventOutputReport.getEventTime());
        Assert.assertEquals(EventType.OUTPUT_REPORT, eventOutputReport.getType());

        Assert.assertEquals(ANY_POSITION.getLat(), ((RouteReport) eventOutputReport.getReport()).getLocation().getLat(), 0d);
        Assert.assertEquals(ANY_POSITION.getLng(), ((RouteReport) eventOutputReport.getReport()).getLocation().getLng(), 0d);
        Assert.assertEquals("20", ((RouteReport) eventOutputReport.getReport()).getDistanceTravelled());
        Assert.assertEquals("20", ((RouteReport) eventOutputReport.getReport()).getTimeElapsed());
        Assert.assertEquals("1", ((RouteReport) eventOutputReport.getReport()).getAverageSpeed());
        Assert.assertEquals(ANY_SOURCE, ((RouteReport) eventOutputReport.getReport()).getSource());
    }

    @Test
    public void publishReportResetsMeasurements() throws Exception {

        RouteStorage testee = new RouteStorage(simulation, eventBus);
        testee.store(new RouteData(ANY_POSITION, ANY_POSITION, 10, 10, 10));

        testee.publishReport(ANY_TIME, ANY_POSITION);

        Object data = getField(testee, "data", Object.class);
        Assert.assertEquals(0d, (double) getField(data, "distanceTraveled", Double.class), DELTA);
        Assert.assertEquals(0, (int) getField(data, "timeElapsed", Integer.class));
    }

    private EventOutputReport retrieveEventOutputReport() {
        verify(eventBus).publish(eventOutputReportCaptor.capture());
        return eventOutputReportCaptor.getValue();
    }
}
