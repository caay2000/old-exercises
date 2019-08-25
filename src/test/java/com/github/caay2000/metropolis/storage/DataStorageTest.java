package com.github.caay2000.metropolis.storage;

import static com.github.caay2000.metropolis.ReflectionTestUtils.getField;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.github.caay2000.metropolis.collector.CollectedData;
import com.github.caay2000.metropolis.collector.PollutionLevel;
import com.github.caay2000.metropolis.engine.Position;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.SystemEventBus;
import com.github.caay2000.metropolis.reporter.DataReport;
import com.github.caay2000.metropolis.reporter.Reporter;

@RunWith(MockitoJUnitRunner.class)
public class DataStorageTest {

    private static final long ANY_TIME = 1234l;
    private static final String ANY_SOURCE = "testSource";
    private static final Position ANY_POSITION = new Position(0d, 0d);

    private EventBus eventBus = new SystemEventBus();

    @Mock
    private Reporter reporter;

    @Test
    public void dataStoredCorrectlyOneValue() throws Exception {

        DataStorage testee = new DataStorage(eventBus, reporter);

        testee.store(new CollectedData(ANY_POSITION, 10));

        Object data = getField(testee, "data", Object.class);
        Assert.assertEquals(1, (int) getField(data, "measurements", Integer.class));
        Assert.assertEquals(10, (int) getField(data, "totalPollution", Integer.class));
    }

    @Test
    public void dataStoredCorrectlyMultipleValues() throws Exception {

        DataStorage testee = new DataStorage(eventBus, reporter);

        testee.store(new CollectedData(ANY_POSITION, 10));
        testee.store(new CollectedData(ANY_POSITION, 10));

        Object data = getField(testee, "data", Object.class);
        Assert.assertEquals(2, (int) getField(data, "measurements", Integer.class));
        Assert.assertEquals(20, (int) getField(data, "totalPollution", Integer.class));
    }

    @Test
    public void publishReportPublishedCorrectly() {

        DataStorage testee = new DataStorage(eventBus, reporter);
        testee.store(new CollectedData(ANY_POSITION, 100));
        testee.store(new CollectedData(ANY_POSITION, 100));

        testee.publishReport(ANY_TIME, ANY_POSITION, ANY_SOURCE);

        verify(reporter).publishReport(eq(new DataReport(ANY_TIME, ANY_POSITION, PollutionLevel.MODERATE.name(), ANY_SOURCE)));
    }

    @Test
    public void publishReportResetsMeasurements() throws Exception {

        DataStorage testee = new DataStorage(eventBus, reporter);
        testee.store(new CollectedData(ANY_POSITION, 100));

        testee.publishReport(ANY_TIME, ANY_POSITION, ANY_SOURCE);

        Object data = getField(testee, "data", Object.class);
        Assert.assertEquals(0, (int) getField(data, "measurements", Integer.class));
        Assert.assertEquals(0, (int) getField(data, "totalPollution", Integer.class));
    }
}
