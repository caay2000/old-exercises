package com.merkleinc.eventsystem;

import java.util.Date;
import org.junit.Assert;
import org.junit.Test;
import com.merkleinc.eventsystem.model.Event;
import com.merkleinc.eventsystem.utlis.EventServiceStub;
import com.merkleinc.eventsystem.utlis.EventTypeProcessorSpy;

public class EventProcessorTest {

    @Test
    public void noEventsRetrieved() {

        EventServiceStub eventServiceStub = new EventServiceStub();
        EventTypeProcessorSpy eventTypeProcessorSpy = new EventTypeProcessorSpy();

        EventProcessor testee = new EventProcessor(eventServiceStub);
        testee.setAcceptProcessor(eventTypeProcessorSpy);
        testee.setRouteProcessor(eventTypeProcessorSpy);
        testee.setStartProcessor(eventTypeProcessorSpy);
        testee.setCompleteProcessor(eventTypeProcessorSpy);
        testee.setNotDoneProcessor(eventTypeProcessorSpy);

        testee.process();

        Assert.assertFalse(eventTypeProcessorSpy.hasBeenCalled());
    }

    @Test
    public void eventIsProcessedByAllTheEventTypeProcessors() {

        EventServiceStub eventService = new EventServiceStub(new Event("id", "dummy", new Date(), "engineerId", "visitNumber"));
        EventTypeProcessorSpy eventTypeProcessorSpy = new EventTypeProcessorSpy();

        EventProcessor testee = new EventProcessor(eventService);
        testee.setAcceptProcessor(eventTypeProcessorSpy);
        testee.setRouteProcessor(eventTypeProcessorSpy);
        testee.setStartProcessor(eventTypeProcessorSpy);
        testee.setCompleteProcessor(eventTypeProcessorSpy);
        testee.setNotDoneProcessor(eventTypeProcessorSpy);

        testee.process();

        Assert.assertTrue(eventTypeProcessorSpy.hasBeenCalled(5));
    }

    @Test
    public void multipleEventsAreProcessedByAllTheEventTypeProcessors() {

        EventServiceStub eventService = new EventServiceStub(
                new Event("id", "dummy", new Date(), "engineerId", "visitNumber"),
                new Event("anotherId", "moreDummies", new Date(), "anotherEngineerId", "anotherVisitNumber"));
        EventTypeProcessorSpy eventTypeProcessorSpy = new EventTypeProcessorSpy();

        EventProcessor testee = new EventProcessor(eventService);
        testee.setAcceptProcessor(eventTypeProcessorSpy);
        testee.setRouteProcessor(eventTypeProcessorSpy);
        testee.setStartProcessor(eventTypeProcessorSpy);
        testee.setCompleteProcessor(eventTypeProcessorSpy);
        testee.setNotDoneProcessor(eventTypeProcessorSpy);

        testee.process();

        Assert.assertTrue(eventTypeProcessorSpy.hasBeenCalled(10));
    }
}