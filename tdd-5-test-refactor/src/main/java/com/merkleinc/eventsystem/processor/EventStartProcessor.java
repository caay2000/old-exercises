package com.merkleinc.eventsystem.processor;

import com.merkleinc.eventsystem.SkyEventNotifier;
import com.merkleinc.eventsystem.model.Event;
import com.merkleinc.eventsystem.model.SkyEventNotification;
public class EventStartProcessor implements EventTypeProcessor {

    private SkyEventNotifier skyEventNotifier;

    public EventStartProcessor(SkyEventNotifier skyEventNotifier) {
        this.skyEventNotifier = skyEventNotifier;
    }

    @Override
    public void process(Event event) {
        if (event.isStart()) {

            SkyEventNotification notification = new SkyEventNotification();
            notification.setType("START");
            notification.setEventTime(event.getEventTime());
            notification.setEngineerId(event.getEngineerId());
            notification.setVisitNumber(event.getVisitNumber());
            notification.setEstimatedTime(event.getEstimatedTime());

            skyEventNotifier.sendNotification(notification);
        }
    }
}
