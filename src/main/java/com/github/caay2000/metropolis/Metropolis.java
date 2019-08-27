package com.github.caay2000.metropolis;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import com.github.caay2000.metropolis.collector.RandomDataMeter;
import com.github.caay2000.metropolis.console.SystemConsole;
import com.github.caay2000.metropolis.event.SystemEventBus;
import com.github.caay2000.metropolis.simulation.Simulation;

public class Metropolis {

    public static void main(String[] args) throws FileNotFoundException {

        PrintStream fileWriter = new PrintStream(
                new FileOutputStream("metropolis.out", true));

        RobotApplication robotApplication = new RobotApplication(new SystemConsole(System.out, System.in),
                new Simulation(6d),
                new SystemEventBus(),
                new RobotConfiguration(new RandomDataMeter(), fileWriter));

        robotApplication.execute();
    }
}
