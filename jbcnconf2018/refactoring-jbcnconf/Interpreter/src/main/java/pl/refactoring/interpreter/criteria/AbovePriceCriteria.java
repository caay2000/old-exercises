package pl.refactoring.interpreter.criteria;

import pl.refactoring.interpreter.Product;

public class AbovePriceCriteria extends PriceCriteria {

    public AbovePriceCriteria(float price) {
        super(price);
    }

    @Override
    public boolean satifies(Product product) {
        return this.price < product.getPrice();
    }
}
