package com.github.caay2000.metropolis.reporter;

public abstract class Report {

    private final long timestamp;

    protected Report(long timestamp) {
        this.timestamp = timestamp / 1000;
    }

    public long getTimestamp() {
        return timestamp;
    }
}

