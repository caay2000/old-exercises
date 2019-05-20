package com.github.caay2000.wordchain.application.dictionary;

public class DictionaryException extends RuntimeException {

    public DictionaryException(Throwable cause) {
        super(cause.getMessage(), cause);
    }
}
