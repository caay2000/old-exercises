package com.github.caay2000.metropolis.event.type;

import com.github.caay2000.metropolis.event.Event;
import com.github.caay2000.metropolis.event.EventType;
import com.github.caay2000.metropolis.reporter.Report;

public class EventOutputReport extends Event {

    private final Report report;

    public EventOutputReport(long time, Report report) {
        super(time);
        this.report = report;
    }

    @Override
    public EventType getType() {
        return EventType.OUTPUT_REPORT;
    }

    public Report getReport() {
        return this.report;
    }
}
