package pl.refactoring.tutor.template;

import java.util.List;

/**
 * Refactoring idea by Wlodek Krakowski
 */
public class ComparableListBubbleSorter<T extends Comparable<T>> extends BubbleSorter<List<T>> {

    public int getLength() {
        return subject.size() - 1;
    }

    public void swap(int i, int j) {
        T swap = subject.get(i);
        subject.set(i, subject.get(j));
        subject.set(j, swap);
    }

    public int compare(int i, int j) {
        return subject.get(i).compareTo(subject.get(j));
    }
}
