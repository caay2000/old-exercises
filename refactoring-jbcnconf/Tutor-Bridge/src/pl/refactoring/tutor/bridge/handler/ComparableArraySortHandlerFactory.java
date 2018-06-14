package pl.refactoring.tutor.bridge.handler;

import pl.refactoring.tutor.bridge.SortHandler;
import pl.refactoring.tutor.bridge.SortHandlerFactory;

public class ComparableArraySortHandlerFactory<T extends Comparable<T>> implements SortHandlerFactory<T[]> {

   @Override
   public SortHandler<T[]> createSortHandler(T[] subject) {
      return new ComparableArraySortHandler(subject);  //To change body of implemented methods use File | Settings | File Templates.
   }

}
