package pl.refactoring.bridge;


public class ComparableArraySelectionSorter<T extends Comparable<T>> extends SelectionSorter<T[]> {

    @Override
    protected int getLength() {
        return subject.length;
    }

    @Override
    protected int compare(int i, int j) {
        return subject[i].compareTo(subject[j]);
    }

    @Override
    protected void swap(int i, int j) {
        T temp = subject[i];
        subject[i] = subject[j];
        subject[j] = temp;
    }
}