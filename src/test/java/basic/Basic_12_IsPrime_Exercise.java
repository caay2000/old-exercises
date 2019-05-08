package basic;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class Basic_12_IsPrime_Exercise {

    private List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    @Test
    public void simple_loop() {
        for (Integer number : numbers) {
            System.out.println(number + " - isPrime? " + isPrime(number));
        }
    }

    private boolean isPrime(int number) {
        boolean divisible = false;
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                divisible = true;
                break;
            }
        }
        return number > 1 && !divisible;
    }

    @Test
    public void streams() {

        for (Integer number : numbers) {
            System.out.println(number + " - isPrime? " + isPrimeStream(number));
        }
    }

    private boolean isPrimeStream(int number) {

        //Write your code here

    }
}
