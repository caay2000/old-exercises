package basic;

import java.util.stream.IntStream;
import org.junit.Test;

public class Basic_10_Range {

    @Test
    public void streams_range() {
        IntStream.range(0, 10)
                .forEach(System.out::println);
    }

    @Test
    public void streams_range_with_map() {
        IntStream.range(0, 10)
                .map(e -> e * 2)
                .forEach(System.out::println);
    }

}
