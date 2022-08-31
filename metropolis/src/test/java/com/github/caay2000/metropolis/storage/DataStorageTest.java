package com.github.caay2000.metropolis.storage;

import com.github.caay2000.metropolis.collector.CollectedData;
import com.github.caay2000.metropolis.collector.PollutionLevel;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.EventType;
import com.github.caay2000.metropolis.event.type.EventOutputReport;
import com.github.caay2000.metropolis.reporter.Reporter;
import com.github.caay2000.metropolis.reporter.type.DataReport;
import com.github.caay2000.metropolis.route.Position;
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
public class DataStorageTest {

    private static final long ANY_TIME = 1234;
    private static final String ANY_SOURCE = "testSource";
    private static final Position ANY_POSITION = new Position(0d, 0d);

    @Mock
    private EventBus eventBus;

    @Mock
    private Simulation simulation;

    @Mock
    private Reporter reporter;

    @Captor
    private ArgumentCaptor<EventOutputReport> eventOutputReportCaptor;

    @Test
    public void dataStoredCorrectlyOneValue() throws Exception {

        DataStorage testee = new DataStorage(simulation, eventBus);

        testee.store(new CollectedData(ANY_POSITION, 10));

        Object data = getField(testee, "data", Object.class);
        Assert.assertEquals(1, (int) getField(data, "measurements", Integer.class));
        Assert.assertEquals(10, (int) getField(data, "totalPollution", Integer.class));
    }

    @Test
    public void dataStoredCorrectlyMultipleValues() throws Exception {

        DataStorage testee = new DataStorage(simulation, eventBus);

        testee.store(new CollectedData(ANY_POSITION, 10));
        testee.store(new CollectedData(ANY_POSITION, 10));

        Object data = getField(testee, "data", Object.class);
        Assert.assertEquals(2, (int) getField(data, "measurements", Integer.class));
        Assert.assertEquals(20, (int) getField(data, "totalPollution", Integer.class));
    }

    @Test
    public void publishReportPublishedCorrectly() {

        when(simulation.getSimulationTime()).thenReturn(ANY_TIME);

        DataStorage testee = new DataStorage(simulation, eventBus);
        testee.store(new CollectedData(ANY_POSITION, 100));
        testee.store(new CollectedData(ANY_POSITION, 100));

        testee.publishReport(ANY_TIME, ANY_POSITION, ANY_SOURCE);

        EventOutputReport eventOutputReport = retrieveEventOutputReport();
        Assert.assertEquals(ANY_TIME, eventOutputReport.getEventTime());
        Assert.assertEquals(EventType.OUTPUT_REPORT, eventOutputReport.getType());

        Assert.assertEquals(ANY_POSITION.getLat(), ((DataReport) eventOutputReport.getReport()).getLocation().getLat(), 0d);
        Assert.assertEquals(ANY_POSITION.getLng(), ((DataReport) eventOutputReport.getReport()).getLocation().getLng(), 0d);
        Assert.assertEquals(PollutionLevel.MODERATE.name(), ((DataReport) eventOutputReport.getReport()).getLevel());
        Assert.assertEquals(ANY_SOURCE, ((DataReport) eventOutputReport.getReport()).getSource());
    }

    @Test
    public void publishReportResetsMeasurements() throws Exception {

        DataStorage testee = new DataStorage(simulation, eventBus);
        testee.store(new CollectedData(ANY_POSITION, 100));

        testee.publishReport(ANY_TIME, ANY_POSITION, ANY_SOURCE);

        Object data = getField(testee, "data", Object.class);
        Assert.assertEquals(0, (int) getField(data, "measurements", Integer.class));
        Assert.assertEquals(0, (int) getField(data, "totalPollution", Integer.class));
    }

    private EventOutputReport retrieveEventOutputReport() {
        verify(eventBus).publish(eventOutputReportCaptor.capture());
        return eventOutputReportCaptor.getValue();
    }
}
