package com.github.caay2000.metropolis;

import com.github.caay2000.metropolis.collector.DataCollector;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.event.type.*;
import com.github.caay2000.metropolis.reporter.Source;
import com.github.caay2000.metropolis.reporter.SystemReporter;
import com.github.caay2000.metropolis.route.*;
import com.github.caay2000.metropolis.simulation.Simulation;
import com.github.caay2000.metropolis.storage.DataStorage;
import com.github.caay2000.metropolis.storage.RouteStorage;
import com.google.common.math.DoubleMath;

public class Robot implements Runnable {

    private final RobotConfiguration robotConfiguration;

    private final Simulation simulation;
    private final EventBus eventBus;
    private final MovementEngine engine;

    private Route route;
    private Position currentPosition;

    private int nextCollectDataDistance;
    private int nextPublishReportTime;

    private volatile boolean running = true;

    public Robot(Simulation simulation, EventBus eventBus, RobotConfiguration robotConfiguration) {

        this.simulation = simulation;
        this.eventBus = eventBus;
        this.robotConfiguration = robotConfiguration;

        this.engine = new MovementEngine(robotConfiguration.getMaxRobotSpeed());

        new DataCollector(simulation, eventBus, robotConfiguration.getDataMeter());
        new SystemReporter(eventBus, robotConfiguration.getOutput());
        new StationRange(simulation, eventBus);
        new RouteStorage(simulation, eventBus);
        new DataStorage(simulation, eventBus);

        this.nextCollectDataDistance = robotConfiguration.getCollectDataDistance();
        this.nextPublishReportTime = robotConfiguration.getPublishReportTime();
    }

    @Override
    public void run() {
        while (running) {
            moveTo(route.getNextStop());
        }
    }

    public void stop() {
        this.running = false;
    }

    public void restart() {
        this.running = true;
    }

    public void start(String polyline) {
        this.route = new Route(polyline);
        this.currentPosition = route.getCurrentStop();
    }

    public void publishInstantReport() {
        this.eventBus.publish(new EventCollectInstantData(simulation.getSimulationTime(), this.currentPosition, Source.ON_DEMAND.getValue()));
    }

    private void moveTo(Position nextStop) {

        RouteData routeData = this.engine.move(this.currentPosition, nextStop, this.nextCollectDataDistance);

        updateSimulation(routeData.getTime());
        updateRobot(routeData);
        checkCollectDataEvent();
        checkPublishDataReportEvent();
        checkPublishStationDataReportEvent();

        if (notOnNextStop(nextStop)) {
            this.moveTo(nextStop);
        } else {
            checkPublishRouteReportEvent();
        }
    }

    private void checkPublishStationDataReportEvent() {
        this.eventBus.publish(new EventStationInRange(simulation.getSimulationTime(), this.currentPosition, this.route, this.robotConfiguration.getDistanceRangeStation()));
    }

    private void checkPublishRouteReportEvent() {
        if (this.route.isEndOfRoute()) {
            this.eventBus.publish(new EventPublishRouteReport(simulation.getSimulationTime(), this.currentPosition));
        }
    }

    private boolean notOnNextStop(Position nextStop) {
        return !this.currentPosition.equals(nextStop);
    }

    private void checkPublishDataReportEvent() {
        if (this.nextPublishReportTime <= 0) {
            this.eventBus.publish(new EventPublishDataReport(simulation.getSimulationTime(), this.currentPosition, Source.ROBOT.getValue()));
            this.nextPublishReportTime = robotConfiguration.getPublishReportTime();
        }
    }

    private void updateSimulation(int secondsElapsed) {
        this.simulation.updateSimulation(secondsElapsed);
    }

    private void updateRobot(RouteData routeData) {
        this.currentPosition = routeData.getDestination();
        this.nextCollectDataDistance -= routeData.getDistance();
        this.nextPublishReportTime -= routeData.getTime();
        this.eventBus.publish(new EventStoreRouteData(simulation.getSimulationTime(), routeData));
    }

    private void checkCollectDataEvent() {
        if (DoubleMath.fuzzyCompare(nextCollectDataDistance, 0d, robotConfiguration.getDistanceDelta()) == 0) {
            this.eventBus.publish(new EventCollectData(simulation.getSimulationTime(), this.currentPosition));
            this.nextCollectDataDistance = this.robotConfiguration.getCollectDataDistance();
        }
    }

}
