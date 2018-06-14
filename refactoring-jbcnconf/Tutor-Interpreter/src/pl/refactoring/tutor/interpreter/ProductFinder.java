package pl.refactoring.tutor.interpreter;

import pl.refactoring.tutor.interpreter.specs.*;

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

    public List<Product> bySpec(Spec spec) {
        return repository
                .parallelStream()
                .filter(spec::isSatisfiedBy)
                .collect(Collectors.toList());
    }

    @Deprecated
    public List<Product> byColor(ProductColor color) {
        return bySpec(new ColorSpec(color));
    }

    @Deprecated
    public List<Product> byPrice(float price) {
        return bySpec(new PriceSpec(price));
    }

    @Deprecated
    public List<Product> byColorAndBelowPrice(ProductColor color, float price) {
        Spec andSpec = new AndSpec(new BelowPriceSpec(price), new ColorSpec(color));

        return bySpec(andSpec);
    }

    @Deprecated
    public List<Product> belowPriceAvoidingAColor(float price, ProductColor color) {
        BelowPriceSpec belowPriceSpec = new BelowPriceSpec(price);
        NotSpec notColorSpec = new NotSpec(new ColorSpec(color));

        return bySpec(new AndSpec(belowPriceSpec, notColorSpec));
    }

    @Deprecated
    public List<Product> byColorAndAbovePrice(ProductColor color, float price) {
        ColorSpec colorSpec = new ColorSpec(color);
        AbovePriceSpec abovePriceSpec = new AbovePriceSpec(price);

        return bySpec(new AndSpec(colorSpec, abovePriceSpec));
    }

    @Deprecated
    public List<Product> byColorSizeAndBelowPrice(ProductColor color, ProductSize size, float price) {
        ColorSpec colorSpec = new ColorSpec(color);
        SizeSpec sizeSpec = new SizeSpec(size);
        BelowPriceSpec priceSpec = new BelowPriceSpec(price);

        return bySpec(new AndSpec(colorSpec, sizeSpec, priceSpec));
    }
}
