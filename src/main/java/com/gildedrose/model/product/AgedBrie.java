package com.gildedrose.model.product;

import com.gildedrose.Item;
import com.gildedrose.model.Product;

public class AgedBrie extends Product {

    public AgedBrie(Item item) {
        super(item);
    }

    @Override
    public void processQuality() {
        increaseQuality();
        if (isExpired()) {
            increaseQuality();
        }
    }
}
