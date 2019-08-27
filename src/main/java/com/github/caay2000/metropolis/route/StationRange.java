package com.github.caay2000.metropolis.route;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.type.EventCollectInstantData;
import com.github.caay2000.metropolis.simulation.Simulation;

public class StationRange {

    private final Simulation simulation;
    private final EventBus eventBus;

    private final Set<Station> stationsVisited;

    public StationRange(Simulation simulation, EventBus eventBus) {
        this.simulation = simulation;
        this.eventBus = eventBus;

        new StationRangeEventHandler(eventBus, this);

        this.stationsVisited = new HashSet<>();
    }

    public void check(Position position, Route route, int distance) {
        List<Station> stationsInRange = Arrays.stream(Station.values())
                .filter(this::notAlreadyVisited)
                .filter(Station::isInstantReport)
                .filter(isInRange(position, distance))
                .collect(Collectors.toList());

        for (Station station : stationsInRange) {
            this.eventBus.publish(new EventCollectInstantData(simulation.getSimulationTime(), position, station.getName()));
            this.stationsVisited.add(station);
        }

        if (route.isEndOfRoute()) {
            this.stationsVisited.clear();
        }
    }

    private Predicate<Station> isInRange(Position position, int distance) {
        return station -> station.getPosition().isInRange(position, distance);
    }

    private boolean notAlreadyVisited(Station e) {
        return !this.stationsVisited.contains(e);
    }
}
