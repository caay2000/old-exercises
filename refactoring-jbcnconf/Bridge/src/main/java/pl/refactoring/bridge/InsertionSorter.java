package pl.refactoring.bridge;


public abstract class InsertionSorter<S> implements Sorter<S> {
    protected S subject;

    public void sort(S subject) {
        this.subject = subject;
        int length = getLength();

        if (length <= 1)
            return;

        for (int j = 1; j < length; j++) {
            for (int i = j; i > 0; i--) {
                if (compare(i - 1, i) <= 0) {
                    break;
                }

                swap(i - 1, i);
            }
        }
    }

    protected abstract int getLength();

    protected abstract int compare(int i, int j);

    protected abstract void swap(int i, int j);
}
