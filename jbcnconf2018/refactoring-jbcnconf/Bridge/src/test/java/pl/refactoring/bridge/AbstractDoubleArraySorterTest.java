package pl.refactoring.bridge;

import com.google.common.primitives.Doubles;
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
public abstract class AbstractDoubleArraySorterTest {

    public static final boolean ASCENDING = true;
    public static final boolean DESCENDING = false;

    protected abstract Sorter<double[]> getDoubleArraySorter();

    @Test
    @Parameters(method = "arraysToSortAndResults")
    public void shouldSort(double[] expectedArray, double[] arrayToSort) throws Exception {
        // Given
        Sorter<double[]> sorter = getDoubleArraySorter();

        // When
        sorter.sort(arrayToSort);

        // Then
        assertThat(arrayToSort).containsSequence(expectedArray);
    }

    public static Object[] arraysToSortAndResults() {
        double[] distinctValues = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        double[] distinctTwins = {0, 0, 2, 2, 4, 4, 6, 6, 8, 8};
        double[] sameItems = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

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

    private static double[] sortedCopy(double[] array, boolean ascending) {
        double[] copy = Arrays.copyOf(array, array.length);
        Doubles.asList(copy).sort(ascending ? Comparator.naturalOrder() : Comparator.reverseOrder());

        return copy;
    }

    private static double[] shuffledCopy(double[] array) {
        double[] copy = Arrays.copyOf(array, array.length);
        Collections.shuffle(Doubles.asList(copy));

        return copy;
    }
}
