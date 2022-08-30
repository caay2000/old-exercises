package com.gildedrose.model;

import com.gildedrose.Item;

class Sulfuras extends AbstractProduct {

    static final String NAME = "Sulfuras, Hand of Ragnaros";

    public Sulfuras(Item item) {
        super(item);
    }

    @Override
    public void processExpireDate() {
        // empty, no expires
    }

    @Override
    public void processQuality() {
        // empty, no quality changes
    }
}
