package pl.refactoring.tutor.bridge.handler;

import pl.refactoring.tutor.bridge.SortHandler;
import pl.refactoring.tutor.bridge.SortHandlerFactory;

public class IntArraySortHandlerFactory implements SortHandlerFactory<int[]> {
   @Override
   public SortHandler<int[]> createSortHandler(int[] subject) {
      return new IntArraySortHandler(subject);
   }
}
