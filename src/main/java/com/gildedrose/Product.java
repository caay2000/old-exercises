package com.gildedrose;

public class Product {

    private static final int EXPIRE_DAY = 0;

    private static final int MIN_ITEM_QUALITY = 0;
    private static final int MAX_ITEM_QUALITY = 50;

    private Item item;

    public Product(Item item) {
        this.item = item;
    }

    public String getName() {
        return this.item.name;
    }

    public int getQuality() {
        return this.item.quality;
    }

    public int getDaysToExpire() {
        return this.item.sellIn;
    }

    public boolean isExpired() {
        return this.item.sellIn < EXPIRE_DAY;
    }

    public void expireOneDay() {
        this.item.sellIn--;
    }

    public void decreaseQuality() {
        if (getQuality() > MIN_ITEM_QUALITY) {
            this.item.quality--;
        }
    }

    public void increaseQuality() {
        if (getQuality() < MAX_ITEM_QUALITY) {
            this.item.quality++;
        }
    }

    public void killQuality() {
        this.item.quality = 0;
    }
}
