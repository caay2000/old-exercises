package basic;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class Basic_1_Print {

    private List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    @Test
    public void simple_loop() {
        for (int i = 0; i < numbers.size(); i++) {
            System.out.println(numbers.get(i));
        }
    }

    @Test
    public void enhanced_loop() {

        for (Integer e : numbers) {
            System.out.println(e);
        }
    }

    @Test
    public void streams_method_reference() {

        numbers.stream().forEach(System.out::println);
    }

    @Test
    public void stream_lambda() {
        numbers.stream().forEach(e -> System.out.println(e));
    }
}
