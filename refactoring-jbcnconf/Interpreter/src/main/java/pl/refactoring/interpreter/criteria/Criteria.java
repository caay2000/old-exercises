package pl.refactoring.interpreter.criteria;

import pl.refactoring.interpreter.Product;

public interface Criteria {

    boolean test(Product product);

    boolean satifies(Product product);

    Criteria and(Criteria criteria);

    default Criteria or(Criteria criteria){
        throw new RuntimeException();
    }

}
