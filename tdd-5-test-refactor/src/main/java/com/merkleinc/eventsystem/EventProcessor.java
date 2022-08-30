package com.merkleinc.eventsystem;

import java.util.Date;
import java.util.List;
import com.merkleinc.eventsystem.eventService.EventService;
import com.merkleinc.eventsystem.model.Event;
import com.merkleinc.eventsystem.processor.EventTypeProcessor;

public class EventProcessor {

    private final EventService eventService;
    private EventTypeProcessor acceptProcessor;
    private EventTypeProcessor routeProcessor;
    private EventTypeProcessor startProcessor;
    private EventTypeProcessor completeProcessor;
    private EventTypeProcessor notDoneProcessor;

    public EventProcessor(EventService eventService) {
        this.eventService = eventService;
    }

    public void setAcceptProcessor(EventTypeProcessor acceptProcessor) {
        this.acceptProcessor = acceptProcessor;
    }

    public void setRouteProcessor(EventTypeProcessor routeProcessor) {
        this.routeProcessor = routeProcessor;
    }

    public void setStartProcessor(EventTypeProcessor startProcessor) {
        this.startProcessor = startProcessor;
    }

    public void setCompleteProcessor(EventTypeProcessor completeProcessor) {
        this.completeProcessor = completeProcessor;
    }

    public void setNotDoneProcessor(EventTypeProcessor notDoneProcessor) {
        this.notDoneProcessor = notDoneProcessor;
    }

    public void process() {

        List<Event> events = eventService.getEvents(new Date());

        for (Event event : events) {
            acceptProcessor.process(event);
            routeProcessor.process(event);
            startProcessor.process(event);
            completeProcessor.process(event);
            notDoneProcessor.process(event);
        }
    }
}
