package com.gildedrose;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
class GildedRose {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    private List<Product> products;

    public GildedRose(Item[] items) {
        products = Arrays.asList(items).stream()
                .map(e -> new Product(e))
                .collect(Collectors.toList());
    }

    public void updateQuality() {
        for (Product product : products) {

            processExpireDate(product);
            processQuality(product);
        }
    }

    private void processQuality(Product product) {
        if (product.getName().equals(SULFURAS)) {
            return;
        }
        if (product.getName().equals(AGED_BRIE)) {
            product.increaseQuality();
            if (product.isExpired()) {
                product.increaseQuality();
            }
        } else if (product.getName().equals(BACKSTAGE_PASS)) {
            if (product.isExpired()) {
                product.killQuality();
            } else {
                product.increaseQuality();
                if (product.getDaysToExpire() < 10) {
                    product.increaseQuality();
                }
                if (product.getDaysToExpire() < 5) {
                    product.increaseQuality();
                }
            }
        } else {
            product.decreaseQuality();
            if (product.isExpired()) {
                product.decreaseQuality();
            }
        }
    }

    private void processExpireDate(Product product) {
        if (product.getName().equals(SULFURAS)) {
            return;
        }
        product.expireOneDay();
    }
}