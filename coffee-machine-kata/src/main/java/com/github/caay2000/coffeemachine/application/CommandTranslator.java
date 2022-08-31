package com.github.caay2000.coffeemachine.application;

public class CommandTranslator {

    public String getCommand(Drink drink, int sugar) {
        return String.format("%s:%s:%s",
                drink.getCode(),
                sugar > 0 ? sugar : "",
                sugar > 0 ? "0" : "");
    }
}
