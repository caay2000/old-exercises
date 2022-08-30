package com.merkle.practice.mars;

import org.junit.Test;

import static com.merkle.practice.mars.Direction.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RoverBackwardTest {

    @Test
    public void new_rover_facing_north_b_received_move_backward() {
        Rover rover = new Rover(0, 0, NORTH);
        rover.move("b");
        assertThat(rover, is(new Rover(0, -1, NORTH)));
    }

    @Test
    public void new_rover_facing_east_b_received_move_backward() {
        Rover rover = new Rover(0, 0, EAST);
        rover.move("b");
        assertThat(rover, is(new Rover(-1, 0, EAST)));
    }

    @Test
    public void new_rover_facing_south_b_received_move_backward() {
        Rover rover = new Rover(0, 0, SOUTH);
        rover.move("b");
        assertThat(rover, is(new Rover(0, 1, SOUTH)));
    }

    @Test
    public void new_rover_facing_west_b_received_move_backward() {
        Rover rover = new Rover(0, 0, WEST);
        rover.move("b");
        assertThat(rover, is(new Rover(1, 0, WEST)));
    }
}
