package com.gildedrose;

class GildedRose {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    private static final int MAX_ITEM_QUALITY = 50;
    private static final int MIN_ITEM_QUALITY = 0;

    private Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (item.name.equals(AGED_BRIE) || item.name.equals(BACKSTAGE_PASS)) {
                if (item.quality < MAX_ITEM_QUALITY) {
                    item.quality = item.quality + 1;

                    if (item.name.equals(BACKSTAGE_PASS)) {

                        if (item.quality < MAX_ITEM_QUALITY) {
                            if (item.sellIn < 11) {
                                item.quality = item.quality + 1;
                            }
                            if (item.sellIn < 6) {
                                item.quality = item.quality + 1;
                            }
                        }
                    }
                }
            } else {
                if (!item.name.equals(SULFURAS)) {
                    if (item.quality > MIN_ITEM_QUALITY) {
                        item.quality = item.quality - 1;
                    }
                }
            }

            if (!item.name.equals(SULFURAS)) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < MIN_ITEM_QUALITY) {
                if (item.name.equals(AGED_BRIE)) {
                    if (item.quality < MAX_ITEM_QUALITY) {
                        item.quality = item.quality + 1;
                    }
                } else {
                    if (item.name.equals(BACKSTAGE_PASS)) {
                        item.quality = item.quality - item.quality;
                    } else {
                        if (item.quality > MIN_ITEM_QUALITY) {
                            item.quality = item.quality - 1;
                        }
                    }
                }
            }
        }
    }
}
