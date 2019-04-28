package com.gildedrose;

import com.gildedrose.model.Product;
import com.gildedrose.model.ProductFactory;
class GildedRose {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    //private List<Product> products;

    private Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            Product product = ProductFactory.aProduct(item);
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