package com.gildedrose.model;

import com.gildedrose.Item;
import com.gildedrose.model.product.AgedBrie;
import com.gildedrose.model.product.DefaultProduct;

public class ProductFactory {

    public static Product aProduct(Item item) {
        switch (item.name) {
            case Product.AGED_BRIE:
                return new AgedBrie(item);
        }
        return new DefaultProduct(item);
    }
}
