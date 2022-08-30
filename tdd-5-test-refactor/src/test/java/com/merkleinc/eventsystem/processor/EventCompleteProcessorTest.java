package com.merkleinc.eventsystem.processor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import org.junit.Test;
import com.merkleinc.eventsystem.model.Event;
import com.merkleinc.eventsystem.utlis.SkyEventNotifierSpy;

public class EventCompleteProcessorTest {

    @Test
    public void completeProcessed() {

        Date date = new Date();

        SkyEventNotifierSpy skyEventNotifierSpy = new SkyEventNotifierSpy();
        EventTypeProcessor testee = new EventCompleteProcessor(skyEventNotifierSpy);

        Event event = new Event("id", "COMPLETE", date, "engineerId", "visitNumber");
        event.setCompleteStatus("completeStatus");
        event.setCompleteDescription("completeDescription");

        testee.process(event);

        assertTrue(skyEventNotifierSpy.hasBeenCalled());
        assertEquals("COMPLETE", skyEventNotifierSpy.getNotificationsProcessed().get(0).getType());
        assertEquals(date, skyEventNotifierSpy.getNotificationsProcessed().get(0).getEventTime());
        assertEquals("engineerId", skyEventNotifierSpy.getNotificationsProcessed().get(0).getEngineerId());
        assertEquals("visitNumber", skyEventNotifierSpy.getNotificationsProcessed().get(0).getVisitNumber());
        assertEquals("completeStatus", skyEventNotifierSpy.getNotificationsProcessed().get(0).getCompletionStatus());
        assertEquals("completeDescription", skyEventNotifierSpy.getNotificationsProcessed().get(0).getCompletionDescription());
    }

    @Test
    public void nonCompleteNotProcessed() {

        SkyEventNotifierSpy skyEventNotifierSpy = new SkyEventNotifierSpy();
        EventTypeProcessor testee = new EventCompleteProcessor(skyEventNotifierSpy);

        Event event = new Event("id", "NOT_COMPLETE", new Date(), "engineerId", "visitNumber");

        testee.process(event);

        assertFalse(skyEventNotifierSpy.hasBeenCalled());
    }
}