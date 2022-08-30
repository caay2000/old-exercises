package pl.refactoring.tutor.bridge.handler;

import pl.refactoring.tutor.bridge.SortHandler;
import pl.refactoring.tutor.bridge.SortHandlerFactory;

public class DoubleArraySortHandlerFactory implements SortHandlerFactory<double[]> {
   @Override
   public SortHandler<double[]> createSortHandler(double[] subject) {
      return new DoubleArraySortHandler(subject);
   }
}
