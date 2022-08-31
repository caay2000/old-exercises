package com.github.caay2000.quote.utils;

import com.github.caay2000.quote.model.Lender;
public class LenderBuilder {

    private static final String name = "defaultName";

    private double rate = 1d;
    private int available = 100;

    private LenderBuilder() {

    }

    public static Lender aLender(double rate, int available) {
        return new Lender(name, rate, available);
    }

    public static LenderBuilder aLender() {
        return new LenderBuilder();
    }

    public Lender withRate(double rate) {
        return new Lender(name, rate, available);
    }

    public Lender withAvailable(int available) {
        return new Lender(name, rate, available);
    }
}
