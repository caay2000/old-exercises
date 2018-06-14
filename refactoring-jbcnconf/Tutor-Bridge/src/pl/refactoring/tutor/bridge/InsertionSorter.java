package pl.refactoring.tutor.bridge;

public class InsertionSorter<T> implements Sorter<T> {
    private SortHandlerFactory<T> sortHandlerFactory = null;

    public InsertionSorter(SortHandlerFactory<T> sortHandlerFactory) {
        this.sortHandlerFactory = sortHandlerFactory;
    }

    public void sort(T array) {
        SortHandler<T> sortHandler = sortHandlerFactory.createSortHandler(array);
        int length = sortHandler.getLength();

        if (length <= 1)
            return;

        for (int j = 1; j < length; j++) {
            for (int i = j; i > 0; i--) {
                if (sortHandler.compare(i - 1, i) <= 0) {
                    break;
                }

                sortHandler.swap(i - 1, i);
            }
        }
    }
}
