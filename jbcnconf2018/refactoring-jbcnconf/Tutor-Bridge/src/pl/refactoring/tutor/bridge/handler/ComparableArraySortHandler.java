package pl.refactoring.tutor.bridge.handler;


public class ComparableArraySortHandler<T extends Comparable<T>> extends AbstractSortHandler<T[]> {

    public ComparableArraySortHandler(T[] array) {
        super(array);
    }

    @Override
    public int getLength() {
        return subject.length;
    }

    @Override
    public int compare(int i, int j) {
       return subject[i].compareTo(subject[j]);
    }

    @Override
    public void swap(int i, int j) {
        T temp = subject[i];
        subject[i] = subject[j];
        subject[j] = temp;
    }
}
