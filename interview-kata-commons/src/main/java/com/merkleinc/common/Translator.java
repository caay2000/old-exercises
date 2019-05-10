package com.merkleinc.common;

public interface Translator<F, T> {

    T translate(F source);
}
