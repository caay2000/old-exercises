package com.github.caay2000.coffeemachine.application;

public enum Drink {

    COFFEE("C", new Money(0.6d)),
    TEA("T", new Money(0.4d)),
    CHOCOLATE("H", new Money(0.5d)),
    ORANGE_JUICE("O", new Money(0.6d));

    private String code;
    private Money price;

    Drink(String code, Money price) {
        this.code = code;
        this.price = price;
    }

    public String getCode() {
        return this.code;
    }

    public Money getPrice() {
        return price;
    }
}
