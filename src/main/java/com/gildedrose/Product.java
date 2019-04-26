package com.gildedrose;

public class Product {

    private Item item;

    public Product(Item item) {
        this.item = item;
    }

    public void decreaseQuality() {
        this.item.quality--;
    }

    public void increaseQuality() {
        this.item.quality++;
    }

    public void updateSellIn() {
        this.item.sellIn--;
    }
}
