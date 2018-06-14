package pl.refactoring.interpreter.criteria;

import pl.refactoring.interpreter.Product;

import java.util.ArrayList;
import java.util.List;

abstract public class CompositeCriteria implements Criteria {

    private final List<Criteria> ands;

    protected CompositeCriteria() {
        this.ands = new ArrayList<>();
    }

    public boolean test(Product product) {
        boolean result = this.satifies(product);
        if (result) {
            for (Criteria and : ands) {
                if (and.satifies(product) == false) {
                    return false;
                }
            }
        }
        return result;
    }


    @Override
    public Criteria and(Criteria criteria) {
        this.ands.add(criteria);
        return this;
    }
}
