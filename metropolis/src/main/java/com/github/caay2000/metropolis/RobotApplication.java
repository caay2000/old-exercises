package com.github.caay2000.metropolis;

import com.github.caay2000.metropolis.console.Console;
import com.github.caay2000.metropolis.console.ConsoleOperation;
import com.github.caay2000.metropolis.event.EventBus;
import com.github.caay2000.metropolis.simulation.Simulation;

public class RobotApplication {

    private static final String EXAMPLE_POLYLINE = "mpjyHx`i@VjAVKnAh@BHHX@LZR@Bj@Ml@WWc@]w@bAyAfBmCb@o@pLeQfCsDVa@@ODQR}AJ{A?{BGuAD_@FKb@MTUX]Le@^kBVcAVo@Ta@|EaFh@m@FWaA{DCo@q@mCm@cC{A_GWeA}@sGSeAcA_EOSMa@}A_GsAwFkAiEoAaFaBoEGo@]_AIWW{AQyAUyBQqAI_BFkEd@aHZcDlAyJLaBPqDDeD?mBEiA}@F]yKWqGSkICmCIeZIuZi@_Sw@{WgAoXS{DOcAWq@KQGIFQDGn@Y`@MJEFIHyAVQVOJGHgFRJBBCCSKBcAKoACyA?m@^yVJmLJ{FGGWq@e@eBIe@Ei@?q@Bk@Hs@Le@Rk@gCuIkJcZsDwLd@g@Oe@o@mB{BgHQYq@qBQYOMSMGBUBGCYc@E_@H]DWJST?JFFHBDNBJ?LED?LBv@WfAc@@EDGNK|@e@hAa@`Bk@b@OEk@Go@IeACoA@a@PyB`@yDDc@e@K{Bi@oA_@w@]m@_@]QkBoAwC{BmAeAo@s@uAoB_AaBmAwCa@mAo@iCgAwFg@iDq@}G[uEU_GBuP@cICmA?eI?qCB{FBkCI}BOyCMiAGcAC{AN{YFqD^}FR}CNu@JcAHu@b@_E`@}DVsB^mBTsAQKkCmAg@[YQOIOvAi@[m@e@s@g@GKCKAEJIn@g@GYGIc@ScBoAf@{A`@uAlBfAG`@";

    private final Robot robot;
    private final Console console;
    private Thread thread;
    private boolean running = false;

    public RobotApplication(Console console, Simulation simulation, EventBus eventBus, RobotConfiguration robotConfiguration) {
        this.console = console;

        this.robot = new Robot(simulation, eventBus, robotConfiguration);
    }

    public void execute() {
        writeAbout();
        while (true) {
            console.write("$metropolis> ");
            ConsoleOperation operation = console.read();
            if (operation != null) {
                executeOperation(operation);
            }
        }
    }

    private void writeAbout() {
        console.writeln("Welcome to Metropolis Robot - Albert Casanovas 2019 (caay2000 at gmail.com)");
        console.writeln("This program will move a robot along a Google Maps polyline, reading periodic pollution values and printing reports");
        console.writeln("The outputs of this robot are stored in metropolis.out file. For more information about this project, read the README.md included");
        console.writeln("Useful commands:");
        console.writeln("$metropolis> start [polyline]   - start the robot routine along the provided polyline");
        console.writeln("$metropolis> stop               - stop the robot in it's current position");
        console.writeln("$metropolis> restart            - restart the robot after an stop");
        console.writeln("$metropolis> report             - make the robot print an instant report in it's current location");
        console.writeln("$metropolis> example            - starts the robot with the example polyline from London West to London City");
        console.writeln("$metropolis> exit               - stops the robot and exits the program");
    }

    private void executeOperation(ConsoleOperation operation) {
        if (ConsoleOperation.Operation.EXIT.equals(operation.getOperation())) {
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
        if (ConsoleOperation.Operation.EXAMPLE.equals(operation.getOperation())) {
            this.start(EXAMPLE_POLYLINE);
        }
        if (ConsoleOperation.Operation.REPORT.equals(operation.getOperation())) {
            this.publishInstantReport();
        }
    }

    public void start(String polyline) {
        this.robot.start(polyline);
        this.running = true;
        thread = new Thread(this.robot);
        thread.start();
    }

    public void stop() {
        this.running = false;
        this.robot.stop();
    }

    public void restart() {
        if (!this.running) {
            this.running = true;
            this.robot.restart();
            this.thread = new Thread(robot);
            this.thread.start();
        }
    }

    public void publishInstantReport() {
        this.robot.publishInstantReport();
    }

}
