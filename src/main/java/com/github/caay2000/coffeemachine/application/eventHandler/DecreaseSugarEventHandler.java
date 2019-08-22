package com.github.caay2000.coffeemachine.application.eventHandler;

import com.github.caay2000.coffeemachine.application.Application;
import com.github.caay2000.coffeemachine.event.Event;
import com.github.caay2000.coffeemachine.event.EventListener;

public class DecreaseSugarEventHandler implements EventListener {

    private final Application application;

    public DecreaseSugarEventHandler(Application application) {
        this.application = application;
    }

    @Override
    public void onNotify(Event event) {
        application.decreaseSugar();
    }
}
