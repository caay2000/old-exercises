package com.merkleinc.eventsystem.processor;

import com.merkleinc.eventsystem.SkyEventNotifier;
import com.merkleinc.eventsystem.model.Event;
import com.merkleinc.eventsystem.model.SkyEventNotification;
public class EventCompleteProcessor implements EventTypeProcessor {

    private SkyEventNotifier skyEventNotifier;

    public EventCompleteProcessor(SkyEventNotifier skyEventNotifier) {
        this.skyEventNotifier = skyEventNotifier;
    }

    @Override
    public void process(Event event) {
        if (event.isComplete()) {

            SkyEventNotification notification = new SkyEventNotification();
            notification.setType("COMPLETE");
            notification.setEventTime(event.getEventTime());
            notification.setEngineerId(event.getEngineerId());
            notification.setVisitNumber(event.getVisitNumber());
            notification.setCompletionStatus(event.getCompleteStatus());
            notification.setCompletionDescription(event.getCompleteDescription());

            skyEventNotifier.sendNotification(notification);
        }
    }
}
