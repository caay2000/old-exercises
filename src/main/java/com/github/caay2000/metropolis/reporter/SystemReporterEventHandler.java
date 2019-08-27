package com.github.caay2000.metropolis.reporter;

import com.github.caay2000.metropolis.event.Event;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.EventHandler;
import com.github.caay2000.metropolis.event.EventType;
import com.github.caay2000.metropolis.event.type.EventOutputReport;
import com.github.caay2000.metropolis.event.type.EventRobotStatus;

class SystemReporterEventHandler implements EventHandler {

    private final Reporter reporter;

    SystemReporterEventHandler(EventBus eventBus, Reporter reporter) {
        this.reporter = reporter;

        eventBus.subscribe(EventType.OUTPUT_REPORT, this::printReportHandler);
        eventBus.subscribe(EventType.ROBOT_STATUS, this::printStatusHandler);
    }

    private void printReportHandler(Event<EventOutputReport> event) {

        EventOutputReport eventOutputReport = event.to(EventOutputReport.class);
        reporter.printReport(eventOutputReport.getReport());
    }

    private void printStatusHandler(Event<EventRobotStatus> event) {

        EventRobotStatus eventRobotStatus = event.to(EventRobotStatus.class);
        reporter.printReport(eventRobotStatus.getReport());
    }
}
