package basic;

import java.util.stream.IntStream;
import org.junit.Test;

public class Basic_11_Match {

    @Test
    public void streams_allMatch() {

        boolean match = IntStream.range(0, 10)
                .allMatch(e -> e % 2 == 0);

        System.out.println(match);
    }

    @Test
    public void streams_anyMatch() {

        boolean match = IntStream.range(0, 10)
                .anyMatch(e -> e % 2 == 0);

        System.out.println(match);
    }
}
