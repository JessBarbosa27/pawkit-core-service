package com.jessbarbosa.apps.pawkit_core_service.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}