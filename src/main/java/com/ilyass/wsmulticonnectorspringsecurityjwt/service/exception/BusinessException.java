package com.ilyass.wsmulticonnectorspringsecurityjwt.service.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
