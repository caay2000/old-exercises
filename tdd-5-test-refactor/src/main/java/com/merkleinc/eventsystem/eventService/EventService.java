package com.merkleinc.eventsystem.eventService;

import java.util.Date;
import java.util.List;
import com.merkleinc.eventsystem.model.Event;

public interface EventService {

    List<Event> getEvents(Date date);
}
