package pl.refactoring.interpreter.criteria;

import pl.refactoring.interpreter.Product;
import pl.refactoring.interpreter.ProductColor;

public class AvoidColorCriteria extends ColorCriteria {

    public AvoidColorCriteria(ProductColor color) {
        super(color);
    }

    @Override
    public boolean satifies(Product product) {
        return !super.satifies(product);
    }
}
