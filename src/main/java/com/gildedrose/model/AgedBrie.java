package com.gildedrose.model;

import com.gildedrose.Item;

public class AgedBrie extends AbstractProduct {

    static final String AGED_BRIE = "Aged Brie";

    public AgedBrie(Item item) {
        super(item);
    }

    @Override
    public void processExpireDate() {
        this.expireDays(1);
    }

    @Override
    public void processQuality() {
        increaseQuality();
        if (isExpired()) {
            increaseQuality();
        }
    }
}
