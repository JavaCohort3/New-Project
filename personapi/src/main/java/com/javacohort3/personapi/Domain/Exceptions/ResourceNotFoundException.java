package com.javacohort3.personapi.Domain.Exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {}
    public ResourceNotFoundException(String message) {}
    public ResourceNotFoundException(String message, Throwable throwable) {};
}