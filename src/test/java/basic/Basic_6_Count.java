package basic;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class Basic_6_Count {

    private List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    @Test
    public void simple_loop() {
        int count = 0;
        for (int i = 0; i < numbers.size(); i++) {
            count++;
        }
        System.out.println(count);
    }

    @Test
    public void streams() {

        System.out.println(numbers.stream().count());
    }
}