package com.merkle.practice.mars;

import org.junit.Test;

import static com.merkle.practice.mars.Direction.EAST;
import static com.merkle.practice.mars.Direction.NORTH;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RoverMultipleMovements {

    @Test
    public void new_rover_multiple_movements() {
        Rover rover = new Rover(0, 0, NORTH);
        rover.move("frf");
        assertThat(rover, is(new Rover(1, 1, EAST)));
    }

}
