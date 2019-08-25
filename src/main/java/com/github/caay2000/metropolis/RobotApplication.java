package com.github.caay2000.metropolis;

import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.simulation.Simulation;

public class RobotApplication {

    private final Robot robot;

    public RobotApplication(Simulation simulation, EventBus eventBus, RobotConfiguration robotConfiguration) {

        this.robot = new Robot(simulation, eventBus, robotConfiguration);
    }

    public void start(String polyline) {
        this.robot.start(polyline);
        Thread thread = new Thread(this.robot);
        thread.start();
    }

    public void stop() {
        this.robot.stop();
    }

    public void restart() {
        this.robot.restart();
    }

    public void publishInstantReport() {
        this.robot.publishInstantReport();
    }
}
