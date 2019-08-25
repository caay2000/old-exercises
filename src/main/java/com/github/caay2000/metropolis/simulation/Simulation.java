package com.github.caay2000.metropolis.simulation;

import java.util.Date;
import com.github.caay2000.metropolis.exception.MetropolisException;

public class Simulation {

    private static final double SIMULATION_TIME_FACTOR = 1000d / 60d;
    private static final long ZERO_MILLI_SECONDS = 0l;

    private long realEpoch;
    private long simulationEpoch;
    private double simulationFactor;

    public Simulation() {
        new Simulation(SIMULATION_TIME_FACTOR);
    }

    public Simulation(double simulationFactor) {
        this.simulationEpoch = new Date().getTime();
        this.realEpoch = this.simulationEpoch;
        this.simulationFactor = simulationFactor;
    }

    public void updateSimulation(int secondsElapsed) {

        long actualEpoch = new Date().getTime();
        long realTimeElapsed = actualEpoch - realEpoch;

        this.simulationEpoch += secondsElapsed * 1000;
        try {
            long sleepMillis = (int) Math.floor(secondsElapsed * this.simulationFactor);
            Thread.sleep(Math.max(ZERO_MILLI_SECONDS, sleepMillis - realTimeElapsed));
        } catch (InterruptedException e) {
            throw new MetropolisException("error simulating time");
        }
        this.realEpoch = new Date().getTime();
    }

    public long getSimulationTime() {
        return this.simulationEpoch;
    }
}
