package com.github.caay2000.metropolis.model.event;

import com.github.caay2000.metropolis.model.exception.MetropolisException;

public abstract class Event<T> {

    protected final long eventTime;

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
