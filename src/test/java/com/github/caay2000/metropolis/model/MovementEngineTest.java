package com.github.caay2000.metropolis.model;

import org.junit.Assert;
import org.junit.Test;

public class MovementEngineTest {

    public static final double DELTA_ASSERTION = 0.00001d;
    private final double MAX_ROBOT_SPEED = 3d;
    private MovementEngine engine = new MovementEngine(MAX_ROBOT_SPEED);

    private final Position ORIGIN = new Position(0d, 0d);
    private final Position ORIGIN_PLUS_ONE = new Position(0d, -0.001d);
    private final Position ORIGIN_PLUS_TWO = new Position(0d, -0.002d);

    private final double DISTANCE_ONE = MovementEngine.DistanceCalculator.distanceBetween(ORIGIN, ORIGIN_PLUS_ONE);

    private final int TIME_DISTANCE_ONE = (int) Math.ceil(DISTANCE_ONE / MAX_ROBOT_SPEED);
    private final double SPEED_DISTANCE_ONE = DISTANCE_ONE / TIME_DISTANCE_ONE;


    @Test
    public void moveToSameLocationDoesNotMove() {

        Step step = engine.move(ORIGIN, ORIGIN, Double.MAX_VALUE);

        Assert.assertEquals(step.getOrigin(), step.getDestination());
        Assert.assertEquals(0d, step.getDistance(), DELTA_ASSERTION);
        Assert.assertEquals(0, step.getTime());
        Assert.assertEquals(0d, step.getSpeed(), DELTA_ASSERTION);
    }


    @Test
    public void moveLessThanMaxDistanceCreatesFullStep() {

        Step step = engine.move(ORIGIN, ORIGIN_PLUS_ONE, Double.MAX_VALUE);

        Assert.assertEquals(ORIGIN, step.getOrigin());
        Assert.assertEquals(ORIGIN_PLUS_ONE, step.getDestination());
        Assert.assertEquals(DISTANCE_ONE, step.getDistance(), DELTA_ASSERTION);
        Assert.assertEquals(TIME_DISTANCE_ONE, step.getTime());
        Assert.assertEquals(SPEED_DISTANCE_ONE, step.getSpeed(), DELTA_ASSERTION);
    }


    @Test
    public void moveMoreThanMaxDistanceCreatesMiddleStep() {

        Step step = engine.move(ORIGIN, ORIGIN_PLUS_TWO, DISTANCE_ONE);

        Assert.assertEquals(ORIGIN, step.getOrigin());
        Assert.assertNotEquals(ORIGIN_PLUS_TWO, step.getDestination());
        Assert.assertEquals(ORIGIN_PLUS_ONE, step.getDestination());
        Assert.assertEquals(DISTANCE_ONE, step.getDistance(), DELTA_ASSERTION);
        Assert.assertEquals(TIME_DISTANCE_ONE, step.getTime());
        Assert.assertEquals(SPEED_DISTANCE_ONE, step.getSpeed(), DELTA_ASSERTION);
    }


}