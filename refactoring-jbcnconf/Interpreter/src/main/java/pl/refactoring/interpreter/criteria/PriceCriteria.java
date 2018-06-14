package pl.refactoring.interpreter.criteria;

import pl.refactoring.interpreter.Product;

public class PriceCriteria extends CompositeCriteria {

    protected final float price;

    public PriceCriteria(float price) {
        this.price = price;
    }

    @Override
    public boolean satifies(Product product) {
        return product.getPrice() == price;
    }
}
