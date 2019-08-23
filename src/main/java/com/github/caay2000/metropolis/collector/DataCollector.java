package com.github.caay2000.metropolis.collector;

import com.github.caay2000.metropolis.event.Event;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.EventCollectData;
import com.github.caay2000.metropolis.event.EventStoreCollectData;
import com.github.caay2000.metropolis.event.EventType;
import com.github.caay2000.metropolis.simulation.Simulation;

public class DataCollector {

    private final EventBus eventBus;
    private final DataMeter meter;
    private final Simulation simulation;

    public DataCollector(Simulation simulation, EventBus eventBus, DataMeter meter) {
        this.simulation = simulation;
        this.eventBus = eventBus;
        this.meter = meter;

        this.eventBus.subscribe(EventType.COLLECT_DATA, this::collectHandler);
    }

    public void collectHandler(Event<EventCollectData> event) {

        CollectedData collectedData = collect(event.to(EventCollectData.class));
        this.eventBus.publish(new EventStoreCollectData(simulation.getSimulationTime(), collectedData));
    }

    public CollectedData collect(EventCollectData eventCollectData) {
        return new CollectedData(eventCollectData.getPosition(), this.meter.getValue());
    }
}
