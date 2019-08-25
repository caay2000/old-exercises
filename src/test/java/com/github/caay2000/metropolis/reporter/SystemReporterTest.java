package com.github.caay2000.metropolis.reporter;

import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.reporter.type.DataReport;
import com.github.caay2000.metropolis.route.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.PrintStream;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SystemReporterTest {

    private static final long ANY_TIME = 1234;
    private static final String ANY_LEVEL = "level";
    private static final String ANY_SOURCE = "test_source";
    private static final Position ANY_POSITION = new Position(0d, 0d);
    private static final Report ANY_REPORT = new DataReport(ANY_TIME, ANY_POSITION, ANY_LEVEL, ANY_SOURCE);

    @Mock
    private EventBus eventBus;

    @Mock
    private PrintStream printStream;

    @Test
    public void test() {
        String expectedResult = String.format("{\"timestamp\":%s,\"location\":{\"lat\":%s,\"lng\":%s},\"level\":\"%s\",\"source\":\"%s\"}",
                ANY_TIME,
                ANY_POSITION.getLat(),
                ANY_POSITION.getLng(),
                ANY_LEVEL,
                ANY_SOURCE);

        Reporter reporter = new SystemReporter(eventBus, printStream);

        reporter.printReport(ANY_REPORT);

        verify(printStream).println(eq(expectedResult));
    }

}