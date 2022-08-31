package com.github.caay2000.coffeemachine.event;

public class Event {

    private final EventType type;
    private final Object content;

    public Event(EventType type) {
        this.type = type;
        this.content = null;
    }

    public Event(EventType type, Object content) {
        this.type = type;
        this.content = content;
    }

    public EventType getType() {
        return type;
    }

    public boolean hasContent() {
        return content != null;
    }

    public String getContent() {
        return content.toString();
    }

    public <T> T getContent(Class<T> clazz) {
        return (T) content;
    }
}
