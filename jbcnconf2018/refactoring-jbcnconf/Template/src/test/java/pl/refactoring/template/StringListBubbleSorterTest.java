package pl.refactoring.template;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.*;

import static junitparams.JUnitParamsRunner.$;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by w.krakowski on 9/14/2015.
 */
@RunWith(JUnitParamsRunner.class)
public class StringListBubbleSorterTest {

    public static final boolean ASCENDING = true;
    public static final boolean DESCENDING = false;

    @Test
    @Parameters(method = "arraysToSortAndResults")
    public void shouldSortIntegersInWrongOrder(List<String> list, List<String> sortedList) throws Exception {
        // Given
        Sorter<List<String>> sorter = new ComparableListBubbleSorter<>();

        // When
        sorter.sort(list);

        // Then
        assertThat(list).isSubsetOf(sortedList);
    }
    
    public static Object[] arraysToSortAndResults() {
        List<String> distinctValues = Arrays.asList("Paul", "Peter", "Adam", "John", "James");
        List<String> distinctTwins =  Arrays.asList("Paul", "Paul", "Peter", "Peter", "Adam", "Adam", "John", "John", "James", "James");
        List<String> sameItems =  Arrays.asList("Adam", "Adam", "Adam", "Adam", "Adam", "Adam");

        return $(
                $(sortedCopy(distinctValues, ASCENDING), distinctValues),
                $(sortedCopy(distinctValues, ASCENDING), sortedCopy(distinctValues, DESCENDING)),
                $(sortedCopy(distinctValues, ASCENDING), shuffledCopy(distinctValues)),
                $(sortedCopy(distinctTwins, ASCENDING), distinctTwins),
                $(sortedCopy(distinctTwins, ASCENDING), sortedCopy(distinctTwins, DESCENDING)),
                $(sortedCopy(distinctTwins, ASCENDING), shuffledCopy(distinctTwins)),
                $(sortedCopy(sameItems, ASCENDING), sameItems),
                $(sortedCopy(sameItems, ASCENDING), sortedCopy(sameItems, DESCENDING))
        );
    }

    private static List<String> sortedCopy(List<String> list, boolean ascending) {
        List<String> copy = new ArrayList<>();
        copy.addAll(list);

        copy.sort(ascending ? Comparator.naturalOrder() : Comparator.reverseOrder());

        return copy;
    }

    private static List<String> shuffledCopy(List<String> list) {
        List<String> copy = new ArrayList<>();
        copy.addAll(list);

        Collections.shuffle(copy);

        return copy;
    }
}
