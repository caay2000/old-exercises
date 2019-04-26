package com.gildedrose;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GildedRoseTest {

    private static final String DEFAULT_ITEM = "foo";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";

    @Test
    public void sellIn_decreases_gradually() {
        Item item = anItem(DEFAULT_ITEM, 10, 10);

        aGildedRose(item).updateQuality();

        assertEquals(9, item.sellIn);
    }

    @Test
    public void quality_decreases_gradually() {
        Item item = anItem(DEFAULT_ITEM, 1, 10);

        aGildedRose(item).updateQuality();

        assertEquals(9, item.quality);
    }

    @Test
    public void quality_decreases_twice_as_fast_when_sellIn_expired() {
        Item defaultItem = anItem(DEFAULT_ITEM, 0, 10);

        aGildedRose(defaultItem).updateQuality();

        assertEquals(8, defaultItem.quality);
    }

    @Test
    public void quality_decreases_twice_as_fast_when_sellIn_expired_but_quality_is_zero() {
        Item defaultItem = anItem(DEFAULT_ITEM, 0, 0);

        aGildedRose(defaultItem).updateQuality();

        assertEquals(0, defaultItem.quality);
    }

    @Test
    public void quality_is_never_negative() {
        Item item = anItem(DEFAULT_ITEM, 10, 0);

        aGildedRose(item).updateQuality();

        assertEquals(0, item.quality);
    }

    @Test
    public void aged_brie_increases_in_quality_the_older_it_gets() {
        Item agedBrie = anItem(AGED_BRIE, 10, 10);
        Item expiredAgedBrie = anItem(AGED_BRIE, 0, 10);

        aGildedRose(agedBrie, expiredAgedBrie).updateQuality();

        assertEquals(11, agedBrie.quality);
        assertEquals(12, expiredAgedBrie.quality);
    }

    @Test
    public void quality_is_never_more_than_50_for_aged_brie() {
        Item agedBrie_49 = anItem(AGED_BRIE, 0, 49);
        Item agedBrie_50 = anItem(AGED_BRIE, 0, 50);

        aGildedRose(agedBrie_49, agedBrie_50).updateQuality();

        assertEquals(50, agedBrie_49.quality);
        assertEquals(50, agedBrie_50.quality);
    }

    @Test
    public void quality_is_never_more_than_50_for_backstage_pass() {
        Item backstage_pass_10_days = anItem(BACKSTAGE_PASS, 10, 49);
        Item backstage_pass_5_days = anItem(BACKSTAGE_PASS, 5, 49);

        aGildedRose(backstage_pass_10_days, backstage_pass_5_days).updateQuality();

        assertEquals(50, backstage_pass_10_days.quality);
        assertEquals(50, backstage_pass_5_days.quality);
    }

    @Test
    public void sulfuras_never_has_to_be_sold() {
        Item item = anItem(SULFURAS, 10, 10);

        aGildedRose(item).updateQuality();

        assertEquals(10, item.sellIn);
    }

    @Test
    public void sulfuras_never_decreases_quality() {
        Item item = anItem(SULFURAS, 0, 80);

        aGildedRose(item).updateQuality();

        assertEquals(80, item.quality);
    }

    @Test
    public void backstage_pass_increases_quality() {
        Item item = anItem(BACKSTAGE_PASS, 20, 10);

        aGildedRose(item).updateQuality();

        assertEquals(11, item.quality);
    }

    @Test
    public void backstage_pass_increases_quality_less_10_days() {
        Item item_10_days = anItem(BACKSTAGE_PASS, 10, 10);
        Item item_11_days = anItem(BACKSTAGE_PASS, 11, 10);

        aGildedRose(item_10_days, item_11_days).updateQuality();

        assertEquals(12, item_10_days.quality);
        assertEquals(11, item_11_days.quality);
    }

    @Test
    public void backstage_pass_increases_quality_less_5_days() {
        Item item_5_days = anItem(BACKSTAGE_PASS, 5, 10);
        Item item_6_days = anItem(BACKSTAGE_PASS, 6, 10);

        aGildedRose(item_5_days, item_6_days).updateQuality();

        assertEquals(13, item_5_days.quality);
        assertEquals(12, item_6_days.quality);
    }

    @Test
    public void backstage_pass_increases_quality_drops_to_zero_after_concet() {
        Item item = anItem(BACKSTAGE_PASS, 0, 10);

        aGildedRose(item).updateQuality();

        assertEquals(0, item.quality);
    }

    private Item anItem(String name, int sellIn, int quality) {
        return new Item(name, sellIn, quality);
    }

    private GildedRose aGildedRose(Item... items) {
        return new GildedRose(items);
    }
}
