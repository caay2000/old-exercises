package com.github.caay2000.metropolis.model;

import com.google.common.math.DoubleMath;

import java.util.Objects;
import java.util.StringJoiner;

public class Position {

    private final double DELTA = 0.000001d;

    private final double lat;
    private final double lng;

    public Position(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return DoubleMath.fuzzyCompare(position.lat, lat, DELTA) == 0 &&
                DoubleMath.fuzzyCompare(position.lng, lng, DELTA) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lng);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Position.class.getSimpleName() + "[", "]")
                .add("lat=" + lat)
                .add("lng=" + lng)
                .toString();
    }
}
