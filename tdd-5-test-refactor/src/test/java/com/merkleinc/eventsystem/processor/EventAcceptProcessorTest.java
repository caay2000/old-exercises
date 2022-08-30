package com.merkleinc.eventsystem.processor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import org.junit.Test;
import com.merkleinc.eventsystem.model.Event;
import com.merkleinc.eventsystem.utlis.SkyEventNotifierSpy;

public class EventAcceptProcessorTest {

    @Test
    public void acceptProcessed() {

        Date date = new Date();

        SkyEventNotifierSpy skyEventNotifierSpy = new SkyEventNotifierSpy();
        EventTypeProcessor testee = new EventAcceptProcessor(skyEventNotifierSpy);

        Event event = new Event("id", "ACCEPT", date, "engineerId", "visitNumber");

        testee.process(event);

        assertTrue(skyEventNotifierSpy.hasBeenCalled());
        assertEquals("ACCEPT", skyEventNotifierSpy.getNotificationsProcessed().get(0).getType());
        assertEquals(date, skyEventNotifierSpy.getNotificationsProcessed().get(0).getEventTime());
        assertEquals("engineerId", skyEventNotifierSpy.getNotificationsProcessed().get(0).getEngineerId());
        assertEquals("visitNumber", skyEventNotifierSpy.getNotificationsProcessed().get(0).getVisitNumber());
    }

    @Test
    public void nonAcceptNotProcessed() {

        SkyEventNotifierSpy skyEventNotifierSpy = new SkyEventNotifierSpy();
        EventTypeProcessor testee = new EventAcceptProcessor(skyEventNotifierSpy);

        Event event = new Event("id", "NOT_ACCEPT", new Date(), "engineerId", "visitNumber");

        testee.process(event);

        assertFalse(skyEventNotifierSpy.hasBeenCalled());
    }
}