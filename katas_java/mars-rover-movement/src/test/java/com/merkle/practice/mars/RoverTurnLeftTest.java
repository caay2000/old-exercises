package com.merkle.practice.mars;

import org.junit.Test;

import static com.merkle.practice.mars.Direction.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RoverTurnLeftTest {

    @Test
    public void new_rover_facing_norht_l_received_turn_left() {
        Rover rover = new Rover(0, 0, NORTH);
        rover.move("l");
        assertThat(rover, is(new Rover(0, 0, WEST)));

    }

    @Test
    public void new_rover_facing_west_l_received_turn_left() {
        Rover rover = new Rover(0, 0, WEST);
        rover.move("l");
        assertThat(rover, is(new Rover(0, 0, SOUTH)));

    }

    @Test
    public void new_rover_facing_south_l_received_turn_left() {
        Rover rover = new Rover(0, 0, SOUTH);
        rover.move("l");
        assertThat(rover, is(new Rover(0, 0, EAST)));

    }

    @Test
    public void new_rover_facing_east_l_received_turn_left() {
        Rover rover = new Rover(0, 0, EAST);
        rover.move("l");
        assertThat(rover, is(new Rover(0, 0, NORTH)));
    }

}
