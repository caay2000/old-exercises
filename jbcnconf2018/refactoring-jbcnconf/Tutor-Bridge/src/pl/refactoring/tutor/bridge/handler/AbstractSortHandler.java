package pl.refactoring.tutor.bridge.handler;

import pl.refactoring.tutor.bridge.SortHandler;

public abstract class AbstractSortHandler<S> implements SortHandler<S> {
   protected S subject;

   AbstractSortHandler(S subject)
   {
      this.subject = subject;
   }

}
