package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class Basic_5_Collect_Filtered_Exercise {

    private List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    @Test
    public void simple_loop() {

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) % 2 == 0) {
                list.add(numbers.get(i));
            }
        }

        printList(list);
    }

    @Test
    public void streams() {

        //Write your code here

    }

    private void printList(List<Integer> list) {
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }
}
