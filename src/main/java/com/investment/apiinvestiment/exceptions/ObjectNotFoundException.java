package com.investment.apiinvestiment.exceptions;

public class ObjectNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -7513937841183368803L;

    public ObjectNotFoundException(String msg) {
        super(msg);
    }

    public ObjectNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

