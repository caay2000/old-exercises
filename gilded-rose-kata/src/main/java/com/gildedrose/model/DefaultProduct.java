package com.gildedrose.model;

import com.gildedrose.Item;

class DefaultProduct extends AbstractProduct {

    public DefaultProduct(Item item) {
        super(item);
    }

    public void processExpireDate() {
        expireOneDay();
    }

    public void processQuality() {

        if (isExpired()) {
            decreaseQuality(2);
        } else {
            decreaseQuality(1);
        }
    }
}
