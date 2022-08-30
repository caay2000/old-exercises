package basic;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class Basic_9_Sum {

    private List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    @Test
    public void simple_loop() {

        int sum = 0;
        for (int i = 0; i < numbers.size(); i++) {
            sum = sum + numbers.get(i);
        }

        System.out.println(sum);
    }

    @Test
    public void streams() {

        int sum = numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println(sum);
    }
}
