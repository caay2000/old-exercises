package pl.refactoring.interpreter;

import pl.refactoring.interpreter.criteria.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class will be re-vamped by introducing Interpreter Design Pattern
 */
public class ProductFinder {
    private List<Product> repository;

    public ProductFinder(List<Product> repository) {
        this.repository = repository;
    }

    public List<Product> by(Criteria criteria) {
        return repository.stream()
                .filter(criteria::test)
                .collect(Collectors.toList());
    }

    @Deprecated
    public List<Product> byColor(ProductColor color) {
        return by(new ColorCriteria(color));
    }

    @Deprecated
    public List<Product> byPrice(float price) {
        return by(new PriceCriteria(price));
    }

    @Deprecated
    public List<Product> byColorAndBelowPrice(ProductColor color, float price) {
        return by(new ColorCriteria(color).and(new BelowPriceCriteria(price)));
    }

    @Deprecated
    public List<Product> belowPriceAvoidingAColor(float price, ProductColor color) {
        return by(new AvoidColorCriteria(color).and(new BelowPriceCriteria(price)));
    }

    @Deprecated
    public List<Product> byColorAndAbovePrice(ProductColor color, float price) {
        return by(new ColorCriteria(color).and(new AbovePriceCriteria(price)));
    }

    @Deprecated
    public List<Product> byColorSizeAndBelowPrice(ProductColor color, ProductSize size, float price) {
        return by(new ColorCriteria(color).and(new SizeCriteria(size)).and(new BelowPriceCriteria(price)));
    }
}
