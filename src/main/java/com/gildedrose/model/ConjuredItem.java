package com.gildedrose.model;

public class ConjuredItem implements Product {

    static final String NAME = "Conjured";

    private final Product product;

    public ConjuredItem(Product product) {
        this.product = product;
    }

    @Override
    public void processExpireDate() {
        this.product.processExpireDate();
    }

    @Override
    public void processQuality() {
        this.product.processQuality();
        this.product.processQuality();
    }
}
