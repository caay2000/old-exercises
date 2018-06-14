package pl.refactoring.tutor.template;


public abstract class BubbleSorter<S> implements Sorter<S> {

    protected S subject;

    @Override
    public final void sort(S subject) {
        this.subject = subject;

        // set flag to true to begin first pass
        boolean swapHappened = true;

        while (swapHappened) {
            //set flag to false awaiting a possible swap
            swapHappened = false;
            for (int j = 0; j < getLength() - 1; j++) {
                if (compare(j, j + 1) > 0) {
                    //swap elements
                    swap(j, j + 1);
                    //shows a swap occurred
                    swapHappened = true;
                }
            }
        }

    }

    protected abstract int getLength();

    protected abstract int compare(int i, int j);

    protected abstract void swap(int i, int j);
}
