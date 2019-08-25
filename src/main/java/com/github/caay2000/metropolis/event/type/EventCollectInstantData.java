package com.github.caay2000.metropolis.event.type;

import com.github.caay2000.metropolis.route.Position;
import com.github.caay2000.metropolis.event.Event;
import com.github.caay2000.metropolis.event.EventType;
import com.github.caay2000.metropolis.reporter.Source;

public class EventCollectInstantData extends Event {

    private final Position position;
    private final String source;

    public EventCollectInstantData(long eventTime, Position position, String source) {
        super(eventTime);
        this.position = position;
        this.source = source;
    }

    @Override
    public EventType getType() {
        return EventType.COLLECT_INSTANT_DATA;
    }

    public Position getPosition() {
        return position;
    }

    public String getSource() {
        return source;
    }
}
