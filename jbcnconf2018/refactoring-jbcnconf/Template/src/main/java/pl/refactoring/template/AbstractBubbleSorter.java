package pl.refactoring.template;

public abstract class AbstractBubbleSorter<S> implements Sorter<S> {

    public final void sort(S subject) {
        // set flag to true to begin first pass
        boolean flag = true;

        while (flag) {
            //set flag to false awaiting a possible swap
            flag = false;
            for (int j = 0; j < size(subject) - 1; j++) {
                if (shouldSwap(subject, j)) {
                    //swap elements
                    swap(subject, j);
                    //shows a swap occurred
                    flag = true;
                }
            }
        }
    }

    protected abstract void swap(S subject, int j);

    protected abstract boolean shouldSwap(S subject, int j);

    protected abstract int size(S subject);
}
