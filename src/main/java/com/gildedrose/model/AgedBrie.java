package com.gildedrose.model;

import com.gildedrose.Item;

public class AgedBrie extends Product {

    public AgedBrie(Item item) {
        super(item);
    }

    @Override
    public void processQuality() {
        increaseQuality();
        if (isExpired()) {
            increaseQuality();
        }
    }
}
