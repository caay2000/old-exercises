import java.util.stream.IntStream;
import java.util.stream.Stream;

class Exercise6 {
    public static boolean isPrime(int number) {
        return number > 1 &&
                IntStream.range(2, number)
                        .noneMatch(i -> number % i == 0);
    }

    public static double computeSumOfSqrtOfPrimes(int start, int count) {
        int index = start;
        int computedCount = 0;
        double sum = 0;

        while (computedCount < count) {
            if (isPrime(index)) {
                sum += Math.sqrt(index);
                computedCount++;
            }
            index++;
        }

        return sum;
    }


    public static void main(String[] args) {

        System.out.println(computeSumOfSqrtOfPrimes(101, 51));
        System.out.println(computeSumOfSqrtOfPrimesFunctional(101, 51));
    }

    private static double computeSumOfSqrtOfPrimesFunctional(int start, int count) {
        return Stream.iterate(start, m -> m + 1)
                .filter(n -> isPrime(n))
                .mapToDouble(i -> Math.sqrt(i))
                .limit(count)
                .sum();
    }
}
