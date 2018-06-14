package pl.refactoring.tutor.bridge;


public interface SortHandlerFactory<S> {
   public SortHandler<S> createSortHandler(S subject);
}
