package com.github.caay2000.metropolis.simulation;

import java.util.Date;
import com.github.caay2000.metropolis.exception.MetropolisException;

public class Simulation {

    public static final double SIMULATION_TIME_FACTOR = 1000d / (60d * 10d);

    private long realEpoch;
    private long simulationEpoch;

    public Simulation() {
        this.simulationEpoch = new Date().getTime();
        this.realEpoch = this.simulationEpoch;
    }

    public void updateSimulation(int secondsElapsed) {

        long actualEpoch = new Date().getTime();
        long realTimeElapsed = actualEpoch - realEpoch;

        this.simulationEpoch += secondsElapsed * 1000;
        try {
            long sleepMillis = (int) Math.floor(secondsElapsed * SIMULATION_TIME_FACTOR);
            Thread.sleep(Math.max(0l, sleepMillis - realTimeElapsed));
        } catch (InterruptedException e) {
            throw new MetropolisException("error simulating time");
        }
        this.realEpoch = new Date().getTime();
    }

    public long getSimulationTime() {
        return this.simulationEpoch;
    }
}
