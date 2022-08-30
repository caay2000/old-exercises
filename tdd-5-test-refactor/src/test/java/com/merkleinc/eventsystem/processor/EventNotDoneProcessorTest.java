package com.merkleinc.eventsystem.processor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import org.junit.Test;
import com.merkleinc.eventsystem.model.Event;
import com.merkleinc.eventsystem.utlis.SkyEventNotifierSpy;

public class EventNotDoneProcessorTest {

    @Test
    public void notDoneProcessed() {

        Date date = new Date();

        SkyEventNotifierSpy skyEventNotifierSpy = new SkyEventNotifierSpy();
        EventTypeProcessor testee = new EventNotDoneProcessor(skyEventNotifierSpy);

        Event event = new Event("id", "NOT_DONE", date, "engineerId", "visitNumber");
        event.setNotDoneReason("notDoneReason");

        testee.process(event);

        assertTrue(skyEventNotifierSpy.hasBeenCalled());
        assertEquals("NOT_DONE", skyEventNotifierSpy.getNotificationsProcessed().get(0).getType());
        assertEquals(date, skyEventNotifierSpy.getNotificationsProcessed().get(0).getEventTime());
        assertEquals("engineerId", skyEventNotifierSpy.getNotificationsProcessed().get(0).getEngineerId());
        assertEquals("visitNumber", skyEventNotifierSpy.getNotificationsProcessed().get(0).getVisitNumber());
        assertEquals("notDoneReason", skyEventNotifierSpy.getNotificationsProcessed().get(0).getNotDoneReason());
    }

    @Test
    public void nonNotDoneNotProcessed() {

        SkyEventNotifierSpy skyEventNotifierSpy = new SkyEventNotifierSpy();
        EventTypeProcessor testee = new EventNotDoneProcessor(skyEventNotifierSpy);

        Event event = new Event("id", "DONE", new Date(), "engineerId", "visitNumber");

        testee.process(event);

        assertFalse(skyEventNotifierSpy.hasBeenCalled());
    }
}