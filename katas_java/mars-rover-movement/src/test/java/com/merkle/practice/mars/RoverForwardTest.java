package com.merkle.practice.mars;

import org.junit.Test;

import static com.merkle.practice.mars.Direction.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RoverForwardTest {


    @Test
    public void new_rover_facing_north_f_received_move_forward() {
        Rover rover = new Rover(0, 0, NORTH);
        rover.move("f");
        assertThat(rover, is(new Rover(0, 1, NORTH)));
    }

    @Test
    public void new_rover_facing_east_f_received_move_forward() {

        Rover rover = new Rover(0, 0, EAST);
        rover.move("f");
        assertThat(rover, is(new Rover(1, 0, EAST)));
    }

    @Test
    public void new_rover_facing_south_f_received_move_forward() {
        Rover rover = new Rover(0, 0, SOUTH);
        rover.move("f");
        assertThat(rover, is(new Rover(0, -1, SOUTH)));
    }

    @Test
    public void new_rover_facing_west_f_received_move_forward() {
        Rover rover = new Rover(0, 0, WEST);
        rover.move("f");
        assertThat(rover, is(new Rover(-1, 0, WEST)));
    }
}
