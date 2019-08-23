package com.github.caay2000.metropolis.model.storage;

import com.github.caay2000.metropolis.model.collector.PollutionLevel;
import com.github.caay2000.metropolis.model.event.*;
import com.github.caay2000.metropolis.model.reporter.Report;
import com.github.caay2000.metropolis.model.reporter.Reporter;

public class DataStorage {

    private final EventBus eventBus;
    private final Reporter reporter;

    private int measurements;
    private int totalPollution;

    public DataStorage(Reporter reporter) {
        this.eventBus = EventBus.getInstance();
        this.reporter = reporter;

        this.eventBus.subscribe(EventType.STORE_COLLECT_DATA, this::store);
        this.eventBus.subscribe(EventType.PUBLISH_REPORT, this::report);
        this.measurements = 0;
        this.totalPollution = 0;
    }

    private void store(Event<EventStoreCollectData> event) {

        EventStoreCollectData eventStoreCollectData = event.to(EventStoreCollectData.class);

        this.measurements++;
        this.totalPollution += eventStoreCollectData.getCollectedData().getPollutionValue();
    }

    private void report(Event<EventPublishReport> event) {

        EventPublishReport eventPublishReport = event.to(EventPublishReport.class);

        String level = calculateLevel();
        resetMeasurements();

        Report report = new Report(event.getEventTime(), eventPublishReport.getPosition(), level, eventPublishReport.getSource());
        this.reporter.report(report);
    }

    private String calculateLevel() {
        int average = this.totalPollution / this.measurements;
        return PollutionLevel.Factory.getLevel(average).name();
    }

    private void resetMeasurements() {
        this.measurements = 0;
        this.totalPollution = 0;
    }
}
