package com.javacohort3.personapi.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {}
    public ResourceNotFoundException(String message) {}
    public ResourceNotFoundException(String message, Throwable throwable) {};
}