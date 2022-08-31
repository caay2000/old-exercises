package com.github.caay2000.metropolis.storage;

import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.type.EventOutputReport;
import com.github.caay2000.metropolis.reporter.Source;
import com.github.caay2000.metropolis.reporter.type.RouteReport;
import com.github.caay2000.metropolis.route.Position;
import com.github.caay2000.metropolis.route.RouteData;
import com.github.caay2000.metropolis.simulation.Simulation;

public class RouteStorage {

    private final EventBus eventBus;
    private final Simulation simulation;

    private final Data data;

    public RouteStorage(Simulation simulation, EventBus eventBus) {
        this.eventBus = eventBus;
        this.simulation = simulation;

        new RouteStorageEventHandler(eventBus, this);

        this.data = new Data();
    }

    public void store(RouteData routeData) {
        this.data.addRouteData(routeData);
    }

    public void publishReport(long eventTime, Position position) {
        RouteReport routeReport = new RouteReport(eventTime, position, this.data.getDistanceTraveled(), this.data.getTimeElapsed(), this.data.getAverageSpeed(), Source.ROUTE.getValue());
        this.eventBus.publish(new EventOutputReport(simulation.getSimulationTime(), routeReport));
        this.data.resetMeasurements();
    }

    private static class Data {
        private double distanceTraveled;
        private int timeElapsed;
        private double averageSpeed;

        private void addRouteData(RouteData routeData) {

            this.distanceTraveled += routeData.getDistance();
            this.timeElapsed += routeData.getTime();
            this.averageSpeed = distanceTraveled / timeElapsed;
        }


        private double getAverageSpeed() {
            return averageSpeed;
        }

        private int getTimeElapsed() {
            return timeElapsed;
        }

        private double getDistanceTraveled() {
            return distanceTraveled;
        }

        private void resetMeasurements() {
            this.distanceTraveled = 0;
            this.timeElapsed = 0;
        }
    }


}
