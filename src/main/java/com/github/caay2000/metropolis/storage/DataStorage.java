package com.github.caay2000.metropolis.storage;

import com.github.caay2000.metropolis.collector.CollectedData;
import com.github.caay2000.metropolis.collector.PollutionLevel;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.type.EventOutputReport;
import com.github.caay2000.metropolis.reporter.type.DataReport;
import com.github.caay2000.metropolis.route.Position;
import com.github.caay2000.metropolis.simulation.Simulation;

public class DataStorage {

    private final Simulation simulation;
    private final EventBus eventBus;

    private final Data data;

    public DataStorage(Simulation simulation, EventBus eventBus) {
        this.simulation = simulation;
        this.eventBus = eventBus;

        new DataStorageEventHandler(eventBus, this);

        this.data = new Data();
    }

    public void store(CollectedData collectedData) {
        this.data.addMeasurement(collectedData.getPollutionValue());
    }

    public void publishReport(long eventTime, Position position, String source) {

        DataReport dataReport = new DataReport(eventTime, position, this.data.getAverage(), source);
        this.eventBus.publish(new EventOutputReport(simulation.getSimulationTime(), dataReport));
        this.data.resetMeasurements();
    }

    private static class Data {
        private int measurements;
        private int totalPollution;

        private Data() {
            this.measurements = 0;
            this.totalPollution = 0;
        }

        private void addMeasurement(int pollutionValue) {
            this.measurements++;
            this.totalPollution += pollutionValue;
        }

        private String getAverage() {
            int average = this.totalPollution / this.measurements;
            return PollutionLevel.Factory.getLevel(average).name();
        }

        private void resetMeasurements() {
            this.measurements = 0;
            this.totalPollution = 0;
        }
    }
}
