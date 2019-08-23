package com.github.caay2000.metropolis.event;

import com.github.caay2000.metropolis.engine.Position;

public class EventPublishReport extends Event {

    private final Position position;
    private final String source;

    public EventPublishReport(long time, Position position, String source) {
        super(time);
        this.position = position;
        this.source = source;
    }

    @Override
    public EventType getType() {
        return EventType.PUBLISH_REPORT;
    }

    public Position getPosition() {
        return position;
    }

    public String getSource() {
        return source;
    }
}
