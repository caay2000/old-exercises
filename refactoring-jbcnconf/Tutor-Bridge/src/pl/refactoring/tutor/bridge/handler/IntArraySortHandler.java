package pl.refactoring.tutor.bridge.handler;


public class IntArraySortHandler extends AbstractSortHandler<int[]> {

    public IntArraySortHandler(int[] array) {
        super(array);
    }

    @Override
    public int getLength() {
        return subject.length;
    }

    @Override
    public int compare(int i, int j) {
       return subject[i] == subject[j] ? 0 : subject[i] < subject[j] ? -1 : 1;
    }

    @Override
    public void swap(int i, int j) {
        int temp = subject[i];
        subject[i] = subject[j];
        subject[j] = temp;
    }
}
