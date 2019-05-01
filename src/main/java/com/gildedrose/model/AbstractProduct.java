package com.gildedrose.model;

import com.gildedrose.Item;
public abstract class AbstractProduct implements Product {

    private static final int EXPIRE_DAY = 0;
    private static final int MIN_ITEM_QUALITY = 0;
    private static final int MAX_ITEM_QUALITY = 50;

    private Item item;

    public AbstractProduct(Item item) {
        this.item = item;
    }

    private int getQuality() {
        return this.item.quality;
    }

    protected int getDaysToExpire() {
        return this.item.sellIn;
    }

    protected boolean isExpired() {
        return this.item.sellIn < EXPIRE_DAY;
    }

    protected void expireDays(int value) {
        this.item.sellIn = this.item.sellIn - value;
    }

    protected void decreaseQuality() {
        if (getQuality() > MIN_ITEM_QUALITY) {
            this.item.quality--;
        }
    }

    protected void increaseQuality() {
        if (getQuality() < MAX_ITEM_QUALITY) {
            this.item.quality++;
        }
    }

    protected void killQuality() {
        this.item.quality = 0;
    }

    abstract public void processExpireDate();
    abstract public void processQuality();
}
