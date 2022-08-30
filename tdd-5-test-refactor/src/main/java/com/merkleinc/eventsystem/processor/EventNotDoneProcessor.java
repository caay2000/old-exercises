package com.merkleinc.eventsystem.processor;

import com.merkleinc.eventsystem.SkyEventNotifier;
import com.merkleinc.eventsystem.model.Event;
import com.merkleinc.eventsystem.model.SkyEventNotification;
public class EventNotDoneProcessor implements EventTypeProcessor {

    private SkyEventNotifier skyEventNotifier;

    public EventNotDoneProcessor(SkyEventNotifier skyEventNotifier) {
        this.skyEventNotifier = skyEventNotifier;
    }

    @Override
    public void process(Event event) {
        if (event.isNotDone()) {

            SkyEventNotification notification = new SkyEventNotification();
            notification.setType("NOT_DONE");
            notification.setEventTime(event.getEventTime());
            notification.setEngineerId(event.getEngineerId());
            notification.setVisitNumber(event.getVisitNumber());
            notification.setNotDoneReason(event.getNotDoneReason());

            skyEventNotifier.sendNotification(notification);
        }
    }
}
