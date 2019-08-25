package com.github.caay2000.metropolis.simulation;

import org.junit.Assert;
import org.junit.Test;
public class SimulationTest {

    public static final double TEST_SIMULATION_FACTOR = 1000d / (60d * 10d);

    @Test
    public void simulationTimeFactor() {

        Simulation testee = new Simulation(TEST_SIMULATION_FACTOR);
        long startTime = testee.getSimulationTime();

        testee.updateSimulation(60);
        long endTime = testee.getSimulationTime();

        long elpasedTime = endTime - startTime;
        Assert.assertEquals(60 * 1000, elpasedTime);
    }

    @Test
    public void realTimeSimulationFactor() {
        Simulation testee = new Simulation(TEST_SIMULATION_FACTOR);
        long startTime = System.currentTimeMillis();

        testee.updateSimulation(60);
        long endTime = System.currentTimeMillis();

        long elpasedTime = endTime - startTime;
        Assert.assertTrue(elpasedTime >= (long) Math.floor(60 * TEST_SIMULATION_FACTOR));
    }
}