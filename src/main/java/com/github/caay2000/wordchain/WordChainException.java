package com.github.caay2000.wordchain;

public class WordChainException extends RuntimeException {

    public WordChainException(String message) {
        super(message);
    }

    public WordChainException(String message, Throwable cause) {
        super(message, cause);
    }
}
