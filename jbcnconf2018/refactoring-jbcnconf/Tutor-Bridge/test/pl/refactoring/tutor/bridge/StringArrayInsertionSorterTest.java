package pl.refactoring.tutor.bridge;


import pl.refactoring.tutor.bridge.handler.ComparableArraySortHandlerFactory;
import junitparams.JUnitParamsRunner;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class StringArrayInsertionSorterTest extends AbstractStringArraySorterTest{

    @Override
    protected Sorter<String[]> getStringComparableArraySorter() {
        return new InsertionSorter<>(new ComparableArraySortHandlerFactory<>());
    }
}
