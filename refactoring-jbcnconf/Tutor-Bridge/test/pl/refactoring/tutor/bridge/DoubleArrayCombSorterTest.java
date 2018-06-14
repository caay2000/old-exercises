package pl.refactoring.tutor.bridge;


import pl.refactoring.tutor.bridge.handler.DoubleArraySortHandlerFactory;
import junitparams.JUnitParamsRunner;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class DoubleArrayCombSorterTest extends AbstractDoubleArraySorterTest{

    @Override
    protected Sorter<double[]> getDoubleArraySorter() {
        return new CombSorter(new DoubleArraySortHandlerFactory());
    }
}
