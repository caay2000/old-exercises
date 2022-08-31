package com.github.caay2000.coffeemachine.application;

public class Money {

    private double price;

    public Money(double price) {
        this.price = price;
    }

    public void add(Money money) {
        this.price += money.price;
    }

    public boolean isEnough(Money available) {
        return this.price >= available.price;
    }

    public double getPrice() {
        return this.price;
    }
}
