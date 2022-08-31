package com.github.caay2000.coffeemachine.application;

import com.github.caay2000.coffeemachine.application.eventHandler.ChocolateEventHandler;
import com.github.caay2000.coffeemachine.application.eventHandler.CoffeeEventHandler;
import com.github.caay2000.coffeemachine.application.eventHandler.DecreaseSugarEventHandler;
import com.github.caay2000.coffeemachine.application.eventHandler.ExtraHotEventHandler;
import com.github.caay2000.coffeemachine.application.eventHandler.IncreaseSugarEventHandler;
import com.github.caay2000.coffeemachine.application.eventHandler.InsertCoinEventHandler;
import com.github.caay2000.coffeemachine.application.eventHandler.OrangeJuiceEventHandler;
import com.github.caay2000.coffeemachine.application.eventHandler.ReturnMoneyEventHandler;
import com.github.caay2000.coffeemachine.application.eventHandler.TeaEventHandler;
import com.github.caay2000.coffeemachine.event.Event;
import com.github.caay2000.coffeemachine.event.EventBus;
import com.github.caay2000.coffeemachine.event.EventType;
public class Application {

    private final CommandTranslator commandTranslator;
    private int sugar = 0;
    private boolean extraHot = false;
    private Money money = new Money(0d);

    public Application() {

        EventBus.getInstance().subscribeTo(new CoffeeEventHandler(this), EventType.COFFEE_EVENT);
        EventBus.getInstance().subscribeTo(new TeaEventHandler(this), EventType.TEA_EVENT);
        EventBus.getInstance().subscribeTo(new ChocolateEventHandler(this), EventType.CHOCOLATE_EVENT);
        EventBus.getInstance().subscribeTo(new IncreaseSugarEventHandler(this), EventType.INCREASE_SUGAR_EVENT);
        EventBus.getInstance().subscribeTo(new DecreaseSugarEventHandler(this), EventType.DECREASE_SUGAR_EVENT);
        EventBus.getInstance().subscribeTo(new InsertCoinEventHandler(this), EventType.INSERT_COIN_EVENT);
        EventBus.getInstance().subscribeTo(new ReturnMoneyEventHandler(this), EventType.RETURN_MONEY_EVENT);
        EventBus.getInstance().subscribeTo(new OrangeJuiceEventHandler(this), EventType.ORANGE_JUICE_EVENT);
        EventBus.getInstance().subscribeTo(new ExtraHotEventHandler(this), EventType.EXTRA_HOT_EVENT);

        this.commandTranslator = new CommandTranslator();
    }

    public void increaseSugar() {
        if (this.sugar < 2) {
            this.sugar++;
        } else {
            EventBus.getInstance().send(new Event(EventType.SEND_MESSAGE_EVENT, "Cannot increase sugar"));
        }
    }

    public void decreaseSugar() {
        if (sugar > 0) {
            this.sugar--;
        } else {
            EventBus.getInstance().send(new Event(EventType.SEND_MESSAGE_EVENT, "Cannot decrease sugar"));
        }
    }

    public void toggleExtraHot() {
        this.extraHot = !this.extraHot;
    }

    public void addCoin(Money coin) {
        this.money.add(coin);
    }

    public void returnMoney() {
        this.money = new Money(0d);
    }

    public void makeDrink(Drink drink) {
        if (this.money.isEnough(drink.getPrice())) {
            EventBus.getInstance().send(new Event(EventType.MAKE_DRINK, commandTranslator.getCommand(drink, sugar)));
        } else {
            EventBus.getInstance().send(new Event(EventType.SEND_MESSAGE_EVENT, "Not enough money, missing " + getMissingMoney(drink)));
        }
    }

    private double getMissingMoney(Drink drink) {
        return drink.getPrice().getPrice() - this.money.getPrice();
    }
}
