package pl.refactoring.template;

import com.google.common.primitives.Ints;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static junitparams.JUnitParamsRunner.$;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class IntArrayBubbleSorterTest {
    public static final boolean ASCENDING = true;
    public static final boolean DESCENDING = false;

    @Test
    @Parameters(method = "arraysToSortAndResults")
    public void shouldSort(int[] expectedArray, int[] arrayToSort) throws Exception {
        // Given
        Sorter<int[]> sorter = new IntArrayBubbleSorter();

        // When
        sorter.sort(arrayToSort);

        // Then
        assertThat(arrayToSort).containsSequence(expectedArray);
    }

    public static Object[] arraysToSortAndResults() {
        int[] distinctValues = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] distinctTwins = {0, 0, 2, 2, 4, 4, 6, 6, 8, 8};
        int[] sameItems = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

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

    private static int[] sortedCopy(int[] array, boolean ascending) {
        int[] copy = Arrays.copyOf(array, array.length);
        Ints.asList(copy).sort(ascending ? Comparator.naturalOrder() : Comparator.reverseOrder());

        return copy;
    }

    private static int[] shuffledCopy(int[] array) {
        int[] copy = Arrays.copyOf(array, array.length);
        Collections.shuffle(Ints.asList(copy));

        return copy;
    }
}
