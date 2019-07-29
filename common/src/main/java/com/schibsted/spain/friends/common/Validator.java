package com.schibsted.spain.friends.common;

public interface Validator<TYPE> {

    void validate(TYPE element);
}
