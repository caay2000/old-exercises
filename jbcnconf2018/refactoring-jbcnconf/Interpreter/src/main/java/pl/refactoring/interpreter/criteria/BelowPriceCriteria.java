package pl.refactoring.interpreter.criteria;

import pl.refactoring.interpreter.Product;

public class BelowPriceCriteria extends PriceCriteria {

    public BelowPriceCriteria(float price) {
        super(price);
    }

    @Override
    public boolean satifies(Product product) {
        return this.price > product.getPrice();
    }
}
