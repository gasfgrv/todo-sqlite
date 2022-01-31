package com.gusto.todo.exception;

public class ControllerException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ControllerException(Throwable throwable) {
        super(throwable);
    }

}
