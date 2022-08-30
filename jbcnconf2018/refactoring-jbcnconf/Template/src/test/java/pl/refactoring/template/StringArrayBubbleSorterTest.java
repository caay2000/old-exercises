package pl.refactoring.template;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static junitparams.JUnitParamsRunner.$;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by w.krakowski on 9/14/2015.
 */
@RunWith(JUnitParamsRunner.class)
public class StringArrayBubbleSorterTest {
    public static final boolean ASCENDING = true;
    public static final boolean DESCENDING = false;

    @Test
    @Parameters(method = "arraysToSortAndResults")
    public void shouldSort(String[] expectedArray, String[] arrayToSort) throws Exception {
        // Given
        Sorter<String[]> sorter = new ComparableArrayBubbleSorter<>();

        // When
        sorter.sort(arrayToSort);

        // Then
        assertThat(arrayToSort).containsSequence(expectedArray);
    }

    public static Object[] arraysToSortAndResults() {
        String[] distinctValues = {"Paul", "Peter", "Adam", "John", "James"};
        String[] distinctTwins = {"Paul", "Paul", "Peter", "Peter", "Adam", "Adam", "John", "John", "James", "James"};
        String[] sameItems = {"Adam", "Adam", "Adam", "Adam", "Adam", "Adam"};

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

    private static String[] sortedCopy(String[] array, boolean ascending) {
        String[] copy = Arrays.copyOf(array, array.length);
        Arrays.asList(copy).sort(ascending ? Comparator.naturalOrder() : Comparator.reverseOrder());

        return copy;
    }

    private static String[] shuffledCopy(String[] array) {
        String[] copy = Arrays.copyOf(array, array.length);
        Collections.shuffle(Arrays.asList(copy));

        return copy;
    }

}
