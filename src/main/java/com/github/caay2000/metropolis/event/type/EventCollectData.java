package com.github.caay2000.metropolis.event.type;

import com.github.caay2000.metropolis.route.Position;
import com.github.caay2000.metropolis.event.Event;
import com.github.caay2000.metropolis.event.EventType;

public class EventCollectData extends Event {

    private final Position position;

    public EventCollectData(long time, Position position) {
        super(time);
        this.position = position;
    }

    @Override
    public EventType getType() {
        return EventType.COLLECT_DATA;
    }

    public Position getPosition() {
        return position;
    }
}
