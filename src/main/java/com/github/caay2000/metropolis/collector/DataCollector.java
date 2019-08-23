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

    public DataCollector(DataMeter meter, Simulation simulation) {
        this.simulation = simulation;
        this.eventBus = EventBus.getInstance();
        this.eventBus.subscribe(EventType.COLLECT_DATA, this::collect);
        this.meter = meter;
    }

    public void collect(Event<EventCollectData> event) {

        EventCollectData eventCollectData = event.to(EventCollectData.class);
        CollectedData collectedData = new CollectedData(eventCollectData.getPosition(), this.meter.getValue());
        this.eventBus.publish(new EventStoreCollectData(simulation.getSimulationTime(), collectedData));
    }
}
