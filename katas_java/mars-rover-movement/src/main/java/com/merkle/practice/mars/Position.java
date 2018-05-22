package com.merkle.practice.mars;

public class Position {

    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveForward(Direction direction) {
        this.move(direction, 1);
    }

    public void moveBackward(Direction direction) {
        this.move(direction, -1);
    }

    private void move(Direction direction, int steps) {
        if (Direction.NORTH.equals(direction)) {
            this.y = y + steps;
        }
        if (Direction.EAST.equals(direction)) {
            this.x = x + steps;
        }
        if (Direction.SOUTH.equals(direction)) {
            this.y = y - steps;
        }
        if (Direction.WEST.equals(direction)) {
            this.x = x - steps;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;
    }

    @Override
    public String toString() {
        return "(x=" + x + ",y=" + y + ")";
    }
}
