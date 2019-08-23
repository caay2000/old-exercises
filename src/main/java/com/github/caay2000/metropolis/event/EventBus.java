package com.github.caay2000.metropolis.event;

import java.util.function.Consumer;

public interface EventBus {

    void publish(Event event);

    void subscribe(EventType eventType, Consumer<? super Event> function);
}
