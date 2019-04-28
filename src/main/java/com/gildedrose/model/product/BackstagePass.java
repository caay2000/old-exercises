package com.gildedrose.model.product;

import com.gildedrose.Item;
import com.gildedrose.model.Product;

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
