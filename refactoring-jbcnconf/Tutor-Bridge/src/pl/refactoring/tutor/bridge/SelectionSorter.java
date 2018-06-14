package pl.refactoring.tutor.bridge;

public class SelectionSorter<T> implements Sorter<T>  {
    private SortHandlerFactory<T> sortHandlerFactory = null;

    public SelectionSorter(SortHandlerFactory<T> sortHandlerFactory) {
        this.sortHandlerFactory = sortHandlerFactory;
    }

    public void sort(T array) {
        SortHandler<T> sortHandler = sortHandlerFactory.createSortHandler(array);
        int length = sortHandler.getLength();

        if (length <= 1)
            return;

        for (int i = 0; i < length; i++) {
            // Find the minimum item from the remaining items
            int indexOfMin = i;

            for (int j = i + 1; j < length; j++) {
                if (sortHandler.compare(indexOfMin, j) > 0) {
                    indexOfMin = j;
                }
            }

            // Swap this minimum item with the first in the range
            if (indexOfMin != i) {
                sortHandler.swap(i, indexOfMin);
            }
        }
    }
}
