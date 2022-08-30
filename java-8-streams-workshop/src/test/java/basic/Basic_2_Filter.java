package basic;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class Basic_2_Filter {

    private List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    @Test
    public void simple_loop() {
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) == 5) {
                System.out.println(numbers.get(i));
            }
        }
    }

    @Test
    public void enhanced_loop() {

        for (Integer e : numbers) {
            if (e == 5) {
                System.out.println(e);
            }
        }
    }

    @Test
    public void stream_lambda() {
        numbers.stream()
                .filter(e -> e == 5)
                .forEach(System.out::println);
    }
}