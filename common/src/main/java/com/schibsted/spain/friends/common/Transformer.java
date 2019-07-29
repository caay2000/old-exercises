package com.schibsted.spain.friends.common;

public interface Transformer<FROM, TO> {

    TO transform(FROM element);
}
