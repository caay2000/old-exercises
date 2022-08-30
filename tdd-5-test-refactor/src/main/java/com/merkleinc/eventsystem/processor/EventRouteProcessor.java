package com.merkleinc.eventsystem.processor;

import com.merkleinc.eventsystem.SkyEventNotifier;
import com.merkleinc.eventsystem.model.Event;
import com.merkleinc.eventsystem.model.SkyEventNotification;
public class EventRouteProcessor implements EventTypeProcessor {

    private SkyEventNotifier skyEventNotifier;

    public EventRouteProcessor(SkyEventNotifier skyEventNotifier) {
        this.skyEventNotifier = skyEventNotifier;
    }

    @Override
    public void process(Event event) {
        if (event.isRoute()) {

            SkyEventNotification notification = new SkyEventNotification();
            notification.setType("ROUTE");
            notification.setEventTime(event.getEventTime());
            notification.setEngineerId(event.getEngineerId());
            notification.setVisitNumber(event.getVisitNumber());
            notification.setTravelTime(getTravelTime(event));

            skyEventNotifier.sendNotification(notification);
        }
    }

    private int getTravelTime(Event event) {
        long milliseconds = event.getEta().getTime() - event.getEventTime().getTime();
        return new Long(milliseconds / (60 * 1000)).intValue();
    }
}
