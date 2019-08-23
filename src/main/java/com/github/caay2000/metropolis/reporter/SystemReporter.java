package com.github.caay2000.metropolis.reporter;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.caay2000.metropolis.exception.MetropolisException;

public class SystemReporter implements Reporter {

    private final ObjectMapper serializer;

    public SystemReporter() {
        this.serializer = new ObjectMapper();
    }

    @Override
    public void report(Report report) {

        System.out.println(serialize(report));
    }

    private String serialize(Report value) {
        try {
            return this.serializer.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new MetropolisException("error serializing", e);
        }
    }
}
