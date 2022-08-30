package com.gildedrose.model;

import com.gildedrose.Item;
abstract class AbstractProduct implements Product {

    private static final int EXPIRE_DAY = 0;
    private static final int MIN_ITEM_QUALITY = 0;
    private static final int MAX_ITEM_QUALITY = 50;

    private final Item item;

    AbstractProduct(Item item) {
        this.item = item;
    }

    int getDaysToExpire() {
        return this.item.sellIn;
    }

    boolean isExpired() {
        return this.item.sellIn < EXPIRE_DAY;
    }

    void expireOneDay() {
        this.item.sellIn--;
    }

    void decreaseQuality(int value) {
        this.item.quality = Math.max(this.item.quality - value, MIN_ITEM_QUALITY);
    }

    void increaseQuality(int value) {
        this.item.quality = Math.min(this.item.quality + value, MAX_ITEM_QUALITY);
    }

    void killQuality() {
        this.item.quality = 0;
    }
}
