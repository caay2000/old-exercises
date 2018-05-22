package com.merkle.practice.mars;

public class Rover {


    private final Position position;
    private Direction direction;

    public Rover(int x, int y, Direction direction) {
        this.position = new Position(x, y);
        this.direction = direction;
    }

    public void move(String input) {
        for (char charInput : input.toCharArray()) {
            singleMove(charInput);
        }
    }

    private void singleMove(char input) {
        if ('l' == input) {
            this.direction = this.direction.turnLeft();
        }
        if ('r' == input) {
            this.direction = this.direction.turnRight();
        }
        if ('f' == input) {
            this.position.moveForward(this.direction);
        }
        if ('b' == input) {
            this.position.moveBackward(this.direction);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rover rover = (Rover) o;

        if (position != null ? !position.equals(rover.position) : rover.position != null) return false;
        return direction == rover.direction;
    }

    @Override
    public String toString() {
        return "Rover{" + position + "," + direction + '}';
    }
}
