package com.github.caay2000.metropolis.reporter;

import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.SystemEventBus;
import com.github.caay2000.metropolis.event.type.EventOutputReport;
import com.github.caay2000.metropolis.reporter.type.DataReport;
import com.github.caay2000.metropolis.route.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SystemReporterEventHandlerTest {

    private static final long ANY_TIME = 1234;
    private static final String ANY_LEVEL = "level";
    private static final String ANY_SOURCE = "test_source";
    private static final Position ANY_POSITION = new Position(0d, 0d);
    private static final Report ANY_REPORT = new DataReport(ANY_TIME, ANY_POSITION, ANY_LEVEL, ANY_SOURCE);

    private final EventBus eventBus = new SystemEventBus();

    @Mock
    private Reporter reporter;

    @Test
    public void eventStoreRouteDataStoresData() {

        new SystemReporterEventHandler(eventBus, reporter);

        eventBus.publish(new EventOutputReport(ANY_TIME, ANY_REPORT));

        verify(reporter).printReport(eq(ANY_REPORT));
    }

}