package com.gildedrose.model;

import com.gildedrose.Item;

class AgedBrie extends AbstractProduct {

    static final String NAME = "Aged Brie";

    public AgedBrie(Item item) {
        super(item);
    }

    @Override
    public void processExpireDate() {
        this.expireOneDay();
    }

    @Override
    public void processQuality() {
        if (isExpired()) {
            increaseQuality(2);
        } else {
            increaseQuality(1);
        }
    }
}
