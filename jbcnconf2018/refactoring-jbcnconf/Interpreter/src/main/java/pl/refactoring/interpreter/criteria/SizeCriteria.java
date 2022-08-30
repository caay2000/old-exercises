package pl.refactoring.interpreter.criteria;

import pl.refactoring.interpreter.Product;
import pl.refactoring.interpreter.ProductSize;

public class SizeCriteria extends CompositeCriteria {

    private final ProductSize size;

    public SizeCriteria(ProductSize size) {
        this.size = size;
    }

    public boolean satifies(Product product) {
        return this.size.equals(product.getSize());

    }

}
