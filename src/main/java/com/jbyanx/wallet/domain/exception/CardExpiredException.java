package com.jbyanx.wallet.domain.exception;

public class CardExpiredException extends RuntimeException{
    public CardExpiredException(String message) {
        super(message);
    }
}
