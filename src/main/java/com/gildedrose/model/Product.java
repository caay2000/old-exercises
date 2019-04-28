package com.gildedrose.model;

import com.gildedrose.Item;

public class Product {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    private static final int EXPIRE_DAY = 0;
    private static final int MIN_ITEM_QUALITY = 0;
    private static final int MAX_ITEM_QUALITY = 50;

    private Item item;

    public Product(Item item) {
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

    protected void expireOneDay() {
        this.item.sellIn--;
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

    public void processExpireDate() {
        expireOneDay();
    }

    public void processQuality() {

        decreaseQuality();
        if (isExpired()) {
            decreaseQuality();
        }
    }

    public static class Factory {

        public static Product aProduct(Item item) {
            switch (item.name) {
                case Product.AGED_BRIE:
                    return new AgedBrie(item);
                case Product.SULFURAS:
                    return new Sulfuras(item);
                case Product.BACKSTAGE_PASS:
                    return new BackstagePass(item);
            }
            return new Product(item);
        }
    }
}
