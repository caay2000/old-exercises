package com.github.caay2000.coffeemachine.hardware;

import com.github.caay2000.coffeemachine.event.Event;
import com.github.caay2000.coffeemachine.event.EventBus;
import com.github.caay2000.coffeemachine.event.EventListener;
import com.github.caay2000.coffeemachine.event.EventType;

public class Pad {

    private final EventBus eventBus;

    public Pad() {
        this.eventBus = EventBus.getInstance();
        this.eventBus.subscribeTo(new SendMessageHandler(this), EventType.SEND_MESSAGE_EVENT);
    }

    public void coffee() {
        this.eventBus.send(new Event(EventType.COFFEE_EVENT));
    }

    public void tea() {
        this.eventBus.send(new Event(EventType.TEA_EVENT));
    }

    public void chocolate() {
        this.eventBus.send(new Event(EventType.CHOCOLATE_EVENT));
    }

    public void orangeJuice() {
        this.eventBus.send(new Event(EventType.ORANGE_JUICE_EVENT));
    }

    public void increaseSugar() {
        this.eventBus.send(new Event(EventType.INCREASE_SUGAR_EVENT));
    }

    public void decreaseSugar() {
        this.eventBus.send(new Event(EventType.DECREASE_SUGAR_EVENT));
    }

    public void extraHot() {
        this.eventBus.send(new Event(EventType.EXTRA_HOT_EVENT));
    }

    public void sendMessage(String message) {
    }

    private class SendMessageHandler implements EventListener {

        private final Pad pad;

        private SendMessageHandler(Pad pad) {
            this.pad = pad;
        }

        @Override
        public void onNotify(Event event) {
            pad.sendMessage(event.getContent());
        }
    }
}
