package com.gildedrose;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GildedRoseTest {

    private static final String DEFAULT_ITEM = "foo";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    @Test
    public void sellIn_decreases_gradually() {
        Item item = anItem(DEFAULT_ITEM, 10, 10);

        aGildedRose(item).updateQuality();

        assertEquals(9, item.sellIn);
    }

    @Test
    public void quality_decreases_gradually() {
        Item item = anItem(DEFAULT_ITEM, 10, 10);

        aGildedRose(item).updateQuality();

        assertEquals(9, item.quality);
    }

    @Test
    public void quality_decreases_twice_as_fast_when_sellIn_expired() {
        Item item = anItem(DEFAULT_ITEM, 0, 10);

        aGildedRose(item).updateQuality();

        assertEquals(8, item.quality);
    }

    @Test
    public void quality_is_never_negative() {
        Item item = anItem(DEFAULT_ITEM, 10, 0);

        aGildedRose(item).updateQuality();

        assertEquals(0, item.quality);
    }

    @Test
    public void aged_brie_increases_in_quality_the_older_it_gets() {
        Item item = anItem(AGED_BRIE, 10, 10);

        aGildedRose(item).updateQuality();

        assertEquals(11, item.quality);
    }

    @Test
    public void quality_is_never_more_than_50() {
        Item item = anItem(AGED_BRIE, 10, 49);

        aGildedRose(item).updateQuality();

        assertEquals(50, item.quality);
    }

    @Test
    public void sulfuras_never_has_to_be_sold() {
        Item item = anItem(SULFURAS, 10, 10);

        aGildedRose(item).updateQuality();

        assertEquals(10, item.sellIn);
    }

    @Test
    public void sulfuras_never_decreases_quality() {
        Item item = anItem(SULFURAS, 10, 10);

        aGildedRose(item).updateQuality();

        assertEquals(10, item.quality);
    }

    private Item anItem(String name, int sellIn, int quality) {
        return new Item(name, sellIn, quality);
    }

    private GildedRose aGildedRose(Item... items) {
        return new GildedRose(items);
    }
}
