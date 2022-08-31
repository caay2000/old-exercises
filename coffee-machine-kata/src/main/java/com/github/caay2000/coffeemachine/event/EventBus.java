package com.github.caay2000.coffeemachine.event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class EventBus {

    private static EventBus eventBus;

    private final Map<EventType, Set<EventListener>> eventListeners = new HashMap();

    private EventBus() {
        this.eventBus = this;
        this.setUp();
    }

    public static EventBus getInstance() {
        if (EventBus.eventBus == null) {
            EventBus.eventBus = new EventBus();
        }
        return EventBus.eventBus;
    }

    public void reset() {
        eventListeners.clear();
        this.setUp();
    }

    private void setUp() {
        Stream.of(EventType.values())
                .forEach(e -> eventListeners.put(e, new HashSet<>()));
    }

    public void send(Event event) {
        eventListeners.get(event.getType()).stream()
                .forEach(e -> e.onNotify(event));
    }

    public void subscribeTo(EventListener eventListener, EventType eventType) {
        eventListeners.get(eventType).add(eventListener);
    }
}
