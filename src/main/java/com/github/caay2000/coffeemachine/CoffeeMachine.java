package com.github.caay2000.coffeemachine;

import com.github.caay2000.coffeemachine.application.Application;
import com.github.caay2000.coffeemachine.application.Money;
import com.github.caay2000.coffeemachine.hardware.CoinSystem;
import com.github.caay2000.coffeemachine.hardware.DrinkMaker;
import com.github.caay2000.coffeemachine.hardware.Pad;

public class CoffeeMachine {

    private final Pad pad;
    private final DrinkMaker drinkMaker;
    private final CoinSystem coinSystem;

    public CoffeeMachine(Pad pad, DrinkMaker drinkMaker) {

        this.pad = pad;
        this.drinkMaker = drinkMaker;
        this.coinSystem = new CoinSystem();
        new Application();
    }

    public void coffee() {
        this.pad.coffee();
    }

    public void tea() {
        this.pad.tea();
    }

    public void chocolate() {
        this.pad.chocolate();
    }

    public void increaseSugar() {
        this.pad.increaseSugar();
    }

    public void decreaseSugar() {
        this.pad.decreaseSugar();
    }

    public void insertCoin(double coin) {
        coinSystem.insertCoin(new Money(coin));
    }

    public void returnMoney() {
        coinSystem.returnMoney();
    }
}
