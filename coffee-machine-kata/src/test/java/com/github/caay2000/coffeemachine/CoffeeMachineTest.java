package com.github.caay2000.coffeemachine;

import org.junit.Assert;
import org.junit.Test;
import com.github.caay2000.coffeemachine.event.EventBus;
import com.github.caay2000.coffeemachine.hardware.CoinSystem;
import com.github.caay2000.coffeemachine.mocks.DrinkMakerSpy;
import com.github.caay2000.coffeemachine.mocks.PadSpy;

public class CoffeeMachineTest {

    private PadSpy pad;
    private DrinkMakerSpy drinkMaker;
    private CoinSystem coinSystem;

    @Test
    public void coffee() {

        CoffeeMachine testSubject = aCoffeeMachine();

        testSubject.insertCoin(1d);
        testSubject.coffee();

        Assert.assertTrue(drinkMaker.called(1));
        Assert.assertEquals("C::", drinkMaker.getCommands().get(0));
    }

    @Test
    public void tea() {

        CoffeeMachine testSubject = aCoffeeMachine();

        testSubject.insertCoin(1d);
        testSubject.tea();

        Assert.assertTrue(drinkMaker.called(1));
        Assert.assertEquals("T::", drinkMaker.getCommands().get(0));
    }

    @Test
    public void chocolate() {

        CoffeeMachine testSubject = aCoffeeMachine();

        testSubject.insertCoin(1d);
        testSubject.chocolate();

        Assert.assertTrue(drinkMaker.called(1));
        Assert.assertEquals("H::", drinkMaker.getCommands().get(0));
    }

    @Test
    public void increaseSugar() {

        CoffeeMachine testSubject = aCoffeeMachine();

        testSubject.insertCoin(1d);
        testSubject.increaseSugar();
        testSubject.coffee();

        Assert.assertTrue(drinkMaker.called(1));
        Assert.assertEquals("C:1:0", drinkMaker.getCommands().get(0));
    }

    @Test
    public void increaseSugar_overflow() {

        CoffeeMachine testSubject = aCoffeeMachine();

        testSubject.insertCoin(1d);
        testSubject.increaseSugar();
        testSubject.increaseSugar();
        testSubject.increaseSugar();
        testSubject.coffee();

        Assert.assertTrue(drinkMaker.called(1));
        Assert.assertEquals("C:2:0", drinkMaker.getCommands().get(0));
    }

    @Test
    public void increaseSugar_overflow_message() {

        CoffeeMachine testSubject = aCoffeeMachine();

        testSubject.increaseSugar();
        testSubject.increaseSugar();
        testSubject.increaseSugar();

        Assert.assertTrue(pad.called(1));
        Assert.assertEquals("Cannot increase sugar", pad.getMessages().get(0));
    }

    @Test
    public void decreaseSugar() {

        CoffeeMachine testSubject = aCoffeeMachine();

        testSubject.insertCoin(1d);
        testSubject.increaseSugar();
        testSubject.increaseSugar();
        testSubject.decreaseSugar();
        testSubject.coffee();

        Assert.assertTrue(drinkMaker.called(1));
        Assert.assertEquals("C:1:0", drinkMaker.getCommands().get(0));
    }

    @Test
    public void decreaseSugar_negative() {

        CoffeeMachine testSubject = aCoffeeMachine();

        testSubject.insertCoin(1d);
        testSubject.decreaseSugar();
        testSubject.coffee();
        Assert.assertTrue(drinkMaker.called(1));
        Assert.assertEquals("C::", drinkMaker.getCommands().get(0));
    }

    @Test
    public void decreaseSugar_negative_message() {

        CoffeeMachine testSubject = aCoffeeMachine();

        testSubject.decreaseSugar();

        Assert.assertTrue(pad.called(1));
        Assert.assertEquals("Cannot decrease sugar", pad.getMessages().get(0));
    }

    @Test
    public void not_enough_money() {

        CoffeeMachine testSubject = aCoffeeMachine();

        testSubject.coffee();

        Assert.assertEquals("Not enough money, missing 0.6", pad.getMessages().get(0));
    }

    @Test
    public void not_enough_money_missing_some_money() {

        CoffeeMachine testSubject = aCoffeeMachine();

        testSubject.insertCoin(0.12d);
        testSubject.coffee();

        Assert.assertEquals("Not enough money, missing 0.48", pad.getMessages().get(0));
    }

    @Test
    public void enough_money() {

        CoffeeMachine testSubject = aCoffeeMachine();

        testSubject.insertCoin(0.6d);
        testSubject.coffee();

        Assert.assertTrue(drinkMaker.called(1));
        Assert.assertEquals("C::", drinkMaker.getCommands().get(0));
    }

    @Test
    public void more_than_enough_money() {

        CoffeeMachine testSubject = aCoffeeMachine();

        testSubject.insertCoin(0.6d);
        testSubject.insertCoin(0.4d);
        testSubject.coffee();

        Assert.assertTrue(drinkMaker.called(1));
        Assert.assertEquals("C::", drinkMaker.getCommands().get(0));
    }

    @Test
    public void return_money() {

        CoffeeMachine testSubject = aCoffeeMachine();

        testSubject.insertCoin(1d);
        testSubject.returnMoney();
        testSubject.tea();

        Assert.assertEquals("Not enough money, missing 0.4", pad.getMessages().get(0));
    }

    private CoffeeMachine aCoffeeMachine() {

        EventBus.getInstance().reset();

        this.pad = new PadSpy();
        this.drinkMaker = new DrinkMakerSpy();
        return new CoffeeMachine(pad, drinkMaker);
    }
}