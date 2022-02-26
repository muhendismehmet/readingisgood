package com.mehmetozkan.readingisgood.exception;

public class OrderCountInvalidException extends RuntimeException{

    public OrderCountInvalidException() {
        super();
    }

    public OrderCountInvalidException(String count) {
        super("Order count is invalid " + count);
    }
}
