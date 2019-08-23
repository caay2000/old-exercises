package com.github.caay2000.metropolis.model.reporter;

import com.github.caay2000.metropolis.model.provider.JsonSerializer;

public class SystemReporter implements Reporter {

    private final JsonSerializer serializer;

    public SystemReporter(JsonSerializer serializer) {
        this.serializer = serializer;
    }

    @Override
    public void report(Report report) {
        System.out.println(serializer.serialize(report));
    }
}
