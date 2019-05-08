import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Exercise5 {
    //Euclid's algorithm
    public static String getTriple(int m, int n) {
        int a = m * m - n * n;
        int b = 2 * m * n;
        int c = m * m + n * n;

        //System.out.println(String.format("m:%s, n:%s, a:%s, b:%s, c:%s", m, n , a, b ,c));

        return String.format("%d %d %d", a, b, c);
    }

    public static List<String> computeTriples(int numberOfValues) {
        List<String> triples = new ArrayList<>();
        int count = 1;

        for (int m = 2; ; m++) {
            for (int n = 1; n < m; n++) {
                triples.add(getTriple(m, n));
                count++;

                if (count > numberOfValues)
                    break;
            }

            if (count > numberOfValues)
                break;
        }

        return triples;
    }

    public static void main(String[] args) {
        //values of a, b, c where a**2 + b**2 == c**2.
        computeTriples(10).forEach(System.out::println);
        System.out.println("Functional:");
        computeTriplesFunctional2(10).forEach(System.out::println);
    }

    private static List<String> computeTriplesFunctional2(int i) {

        return Stream.iterate(2, m -> m+1)
                .flatMap(m -> IntStream.range(1, m)
                        .boxed()
                        .map(e-> getTriple(m, e))
                )
                .limit(i)
                .collect(Collectors.toList());

    }

    private static List<String> computeTriplesFunctional(int number) {
        return Stream.iterate(2, m -> m + 1)
                .flatMap(m -> IntStream.range(1, m)
                        .boxed()
                        .map(n -> getTriple(m, n)))
                .limit(number)
                .collect(Collectors.toList());
    }


}