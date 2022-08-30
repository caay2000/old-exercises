package pl.refactoring.bridge;


import junitparams.JUnitParamsRunner;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class IntArrayCombSorterTest extends AbstractIntArraySorterTest{


    @Override
    protected Sorter<int[]> getIntArraySorter() {
        return new IntArrayCombSorter();
    }
}
