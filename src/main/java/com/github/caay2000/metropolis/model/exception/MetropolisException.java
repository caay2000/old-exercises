package com.github.caay2000.metropolis.model.exception;

public class MetropolisException extends RuntimeException {

    public MetropolisException(String message) {
        super(message);
    }

    public MetropolisException(String message, Throwable cause) {
        super(message, cause);
    }
}
