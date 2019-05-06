package com.merkleinc.common;

public interface Translator<A, B> {

    B translate(A source);
}
