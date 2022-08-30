package com.merkleinc.eventsystem.processor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import org.junit.Test;
import com.merkleinc.eventsystem.model.Event;
import com.merkleinc.eventsystem.utlis.SkyEventNotifierSpy;

public class EventStartProcessorTest {

    @Test
    public void startProcessed() {

        Date date = new Date();

        SkyEventNotifierSpy skyEventNotifierSpy = new SkyEventNotifierSpy();
        EventTypeProcessor testee = new EventStartProcessor(skyEventNotifierSpy);

        Event event = new Event("id", "START", date, "engineerId", "visitNumber");
        event.setEstimatedTime(60);

        testee.process(event);

        assertTrue(skyEventNotifierSpy.hasBeenCalled());
        assertEquals("START", skyEventNotifierSpy.getNotificationsProcessed().get(0).getType());
        assertEquals(date, skyEventNotifierSpy.getNotificationsProcessed().get(0).getEventTime());
        assertEquals("engineerId", skyEventNotifierSpy.getNotificationsProcessed().get(0).getEngineerId());
        assertEquals("visitNumber", skyEventNotifierSpy.getNotificationsProcessed().get(0).getVisitNumber());
        assertEquals(60, skyEventNotifierSpy.getNotificationsProcessed().get(0).getEstimatedTime());
    }

    @Test
    public void nonStartNotProcessed() {

        SkyEventNotifierSpy skyEventNotifierSpy = new SkyEventNotifierSpy();
        EventTypeProcessor testee = new EventStartProcessor(skyEventNotifierSpy);

        Event event = new Event("id", "NOT_START", new Date(), "engineerId", "visitNumber");

        testee.process(event);

        assertFalse(skyEventNotifierSpy.hasBeenCalled());
    }
}