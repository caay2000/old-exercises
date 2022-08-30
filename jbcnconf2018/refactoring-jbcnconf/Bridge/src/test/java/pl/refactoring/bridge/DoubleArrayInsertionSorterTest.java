package pl.refactoring.bridge;


import junitparams.JUnitParamsRunner;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class DoubleArrayInsertionSorterTest extends AbstractDoubleArraySorterTest {

    @Override
    protected Sorter<double[]> getDoubleArraySorter() {
        return new DoubleArrayInsertionSorter();
    }
}
