package com.gildedrose;

import com.gildedrose.model.Product;
import com.gildedrose.model.ProductFactory;

class GildedRose {

    private Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            Product product = ProductFactory.aProduct(item);
            product.processExpireDate();
            product.processQuality();
        }
    }
}