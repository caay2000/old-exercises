package com.github.caay2000.metropolis.route;

import org.junit.Assert;
import org.junit.Test;


public class RouteTest {

    private final Position POINT_1 = new Position(41.37721d, 2.08569d);
    private final Position POINT_2 = new Position(41.38802d, 2.16921d);
    private final String POLYLINE_THREE_POINTS = "qnp{FqjvKqbA_iOdk@efB";

    @Test
    public void afterNewObjectCurrentPositionIsFirstPoint() {
        Route testee = new Route(POLYLINE_THREE_POINTS);

        Assert.assertEquals(POINT_1, testee.getCurrentStop());
    }

    @Test
    public void nextStopMovesToNextStop() {
        Route testee = new Route(POLYLINE_THREE_POINTS);

        Assert.assertEquals(POINT_2, testee.getNextStop());
    }

    @Test
    public void endOfRoute() {
        Route testee = new Route(POLYLINE_THREE_POINTS);
        testee.getNextStop();
        testee.getNextStop();

        Assert.assertTrue(testee.isEndOfRoute());
    }

    @Test
    public void nextStopSwapsDirectionAtEndOfRoute() {
        Route testee = new Route(POLYLINE_THREE_POINTS);
        testee.getNextStop();
        testee.getNextStop();

        Assert.assertEquals(POINT_2, testee.getNextStop());
    }


}