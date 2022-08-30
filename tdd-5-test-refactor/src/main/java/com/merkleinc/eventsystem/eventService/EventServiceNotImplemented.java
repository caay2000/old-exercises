package com.merkleinc.eventsystem.eventService;

import java.util.Date;
import java.util.List;
import com.merkleinc.eventsystem.model.Event;
public class EventServiceNotImplemented implements EventService {

    @Override
    public List<Event> getEvents(Date date) {
        throw new RuntimeException("this shouldn't be called in test code");
    }
}
