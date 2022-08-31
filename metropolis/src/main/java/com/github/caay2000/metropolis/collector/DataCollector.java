package com.github.caay2000.metropolis.collector;

import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.type.EventOutputReport;
import com.github.caay2000.metropolis.event.type.EventStoreCollectData;
import com.github.caay2000.metropolis.reporter.type.DataReport;
import com.github.caay2000.metropolis.route.Position;
import com.github.caay2000.metropolis.simulation.Simulation;

public class DataCollector {

    private final EventBus eventBus;
    private final DataMeter meter;
    private final Simulation simulation;

    public DataCollector(Simulation simulation, EventBus eventBus, DataMeter meter) {

        new DataCollectorEventHandler(eventBus, this);

        this.simulation = simulation;
        this.eventBus = eventBus;
        this.meter = meter;
    }

    public void collect(Position position) {
        CollectedData collectedData = new CollectedData(position, this.meter.getValue());
        this.eventBus.publish(new EventStoreCollectData(simulation.getSimulationTime(), collectedData));
    }

    public void collectInstant(Position position, String source) {
        CollectedData collectedData = new CollectedData(position, this.meter.getValue());
        DataReport report = new DataReport(simulation.getSimulationTime(), position, collectedData.getPollutionLevel().name(), source);
        this.eventBus.publish(new EventOutputReport(simulation.getSimulationTime(), report));
    }
}
