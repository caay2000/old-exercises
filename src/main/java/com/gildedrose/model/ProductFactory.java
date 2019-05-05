package com.gildedrose.model;

import com.gildedrose.Item;

public class ProductFactory {

    private ProductFactory() {
    }

    public static Product aProduct(Item item) {
        Product product = createProduct(item);
        if (item.name.startsWith(ConjuredItem.NAME)) {
            return new ConjuredItem(product);
        }
        return product;
    }

    private static Product createProduct(Item item) {
        if (AgedBrie.NAME.contains(item.name)) {
            return new AgedBrie(item);
        } else if (Sulfuras.NAME.contains(item.name)) {
            return new Sulfuras(item);
        } else if (BackstagePass.NAME .contains(item.name)) {
            return new BackstagePass(item);
        }
        return new DefaultProduct(item);
    }
}