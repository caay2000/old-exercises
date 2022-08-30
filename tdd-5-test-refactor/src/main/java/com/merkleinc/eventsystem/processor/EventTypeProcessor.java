package com.merkleinc.eventsystem.processor;

import com.merkleinc.eventsystem.model.Event;

public interface EventTypeProcessor {

    void process(Event event);
}
