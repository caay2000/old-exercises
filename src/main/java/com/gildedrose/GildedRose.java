package com.gildedrose;

import com.gildedrose.model.Product;

class GildedRose {

    private Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            Product product = Product.Factory.aProduct(item);
            product.processExpireDate();
            product.processQuality();
        }
    }
}