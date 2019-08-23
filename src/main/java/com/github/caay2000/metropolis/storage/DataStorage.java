package com.github.caay2000.metropolis.storage;

import com.github.caay2000.metropolis.collector.PollutionLevel;
import com.github.caay2000.metropolis.event.Event;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.EventPublishReport;
import com.github.caay2000.metropolis.event.EventStoreCollectData;
import com.github.caay2000.metropolis.event.EventType;
import com.github.caay2000.metropolis.reporter.Report;
import com.github.caay2000.metropolis.reporter.Reporter;

public class DataStorage {

    private final EventBus systemEventBus;
    private final Reporter reporter;

    private int measurements;
    private int totalPollution;

    public DataStorage(EventBus systemEventBus, Reporter reporter) {
        this.systemEventBus = systemEventBus;
        this.reporter = reporter;

        this.systemEventBus.subscribe(EventType.STORE_COLLECT_DATA, this::store);
        this.systemEventBus.subscribe(EventType.PUBLISH_REPORT, this::publishReport);
        this.measurements = 0;
        this.totalPollution = 0;
    }

    public void store(Event<EventStoreCollectData> event) {

        EventStoreCollectData eventStoreCollectData = event.to(EventStoreCollectData.class);

        this.measurements++;
        this.totalPollution += eventStoreCollectData.getCollectedData().getPollutionValue();
    }

    public void publishReport(Event<EventPublishReport> event) {

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
