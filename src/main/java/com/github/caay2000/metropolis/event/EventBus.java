package com.github.caay2000.metropolis.event;

import java.util.HashMap;
import java.util.function.Consumer;

public final class EventBus {

    private static EventBus instance;

    private final HashMap<EventType, Consumer<? super Event>> subscriptions;

    private EventBus() {
        this.subscriptions = new HashMap<>();
    }

    public static EventBus getInstance() {
        if (instance == null) {
            instance = new EventBus();
        }
        return instance;
    }

    public void publish(Event event) {
        subscriptions.entrySet().stream()
                .filter(entry -> event.getType().equals(entry.getKey()))
                .forEach(entry -> entry.getValue().accept(event));
    }

    public void subscribe(EventType eventType, Consumer<? super Event> function) {
        this.subscriptions.put(eventType, function);
    }
}
