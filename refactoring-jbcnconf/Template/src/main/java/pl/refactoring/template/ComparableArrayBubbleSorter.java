package pl.refactoring.template;

/**
 * Refactoring idea by Wlodek Krakowski
 */
public class ComparableArrayBubbleSorter<T extends Comparable<T>> extends AbstractBubbleSorter<T[]> {

    @Override
    protected void swap(T[] array, int j) {
        T swap = array[j];
        array[j] = array[j + 1];
        array[j + 1] = swap;
    }

    @Override
    protected boolean shouldSwap(T[] array, int j) {
        return array[j].compareTo(array[j + 1]) > 0;
    }

    @Override
    protected int size(T[] array) {
        return array.length;
    }
}
