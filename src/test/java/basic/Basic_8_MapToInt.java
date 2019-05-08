package basic;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class Basic_8_MapToInt {

    private List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    @Test
    public void simple_loop() {
        for (int i = 0; i < numbers.size(); i++) {
            System.out.println(numbers.get(i) * 2);
        }
    }

    @Test
    public void streams() {
        numbers.stream()
                .mapToInt(e -> e * 2)
                .forEach(e -> System.out.println(e));
    }
}
