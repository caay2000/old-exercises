package com.gildedrose.model;

import com.gildedrose.Item;

class BackstagePass extends AbstractProduct {

    static final String NAME = "Backstage passes to a TAFKAL80ETC concert";

    public BackstagePass(Item item) {
        super(item);
    }

    @Override
    public void processExpireDate() {
        this.expireOneDay();
    }

    @Override
    public void processQuality() {
        if (isExpired()) {
            killQuality();
        } else if (getDaysToExpire() < 5) {
            increaseQuality(3);
        } else if (getDaysToExpire() < 10) {
            increaseQuality(2);
        } else {
            increaseQuality(1);
        }
    }
}
