package com.gildedrose.model;

import com.gildedrose.Item;
public class DefaultProduct extends AbstractProduct {

    public DefaultProduct(Item item) {
        super(item);
    }

    public void processExpireDate() {
        expireOneDay();
    }

    public void processQuality() {

        decreaseQuality();
        if (isExpired()) {
            decreaseQuality();
        }
    }
}
