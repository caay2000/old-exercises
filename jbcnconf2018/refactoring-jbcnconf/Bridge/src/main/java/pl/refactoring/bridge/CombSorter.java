package pl.refactoring.bridge;


public abstract class CombSorter<S> implements Sorter<S> {
    protected S subject;

    // https://en.wikipedia.org/wiki/Comb_sort
    public void sort(S subject) {
        this.subject = subject;
        int length = getLength();

        if (length <= 1)
            return;

        int gap = length;
        boolean swapped = true;

        while (gap > 1 || swapped) {
            gap = nextGap(gap);
            swapped = false;
            for (int i = 0; i + gap < length; i++) {
                if (compare(i, i + gap) > 0) {
                    swap(i, i + gap);
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

    protected abstract int getLength();

    protected abstract int compare(int i, int j);

    protected abstract void swap(int i, int j);
}
