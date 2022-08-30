package pl.refactoring.tutor.interpreter.specs;

import pl.refactoring.tutor.interpreter.Product;
import pl.refactoring.tutor.interpreter.Spec;

public class PriceSpec implements Spec {
    private final float price;

    public PriceSpec(float price) {
        super();
        this.price = price;
    }

    @Override
    public boolean isSatisfiedBy(Product product) {
        return product.getPrice() == price;
    }
}
