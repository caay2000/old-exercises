package com.github.caay2000.metropolis.route;

import org.junit.Assert;
import org.junit.Test;

public class MovementEngineTest {

    private static final double DELTA_ASSERTION = 0.00001d;
    private final double MAX_ROBOT_SPEED = 3d;
    private final MovementEngine engine = new MovementEngine(MAX_ROBOT_SPEED);

    private final Position ORIGIN = new Position(0d, 0d);
    private final Position ORIGIN_PLUS_ONE = new Position(0d, -0.001d);
    private final Position ORIGIN_PLUS_TWO = new Position(0d, -0.002d);

    private final double DISTANCE_ONE = HaversineDistance.distanceBetween(ORIGIN, ORIGIN_PLUS_ONE);

    private final int TIME_DISTANCE_ONE = (int) Math.ceil(DISTANCE_ONE / MAX_ROBOT_SPEED);
    private final double SPEED_DISTANCE_ONE = DISTANCE_ONE / TIME_DISTANCE_ONE;


    @Test
    public void moveToSameLocationDoesNotMove() {

        RouteData routeData = engine.move(ORIGIN, ORIGIN, Double.MAX_VALUE);

        Assert.assertEquals(routeData.getOrigin(), routeData.getDestination());
        Assert.assertEquals(0d, routeData.getDistance(), DELTA_ASSERTION);
        Assert.assertEquals(0, routeData.getTime());
        Assert.assertEquals(0d, routeData.getSpeed(), DELTA_ASSERTION);
    }

    @Test
    public void moveLessThanMaxDistanceCreatesFullStep() {

        RouteData routeData = engine.move(ORIGIN, ORIGIN_PLUS_ONE, Double.MAX_VALUE);

        Assert.assertEquals(ORIGIN, routeData.getOrigin());
        Assert.assertEquals(ORIGIN_PLUS_ONE, routeData.getDestination());
        Assert.assertEquals(DISTANCE_ONE, routeData.getDistance(), DELTA_ASSERTION);
        Assert.assertEquals(TIME_DISTANCE_ONE, routeData.getTime());
        Assert.assertEquals(SPEED_DISTANCE_ONE, routeData.getSpeed(), DELTA_ASSERTION);
    }


    @Test
    public void moveMoreThanMaxDistanceCreatesMiddleStep() {

        RouteData routeData = engine.move(ORIGIN, ORIGIN_PLUS_TWO, DISTANCE_ONE);

        Assert.assertEquals(ORIGIN, routeData.getOrigin());
        Assert.assertNotEquals(ORIGIN_PLUS_TWO, routeData.getDestination());
        Assert.assertEquals(ORIGIN_PLUS_ONE, routeData.getDestination());
        Assert.assertEquals(DISTANCE_ONE, routeData.getDistance(), DELTA_ASSERTION);
        Assert.assertEquals(TIME_DISTANCE_ONE, routeData.getTime());
        Assert.assertEquals(SPEED_DISTANCE_ONE, routeData.getSpeed(), DELTA_ASSERTION);
    }


}