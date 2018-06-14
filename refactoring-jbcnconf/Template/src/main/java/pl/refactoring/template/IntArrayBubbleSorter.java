package pl.refactoring.template;

/**
 * Refactoring idea by Wlodek Krakowski
 */
public class IntArrayBubbleSorter extends AbstractBubbleSorter<int[]> {

    @Override
    protected void swap(int[] array, int j) {
        int swap = array[j];
        array[j] = array[j + 1];
        array[j + 1] = swap;
    }

    @Override
    protected boolean shouldSwap(int[] array, int j) {
        return array[j] > array[j + 1];
    }

    @Override
    protected int size(int[] array) {
        return array.length;
    }

}