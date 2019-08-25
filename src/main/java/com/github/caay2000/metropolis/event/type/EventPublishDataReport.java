package com.github.caay2000.metropolis.event.type;

import com.github.caay2000.metropolis.route.Position;
import com.github.caay2000.metropolis.event.Event;
import com.github.caay2000.metropolis.event.EventType;
import com.github.caay2000.metropolis.reporter.Source;

public class EventPublishDataReport extends Event {

    private final Position position;
    private final String source;

    public EventPublishDataReport(long time, Position position, String source) {
        super(time);
        this.position = position;
        this.source = source;
    }

    @Override
    public EventType getType() {
        return EventType.PUBLISH_DATA_REPORT;
    }

    public Position getPosition() {
        return position;
    }

    public String getSource() {
        return source;
    }
}
