package com.github.caay2000.quote.model;

public class QuoteException extends RuntimeException {

    public QuoteException(String message, Object... params) {
        super(String.format(message, params));
    }
}
