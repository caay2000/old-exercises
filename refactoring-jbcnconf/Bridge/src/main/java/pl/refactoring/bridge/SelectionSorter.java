package pl.refactoring.bridge;


public abstract class SelectionSorter<S> implements Sorter<S> {
    protected S subject;

    @Override
    public void sort(S subject) {
        this.subject = subject;
        int length = getLength();
        if (length <= 1)
            return;

        for (int i = 0; i < length; i++) {
            // Find the minimum item from the remaining items
            int indexOfMin = i;

            for (int j = i + 1; j < length; j++) {
                if (compare(indexOfMin, j) > 0) {
                    indexOfMin = j;
                }
            }

            // Swap this minimum item with the first in the range
            if (indexOfMin != i) {
                swap(i, indexOfMin);
            }
        }
    }

    protected abstract int getLength();

    protected abstract int compare(int i, int j);

    protected abstract void swap(int i, int j);
}
