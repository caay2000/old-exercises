package com.github.caay2000.metropolis.route;

import com.github.caay2000.metropolis.util.PolylineEncoding;

import java.util.List;
import java.util.stream.Collectors;

public class Route {

    private final List<Position> routeStops;

    private int stopIndex;
    private Direction direction;

    public Route(String polyline) {
        this.routeStops = PolylineEncoding.decode(polyline).stream()
                .map(e -> new Position(e.lat, e.lng))
                .collect(Collectors.toList());
        this.stopIndex = 0;
        this.direction = Direction.BACKWARD;
    }

    public Position getCurrentStop() {
        return this.routeStops.get(stopIndex);
    }

    public Position getNextStop() {
        if (isEndOfRoute()) {
            this.swapDirection();
        }
        return nextStop();
    }

    public boolean isEndOfRoute() {
        return stopIndex == routeStops.size() - 1 ||
                stopIndex == 0;
    }

    private void swapDirection() {
        if (this.direction == Direction.FORWARD) {
            this.direction = Direction.BACKWARD;
        } else {
            this.direction = Direction.FORWARD;
        }
    }

    private Position nextStop() {
        if (this.direction == Direction.FORWARD) {
            return this.routeStops.get(++stopIndex);
        }
        return this.routeStops.get(--stopIndex);
    }

    private enum Direction {
        FORWARD,
        BACKWARD
    }
}
