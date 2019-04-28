package com.gildedrose.model;

import com.gildedrose.Item;

public class BackstagePass extends Product {

    public BackstagePass(Item item) {
        super(item);
    }

    @Override
    public void processQuality() {
        if (isExpired()) {
            killQuality();
        } else {
            increaseQuality();
            if (getDaysToExpire() < 10) {
                increaseQuality();
            }
            if (getDaysToExpire() < 5) {
                increaseQuality();
            }
        }
    }
}
