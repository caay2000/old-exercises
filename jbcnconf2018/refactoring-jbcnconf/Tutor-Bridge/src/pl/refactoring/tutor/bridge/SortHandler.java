package pl.refactoring.tutor.bridge;


public interface SortHandler<T> {
    int getLength();

    int compare(int i, int j);

    void swap(int i, int j);
}
