package com.gusto.todo.exception;

public class DaoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DaoException(Throwable cause) {
        super(cause);
    }

}
