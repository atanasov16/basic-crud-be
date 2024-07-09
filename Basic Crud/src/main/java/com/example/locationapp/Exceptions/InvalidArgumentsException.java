package com.example.locationapp.Exceptions;

public class InvalidArgumentsException extends RuntimeException{
    public InvalidArgumentsException() {
        super("ERROR: Invalid arguments");
    }
}
