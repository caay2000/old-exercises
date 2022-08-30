import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Tutorial1 {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        //BASIC

        //printList(numbers);

        //count(numbers);

        //printEvenNumbers(numbers);

        //mapToInt(numbers);

        sumFiltered(numbers);

        //EXERCICE 1 & 2

        //range();

        //match();

        //EXERCICE 3

        //collect(numbers);

        //grouping();

        //limit(numbers);

        //boxed();

        //flatMap();
    }

    private static void printList(List<Integer> numbers) {

        for (int i = 0; i < numbers.size(); i++) {
            System.out.println(numbers.get(i));
        }
        System.out.println("------------");
        for (Integer e : numbers) {
            System.out.println(e);
        }

        System.out.println("------------");
        numbers.stream().forEach(System.out::println);
        System.out.println("------------");

        numbers.stream().forEach(e -> System.out.println(e));
        System.out.println("------------");
    }

    private static void count(List<Integer> numbers) {
        int count = 0;
        for (int i = 0; i < numbers.size(); i++) {
            count++;
        }

        System.out.println(count);
        System.out.println("------------");
        System.out.println(numbers.stream().count());
        System.out.println("------------");
    }

    private static void printEvenNumbers(List<Integer> numbers) {

        for (int i = 0; i < numbers.size(); i++) {
            if (i % 2 == 0) {
                System.out.println(numbers.get(i));
            }
        }
        System.out.println("------------");

        numbers.stream()
                .filter(i -> i % 2 == 0)
                .forEach(e -> System.out.println(e));
        System.out.println("------------");
    }

    private static void mapToInt(List<Integer> numbers) {

        for (int i = 0; i < numbers.size(); i++) {
            System.out.println(numbers.get(i) * 2);
        }

        System.out.println("------------");
        numbers.stream()
                .filter(e -> e % 2 == 0)
                .mapToInt(e -> e * 2)
                .forEach(e -> System.out.println(e));
        System.out.println("------------");
    }

    private static void sumFiltered(List<Integer> numbers) {
        int sum = 0;
        for (int i = 0; i < numbers.size(); i++) {
            if (i % 2 == 0) {
                sum = sum + numbers.get(i);
            }
        }

        System.out.println(sum);
        System.out.println("------------");
        int sum1 = numbers.stream()
                .filter(e -> e % 2 == 0)
                .mapToInt(e -> e)
                .sum();
        System.out.println(sum1);
        System.out.println("------------");
    }

    private static void range() {
        IntStream.range(0, 10).forEach(System.out::println);
        System.out.println("------------");

        IntStream.range(0, 10)
                .map(e -> e * 2)
                .forEach(System.out::println);
    }

    private static void match() {
        System.out.println(
                IntStream.range(0, 10)
                        .allMatch(e -> e % 2 == 0));
        System.out.println("------------");
        System.out.println(IntStream.range(0, 10)
                .anyMatch(e -> e % 2 == 0));
        System.out.println("------------");
    }

    private static void collect(List<Integer> numbers) {

        List<Integer> list = numbers.stream().collect(Collectors.toList());
        printList(list);

        list = IntStream.range(0, 10).boxed().collect(Collectors.toList());
        printList(list);

        Map<Integer, Integer> map = IntStream.range(0, 10).boxed()
                .collect(toMap(e -> e, e -> e * 2));
    }

    private static void grouping() {

        Map<Integer, Integer> map = IntStream.range(0, 10).boxed()
                .collect(toMap(e -> e, e -> e));

        Map<Boolean, List<Integer>> collect = map.keySet().stream()
                .collect(groupingBy(e -> e % 2 == 0));

        System.out.println(collect);
    }

    private static void limit(List<Integer> numbers) {

        numbers.stream().limit(5).forEach(System.out::println);
    }

    private static void boxed() {
        List<Integer> ints = IntStream.of(1, 2, 3, 4, 5)
                .boxed()
                .collect(Collectors.toList());

        System.out.println(ints);
    }

    private static void flatMap() {

        String[][] data = new String[][] {{"a", "b"}, {"c", "d"}, {"e", "f"}};

        Stream<String[]> temp = Arrays.stream(data);
        temp.forEach(System.out::println);

        Stream<String> stringStream = Arrays.stream(data).flatMap(x -> Arrays.stream(x));
        stringStream.forEach(System.out::println);
    }
}
