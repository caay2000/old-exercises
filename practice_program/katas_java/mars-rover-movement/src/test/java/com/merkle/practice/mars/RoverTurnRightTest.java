package com.merkle.practice.mars;

import org.junit.Test;

import static com.merkle.practice.mars.Direction.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RoverTurnRightTest {

    @Test
    public void new_rover_facing_north_r_received_turn_right() {
        Rover rover = new Rover(0, 0, NORTH);
        rover.move("r");
        assertThat(rover, is(new Rover(0, 0, EAST)));
    }

    @Test
    public void new_rover_facing_east_r_received_turn_right() {
        Rover rover = new Rover(0, 0, EAST);
        rover.move("r");
        assertThat(rover, is(new Rover(0, 0, SOUTH)));
    }

    @Test
    public void new_rover_facing_south_r_received_turn_right() {
        Rover rover = new Rover(0, 0, SOUTH);
        rover.move("r");
        assertThat(rover, is(new Rover(0, 0, WEST)));
    }

    @Test
    public void new_rover_facing_west_r_received_turn_right() {
        Rover rover = new Rover(0, 0, WEST);
        rover.move("r");
        assertThat(rover, is(new Rover(0, 0, NORTH)));
    }

}
