package com.github.caay2000.metropolis.reporter;

import java.io.PrintStream;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.EventHandler;
import com.github.caay2000.metropolis.exception.MetropolisException;

public class SystemReporter implements Reporter {

    private final EventHandler eventHandler;

    private final PrintStream printStream;
    private final ObjectMapper serializer;

    public SystemReporter(EventBus eventBus, PrintStream printStream) {
        this.eventHandler = new ReporterEventHandler(eventBus, this);

        this.printStream = printStream;
        this.serializer = new ObjectMapper();
    }

    @Override
    public void printReport(Report report) {

        printStream.println(serialize(report));
    }

    private String serialize(Report value) {
        try {
            return this.serializer.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new MetropolisException("error serializing", e);
        }
    }
}
