package com.gildedrose.model;

import com.gildedrose.Item;
import com.gildedrose.model.product.AgedBrie;
import com.gildedrose.model.product.BackstagePass;
import com.gildedrose.model.product.DefaultProduct;
import com.gildedrose.model.product.Sulfuras;

public class ProductFactory {

    public static Product aProduct(Item item) {
        switch (item.name) {
            case Product.AGED_BRIE:
                return new AgedBrie(item);
            case Product.SULFURAS:
                return new Sulfuras(item);
            case Product.BACKSTAGE_PASS:
                return new BackstagePass(item);
        }
        return new DefaultProduct(item);
    }
}
