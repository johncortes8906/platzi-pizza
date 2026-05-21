package com.platzi.pizza.service.exceptions;

public class EmailApiException extends RuntimeException{

    public EmailApiException() {
        super("Unexpectable error happened while email was being sent");
    }
}
