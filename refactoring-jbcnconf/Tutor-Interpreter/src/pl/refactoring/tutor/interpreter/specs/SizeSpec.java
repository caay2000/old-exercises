package pl.refactoring.tutor.interpreter.specs;

import pl.refactoring.tutor.interpreter.Product;
import pl.refactoring.tutor.interpreter.ProductSize;
import pl.refactoring.tutor.interpreter.Spec;

public class SizeSpec implements Spec {
    private final ProductSize size;

    public SizeSpec(ProductSize size) {
        this.size = size;
    }

    @Override
    public boolean isSatisfiedBy(Product product) {
        return product.getSize().equals(size);
    }
}
