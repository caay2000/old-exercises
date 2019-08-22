package com.github.caay2000.coffeemachine.application.eventHandler;

import com.github.caay2000.coffeemachine.application.Application;
import com.github.caay2000.coffeemachine.application.Drink;
import com.github.caay2000.coffeemachine.event.Event;
import com.github.caay2000.coffeemachine.event.EventListener;

public class CoffeeEventHandler implements EventListener {

    private final Application application;

    public CoffeeEventHandler(Application application) {
        this.application = application;
    }

    @Override
    public void onNotify(Event event) {
        application.makeDrink(Drink.COFFEE);
    }
}
