package com.mehmetozkan.readingisgood.exception;

public class MailAlreadyTakenException extends RuntimeException{

    public MailAlreadyTakenException() {
        super();
    }

    public MailAlreadyTakenException(String message) {
        super("Mail already taken. Mail: " + message);
    }
}
