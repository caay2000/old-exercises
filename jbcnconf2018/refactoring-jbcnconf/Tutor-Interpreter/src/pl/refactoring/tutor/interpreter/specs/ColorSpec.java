package pl.refactoring.tutor.interpreter.specs;

import pl.refactoring.tutor.interpreter.Product;
import pl.refactoring.tutor.interpreter.ProductColor;
import pl.refactoring.tutor.interpreter.Spec;

public class ColorSpec implements Spec {
    private ProductColor color;

    public ColorSpec(ProductColor color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfiedBy(Product product) {
        return product.getColor().equals(color);
    }
}
