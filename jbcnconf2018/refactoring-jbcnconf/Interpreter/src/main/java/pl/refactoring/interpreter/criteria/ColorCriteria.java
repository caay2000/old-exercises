package pl.refactoring.interpreter.criteria;

import pl.refactoring.interpreter.Product;
import pl.refactoring.interpreter.ProductColor;

public class ColorCriteria extends CompositeCriteria {

    private final ProductColor color;

    public ColorCriteria(ProductColor color) {
        this.color = color;
    }

    public boolean satifies(Product product) {
        return this.color.equals(product.getColor());

    }

}
