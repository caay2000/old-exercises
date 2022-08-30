package com.merkleinc.eventsystem.processor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import org.junit.Test;
import com.merkleinc.eventsystem.model.Event;
import com.merkleinc.eventsystem.utlis.SkyEventNotifierSpy;

public class EventRouteProcessorTest {

    @Test
    public void routeProcessed() {

        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        calendar.add(Calendar.HOUR, 2);
        Date laterToday = calendar.getTime();

        SkyEventNotifierSpy skyEventNotifierSpy = new SkyEventNotifierSpy();
        EventTypeProcessor testee = new EventRouteProcessor(skyEventNotifierSpy);

        Event event = new Event("id", "ROUTE", today, "engineerId", "visitNumber");
        event.setEta(laterToday);

        testee.process(event);

        assertTrue(skyEventNotifierSpy.hasBeenCalled());
        assertEquals("ROUTE", skyEventNotifierSpy.getNotificationsProcessed().get(0).getType());
        assertEquals(today, skyEventNotifierSpy.getNotificationsProcessed().get(0).getEventTime());
        assertEquals("engineerId", skyEventNotifierSpy.getNotificationsProcessed().get(0).getEngineerId());
        assertEquals("visitNumber", skyEventNotifierSpy.getNotificationsProcessed().get(0).getVisitNumber());
        assertEquals(120, skyEventNotifierSpy.getNotificationsProcessed().get(0).getTravelTime());
    }

    @Test
    public void nonRouteNotProcessed() {

        SkyEventNotifierSpy skyEventNotifierSpy = new SkyEventNotifierSpy();
        EventTypeProcessor testee = new EventRouteProcessor(skyEventNotifierSpy);

        Event event = new Event("id", "NOT_ROUTE", new Date(), "engineerId", "visitNumber");

        testee.process(event);

        assertFalse(skyEventNotifierSpy.hasBeenCalled());
    }
}