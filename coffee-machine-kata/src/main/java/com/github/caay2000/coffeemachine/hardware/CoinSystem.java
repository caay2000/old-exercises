package com.github.caay2000.coffeemachine.hardware;

import com.github.caay2000.coffeemachine.application.Money;
import com.github.caay2000.coffeemachine.event.Event;
import com.github.caay2000.coffeemachine.event.EventBus;
import com.github.caay2000.coffeemachine.event.EventType;
public class CoinSystem {

    public void insertCoin(Money coin) {
        EventBus.getInstance().send(new Event(EventType.INSERT_COIN_EVENT, coin));
    }

    public void returnMoney() {
        EventBus.getInstance().send(new Event(EventType.RETURN_MONEY_EVENT));
    }
}
