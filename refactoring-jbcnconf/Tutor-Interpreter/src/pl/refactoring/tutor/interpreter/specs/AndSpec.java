package pl.refactoring.tutor.interpreter.specs;

import pl.refactoring.tutor.interpreter.Product;
import pl.refactoring.tutor.interpreter.Spec;

public class AndSpec implements Spec {
    private final Spec[] specs;

    public AndSpec(Spec... specs){
        super();
        this.specs = specs;
    }

    @Override
    public boolean isSatisfiedBy(Product product) {
        for(Spec spec : specs)
            if(spec.isSatisfiedBy(product) == false)
                return false;
        return true;
    }
}
