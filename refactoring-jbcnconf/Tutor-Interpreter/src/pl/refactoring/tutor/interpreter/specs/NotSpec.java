package pl.refactoring.tutor.interpreter.specs;

import pl.refactoring.tutor.interpreter.Product;
import pl.refactoring.tutor.interpreter.Spec;

public class NotSpec implements Spec {
    private final Spec spec;

    public NotSpec(Spec spec) {
        super();
        this.spec = spec;
    }

    @Override
    public boolean isSatisfiedBy(Product product) {
        return !spec.isSatisfiedBy(product);
    }
}
