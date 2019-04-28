package com.gildedrose.model;

import com.gildedrose.Item;

public class ProductFactory {

    public static Product aProduct(Item item) {
        return new Product(item);
    }
}
