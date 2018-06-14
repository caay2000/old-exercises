package pl.refactoring.tutor.bridge;


public class CombSorter<T> implements Sorter<T>  {
    private SortHandlerFactory<T> sortHandlerFactory = null;

    public CombSorter(SortHandlerFactory<T> sortHandlerFactory) {
        this.sortHandlerFactory = sortHandlerFactory;
    }

    // https://en.wikipedia.org/wiki/Comb_sort
    public void sort(T array) {
        SortHandler<T> sortHandler = sortHandlerFactory.createSortHandler(array);
        int length = sortHandler.getLength();

        if (length <= 1)
            return;

        int gap = length;
        boolean swapped = true;

        while (gap > 1 || swapped) {
            gap = nextGap(gap);
            swapped = false;
            for (int i = 0; i + gap < length; i++) {
                if (sortHandler.compare(i, i + gap) > 0) {
                    sortHandler.swap(i, i + gap);
                    swapped = true;
                }
            }
        }
    }

    private int nextGap(int gap) {
        if (gap > 1) {
            gap = (int) (gap / 1.3);

            if (gap < 1)
                gap = 1;
        }
        return gap;
    }

}
