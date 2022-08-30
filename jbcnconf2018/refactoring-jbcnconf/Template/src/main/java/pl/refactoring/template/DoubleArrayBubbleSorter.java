package pl.refactoring.template;

/**
 * Refactoring idea by Wlodek Krakowski
 */
public class DoubleArrayBubbleSorter extends AbstractBubbleSorter<double[]> {

    @Override
    protected void swap(double[] array, int j) {
        double swap = array[j];
        array[j] = array[j + 1];
        array[j + 1] = swap;
    }

    @Override
    protected boolean shouldSwap(double[] array, int j) {
        return array[j] > array[j + 1];
    }

    @Override
    protected int size(double[] array) {
        return array.length;
    }


}
