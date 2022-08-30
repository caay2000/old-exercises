package com.merkleinc.eventsystem.utlis;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import com.merkleinc.eventsystem.eventService.EventService;
import com.merkleinc.eventsystem.model.Event;

public class EventServiceStub implements EventService {

    private final List<Event> events;

    public EventServiceStub(Event... events) {
        this.events = Arrays.asList(events);
    }

    @Override
    public List<Event> getEvents(Date date) {
        return this.events;
    }
}
