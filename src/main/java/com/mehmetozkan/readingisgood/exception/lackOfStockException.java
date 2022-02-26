package com.mehmetozkan.readingisgood.exception;

public class lackOfStockException extends RuntimeException{

    public lackOfStockException() {
        super();
    }

    public lackOfStockException(String message) {
        super(message);
    }
}
