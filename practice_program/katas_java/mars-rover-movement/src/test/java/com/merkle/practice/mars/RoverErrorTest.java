package com.merkle.practice.mars;

import org.junit.Test;

import static com.merkle.practice.mars.Direction.NORTH;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RoverErrorTest {

    @Test
    public void new_rover_no_movements() {
        Rover rover = new Rover(0, 0, NORTH);
        rover.move("");
        assertThat(rover, is(new Rover(0, 0, NORTH)));
    }

    @Test
    public void new_rover_wrong_movements() {
        Rover rover = new Rover(0, 0, NORTH);
        rover.move("ui87");
        assertThat(rover, is(new Rover(0, 0, NORTH)));
    }

}
