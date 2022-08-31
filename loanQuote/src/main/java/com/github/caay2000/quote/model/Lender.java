package com.github.caay2000.quote.model;

public class Lender {

    private final String name;
    private final double rate;
    private final int available;

    public Lender(String name, double rate, int available) {
        this.name = name;
        this.rate = rate;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public int getAvailable() {
        return available;
    }
}
