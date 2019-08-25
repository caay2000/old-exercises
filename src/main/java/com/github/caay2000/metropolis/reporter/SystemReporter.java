package com.github.caay2000.metropolis.reporter;

import java.io.PrintStream;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.caay2000.metropolis.exception.MetropolisException;

public class SystemReporter implements Reporter {

    private final PrintStream printStream;
    private final ObjectMapper serializer;

    public SystemReporter(PrintStream printStream) {
        this.printStream = printStream;
        this.serializer = new ObjectMapper();
    }

    @Override
    public void publishReport(Report report) {

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
