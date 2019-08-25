package com.github.caay2000.metropolis.reporter;

public enum Source {

    ROBOT,
    ROUTE,
    ON_DEMAND;

    public String getValue() {
        return this.name().toLowerCase();
    }
}
