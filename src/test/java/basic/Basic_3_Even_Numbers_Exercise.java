package basic;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class Basic_3_Even_Numbers_Exercise {

    private List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    @Test
    public void simple_loop() {
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) % 2 == 0) {
                System.out.println(numbers.get(i));
            }
        }
    }

    @Test
    public void streams() {

        numbers.stream()
                .filter(i -> i % 2 == 0)
                .forEach(e -> System.out.println(e));
    }
}