package pl.refactoring.bridge;


import junitparams.JUnitParamsRunner;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class StringArrayCombSorterTest extends AbstractStringArraySorterTest{


    @Override
    protected Sorter<String[]> getStringComparableArraySorter() {
        return new ComparableArrayCombSorter<>();
    }
}
