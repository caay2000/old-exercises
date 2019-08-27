package com.github.caay2000.metropolis;

import com.github.caay2000.metropolis.console.Console;
import com.github.caay2000.metropolis.console.ConsoleOperation;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.simulation.Simulation;

public class RobotApplication {

    private final Robot robot;
    private final Console console;
    private Thread thread;

    public RobotApplication(Console console, Simulation simulation, EventBus eventBus, RobotConfiguration robotConfiguration) {
        this.console = console;

        this.robot = new Robot(simulation, eventBus, robotConfiguration);
    }

    public void execute() {
        while (true) {
            console.write("metropolis> ");
            ConsoleOperation operation = console.read();
            if (operation != null) {
                executeOperation(operation);
            }
        }
    }

    private void executeOperation(ConsoleOperation operation) {
        if (ConsoleOperation.Operation.QUIT.equals(operation.getOperation())) {
            System.exit(0);
        }
        if (ConsoleOperation.Operation.START.equals(operation.getOperation())) {
            this.start(operation.getValue());
        }
        if (ConsoleOperation.Operation.STOP.equals(operation.getOperation())) {
            this.stop();
        }
        if (ConsoleOperation.Operation.RESTART.equals(operation.getOperation())) {
            this.restart();
        }
        if (ConsoleOperation.Operation.REPORT.equals(operation.getOperation())) {
            this.publishInstantReport();
        }
    }

    public void start(String polyline) {
        this.robot.start(polyline);
        thread = new Thread(this.robot);
        thread.start();
    }

    public void stop() {
        this.robot.stop();
    }

    public void restart() {
        this.robot.restart();
        this.thread = new Thread(robot);
        this.thread.start();
    }

    public void publishInstantReport() {
        this.robot.publishInstantReport();
    }


}
