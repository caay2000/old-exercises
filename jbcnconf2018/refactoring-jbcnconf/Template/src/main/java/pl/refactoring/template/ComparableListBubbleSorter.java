package pl.refactoring.template;

import java.util.List;

/**
 * Refactoring idea by Wlodek Krakowski
 */
public class ComparableListBubbleSorter<T extends Comparable<T>> extends AbstractBubbleSorter<List<T>> {

    @Override
    protected void swap(List<T> subject, int j) {
        T swap = subject.get(j);
        subject.set(j, subject.get(j + 1));
        subject.set(j + 1, swap);
    }

    @Override
    protected boolean shouldSwap(List<T> subject, int j) {
        return subject.get(j).compareTo(subject.get(j + 1)) > 0;
    }

    @Override
    protected int size(List<T> subject) {
        return subject.size();
    }
}
