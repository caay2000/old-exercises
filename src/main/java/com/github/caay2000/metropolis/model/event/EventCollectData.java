package com.github.caay2000.metropolis.model.event;

import com.github.caay2000.metropolis.model.engine.Position;

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
