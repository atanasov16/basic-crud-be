package com.example.locationapp.Exceptions;

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException() {
        super("ERROR: Location not found");
    }
}
