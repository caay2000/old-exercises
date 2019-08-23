package com.github.caay2000.metropolis.storage;

import static org.mockito.Mockito.verify;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.github.caay2000.metropolis.collector.CollectedData;
import com.github.caay2000.metropolis.collector.PollutionLevel;
import com.github.caay2000.metropolis.engine.Position;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.EventPublishReport;
import com.github.caay2000.metropolis.event.EventStoreCollectData;
import com.github.caay2000.metropolis.reporter.Report;
import com.github.caay2000.metropolis.reporter.Reporter;

@RunWith(MockitoJUnitRunner.class)
public class DataStorageTest {

    private static final long ANY_TIME = 1234l;
    private static final String ANY_SOURCE = "testSource";
    private static final CollectedData ANY_COLLECTED_DATA = new CollectedData(new Position(0d, 0d), 1);
    private static final Position ANY_POSITION = new Position(0d, 0d);
    private static final EventPublishReport ANY_PUBLISH_REPORT_EVENT = new EventPublishReport(ANY_TIME, ANY_POSITION, ANY_SOURCE);

    @Mock
    private EventBus eventBus;

    @Mock
    private Reporter reporter;

    @Captor
    private ArgumentCaptor<Report> reportCaptor;

    @Test
    public void dataStoredCorrectly() {

        DataStorage testee = new DataStorage(eventBus, reporter);

        testee.store(new EventStoreCollectData(ANY_TIME, ANY_COLLECTED_DATA));

        Report report = retrieveStoredData(testee);
        assertStoredData(report, ANY_TIME, ANY_POSITION, PollutionLevel.GOOD, ANY_SOURCE);
    }

    @Test
    public void publishReportPublishedCorrectly() {

        DataStorage testee = new DataStorage(eventBus, reporter);
        testee.store(new EventStoreCollectData(ANY_TIME, new CollectedData(ANY_POSITION, 50)));
        testee.store(new EventStoreCollectData(ANY_TIME, new CollectedData(ANY_POSITION, 300)));

        testee.publishReport(new EventPublishReport(ANY_TIME, ANY_POSITION, ANY_SOURCE));

        Report report = retrievePublishedReport(testee);
        assertStoredData(report, ANY_TIME, ANY_POSITION, PollutionLevel.UNHEALTHY, ANY_SOURCE);
    }

    @Test
    public void publishReportResetsMeasurements() {

        DataStorage testee = new DataStorage(eventBus, reporter);
        testee.store(new EventStoreCollectData(ANY_TIME, new CollectedData(ANY_POSITION, 50)));
        testee.store(new EventStoreCollectData(ANY_TIME, new CollectedData(ANY_POSITION, 300)));

        testee.publishReport(new EventPublishReport(ANY_TIME, ANY_POSITION, ANY_SOURCE));

        testee.store(new EventStoreCollectData(ANY_TIME, new CollectedData(ANY_POSITION, 1)));
        Report report = retrievePublishedReport(testee);
        assertStoredData(report, ANY_TIME, ANY_POSITION, PollutionLevel.GOOD, ANY_SOURCE);
    }

    private void assertStoredData(Report report, long time, Position position, PollutionLevel pollutionLevel, String source) {
        Assert.assertEquals(time, report.getTimestamp());
        Assert.assertEquals(position.getLat(), report.getLocation().getLat(), 0d);
        Assert.assertEquals(position.getLng(), report.getLocation().getLng(), 0d);
        Assert.assertEquals(pollutionLevel.name(), report.getLevel());
        Assert.assertEquals(source, report.getSource());
    }

    private Report retrievePublishedReport(DataStorage testee) {
        verify(reporter).report(reportCaptor.capture());
        return reportCaptor.getValue();
    }

    private Report retrieveStoredData(DataStorage testee) {
        testee.publishReport(ANY_PUBLISH_REPORT_EVENT);
        return retrievePublishedReport(testee);
    }
}
