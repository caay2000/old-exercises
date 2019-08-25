package com.github.caay2000.metropolis.reporter;

import com.github.caay2000.metropolis.event.Event;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.EventHandler;
import com.github.caay2000.metropolis.event.EventType;
import com.github.caay2000.metropolis.event.type.EventCollectData;
import com.github.caay2000.metropolis.event.type.EventOutputReport;

public class ReporterEventHandler implements EventHandler {

    private final EventBus eventBus;
    private final Reporter reporter;

    public ReporterEventHandler(EventBus eventBus, Reporter reporter) {
        this.eventBus = eventBus;
        this.reporter = reporter;

        this.eventBus.subscribe(EventType.OUTPUT_REPORT, this::printReportHandler);
    }

    public void printReportHandler(Event<EventOutputReport> event) {

        EventOutputReport eventOutputReport = event.to(EventOutputReport.class);
        reporter.printReport(eventOutputReport.getReport());
    }
}
