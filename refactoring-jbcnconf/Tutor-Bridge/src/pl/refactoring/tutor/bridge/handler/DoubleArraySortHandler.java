package pl.refactoring.tutor.bridge.handler;

public class DoubleArraySortHandler extends AbstractSortHandler<double[]> {

    public DoubleArraySortHandler(double[] array) {
        super(array);
    }

    @Override
    public int getLength() {
        return  subject.length;
    }

    @Override
    public int compare(int i, int j) {
       return subject[i] == subject[j] ? 0 : subject[i] < subject[j] ? -1 : 1 ;
    }

    public void swap(int i, int j) {
        double temp = subject[i];
        subject[i] = subject[j];
        subject[j] = temp;
    }
}