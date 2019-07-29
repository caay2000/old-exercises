package com.schibsted.spain.friends.common;

public class Translator<FROM, TO> {

    private final Validator<FROM> preTransformationValidator;
    private final Transformer<FROM, TO> transformer;

    public Translator(Transformer<FROM, TO> transformer) {
        this(null, transformer);
    }

    public Translator(Validator<FROM> preTransformationValidator, Transformer<FROM, TO> transformer) {
        this.preTransformationValidator = preTransformationValidator;
        this.transformer = transformer;
    }

    public TO translate(FROM element) {
        if (preTransformationValidator != null) {
            preTransformationValidator.validate(element);
        }
        return transformer.transform(element);
    }
}
