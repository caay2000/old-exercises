package com.github.caay2000.metropolis.simulation;

import org.junit.Assert;
import org.junit.Test;
public class SimulationTest {

    @Test
    public void simulationTimeFactor() {

        Simulation testee = new Simulation();
        long startTime = testee.getSimulationTime();

        testee.updateSimulation(60);
        long endTime = testee.getSimulationTime();

        long elpasedTime = endTime - startTime;
        Assert.assertEquals(60 * 1000, elpasedTime);
    }

    @Test
    public void realTimeSimulationFactor() {
        Simulation testee = new Simulation();
        long startTime = System.currentTimeMillis();

        testee.updateSimulation(60);
        long endTime = System.currentTimeMillis();

        long elpasedTime = endTime - startTime;
        Assert.assertTrue(elpasedTime >= (long) Math.floor(60 * Simulation.SIMULATION_TIME_FACTOR));
    }
}