package com.github.caay2000.metropolis.event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

import static java.util.Objects.isNull;

public final class SystemEventBus implements EventBus {

    private final HashMap<EventType, Set<Consumer<? super Event>>> subscriptions;

    public SystemEventBus() {
        this.subscriptions = new HashMap<>();
    }

    public void publish(Event event) {
        subscriptions.get(event.getType()).stream()
                .forEach(e -> e.accept(event));
    }

    public void subscribe(EventType eventType, Consumer<? super Event> function) {
        if (isNull(this.subscriptions.get(eventType))) {
            this.subscriptions.put(eventType, new HashSet<>());
        }
        this.subscriptions.get(eventType).add(function);
    }
}
