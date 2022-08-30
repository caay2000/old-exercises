package com.merkleinc.eventsystem.utlis;

import java.util.ArrayList;
import java.util.List;
import com.merkleinc.eventsystem.model.Event;
import com.merkleinc.eventsystem.processor.EventTypeProcessor;

public class EventTypeProcessorSpy implements EventTypeProcessor {

    private final List<Event> events;

    public EventTypeProcessorSpy() {
        events = new ArrayList<>();
    }

    @Override
    public void process(Event event) {
        events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }

    public boolean hasBeenCalled() {
        return events.size() > 0;
    }

    public boolean hasBeenCalled(int times) {
        return events.size() == times;
    }
}
