package com.github.caay2000.coffeemachine.hardware;

import com.github.caay2000.coffeemachine.event.Event;
import com.github.caay2000.coffeemachine.event.EventBus;
import com.github.caay2000.coffeemachine.event.EventListener;
import com.github.caay2000.coffeemachine.event.EventType;

public class DrinkMaker {

    public DrinkMaker() {

        DrinkMakerEventHandler eventListener = new DrinkMakerEventHandler(this);

        EventBus.getInstance().subscribeTo(eventListener, EventType.MAKE_DRINK);
    }

    public void make(String command) {

    }

    private class DrinkMakerEventHandler implements EventListener {

        private final DrinkMaker drinkMaker;

        private DrinkMakerEventHandler(DrinkMaker drinkMaker) {
            this.drinkMaker = drinkMaker;
        }

        @Override
        public void onNotify(Event event) {
            drinkMaker.make(event.getContent());
        }
    }
}
