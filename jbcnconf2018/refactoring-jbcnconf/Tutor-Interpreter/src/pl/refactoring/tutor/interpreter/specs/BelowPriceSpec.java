package pl.refactoring.tutor.interpreter.specs;

import pl.refactoring.tutor.interpreter.Product;
import pl.refactoring.tutor.interpreter.Spec;

public class BelowPriceSpec implements Spec {
    private float price;

    public BelowPriceSpec(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public boolean isSatisfiedBy(Product product) {
        return product.getPrice() < getPrice();
    }
}
