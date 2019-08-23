package com.github.caay2000.metropolis.collector;

import java.util.ArrayList;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

public class RandomDataMeterTest {

    @Test
    @Ignore("not a test, only to manually check distribution of random function")
    public void generatesRandomNumbers() {
        RandomDataMeter testee = new RandomDataMeter();
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            values.add(testee.getValue());
        }

        System.out.println(values.stream().filter(e -> e < 50).count());
        System.out.println(values.stream().filter(e -> e > 50 && e < 100).count());
        System.out.println(values.stream().filter(e -> e > 100 && e < 150).count());
        System.out.println(values.stream().filter(e -> e > 150).count());
    }
}