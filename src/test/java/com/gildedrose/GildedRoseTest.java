package com.gildedrose;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void sellIn_decreases_gradually() {
        Item item = anItem("foo", 10, 10);

        aGildedRose(item).updateQuality();

        assertEquals(9, item.sellIn);
    }

    @Test
    public void quality_decreases_gradually() {
        Item item = anItem("foo", 10, 10);

        aGildedRose(item).updateQuality();

        assertEquals(9, item.quality);
    }

    @Test
    public void quality_decreases_twice_as_fast_when_sellIn_expired() {
        Item item = anItem("foo", 0, 10);

        aGildedRose(item).updateQuality();

        assertEquals(8, item.quality);
    }

    private Item anItem(String name, int sellIn, int quality) {
        return new Item(name, sellIn, quality);
    }

    private GildedRose aGildedRose(Item... items) {
        return new GildedRose(items);
    }
}
