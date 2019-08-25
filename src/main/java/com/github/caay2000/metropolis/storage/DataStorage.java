package com.github.caay2000.metropolis.storage;

import com.github.caay2000.metropolis.collector.CollectedData;
import com.github.caay2000.metropolis.collector.PollutionLevel;
import com.github.caay2000.metropolis.engine.Position;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.EventHandler;
import com.github.caay2000.metropolis.reporter.DataReport;
import com.github.caay2000.metropolis.reporter.Reporter;

public class DataStorage {

    private final EventHandler eventHandler;

    private final Reporter reporter;
    private final Data data;

    public DataStorage(EventBus eventBus, Reporter reporter) {
        this.eventHandler = new DataStorageEventHandler(eventBus, this);
        this.reporter = reporter;
        this.data = new Data();
    }

    public void store(CollectedData collectedData) {
        this.data.addMeasurement(collectedData.getPollutionValue());
    }

    public void publishReport(long eventTime, Position position, String source) {

        this.reporter.publishReport(new DataReport(eventTime, position, this.data.getAverage(), source));
        this.data.resetMeasurements();
    }

    private class Data {
        private int measurements;
        private int totalPollution;

        public Data() {
            this.measurements = 0;
            this.totalPollution = 0;
        }

        public void addMeasurement(int pollutionValue) {
            this.measurements++;
            this.totalPollution += pollutionValue;
        }

        public String getAverage() {
            int average = this.totalPollution / this.measurements;
            return PollutionLevel.Factory.getLevel(average).name();
        }

        public void resetMeasurements() {
            this.measurements = 0;
            this.totalPollution = 0;
        }
    }
}
