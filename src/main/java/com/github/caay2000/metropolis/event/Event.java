package com.github.caay2000.metropolis.event;

import com.github.caay2000.metropolis.exception.MetropolisException;

public abstract class Event<T> {

    private final long eventTime;

    protected Event(long time) {

        this.eventTime = time;
    }

    public abstract EventType getType();

    public T to(Class<T> clazz) {
        try {
            return (T) this;
        } catch (ClassCastException e) {
            throw new MetropolisException("error", e);
        }
    }

    public long getEventTime() {
        return eventTime;
    }
}
