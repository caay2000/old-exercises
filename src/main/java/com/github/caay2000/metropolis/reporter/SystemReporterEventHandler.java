package com.github.caay2000.metropolis.reporter;

import com.github.caay2000.metropolis.event.Event;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.EventHandler;
import com.github.caay2000.metropolis.event.EventType;
import com.github.caay2000.metropolis.event.type.EventOutputReport;

class SystemReporterEventHandler implements EventHandler {

    private final Reporter reporter;

    SystemReporterEventHandler(EventBus eventBus, Reporter reporter) {
        this.reporter = reporter;

        eventBus.subscribe(EventType.OUTPUT_REPORT, this::printReportHandler);
    }

    private void printReportHandler(Event<EventOutputReport> event) {

        EventOutputReport eventOutputReport = event.to(EventOutputReport.class);
        reporter.printReport(eventOutputReport.getReport());
    }
}
