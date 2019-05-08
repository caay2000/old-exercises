package com.merkleinc.common;

public interface Translator<FROM, TO> {

    TO translate(FROM source);
}
