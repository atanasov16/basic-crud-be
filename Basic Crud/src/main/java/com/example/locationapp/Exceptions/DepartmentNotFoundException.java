package com.example.locationapp.Exceptions;

public class DepartmentNotFoundException extends RuntimeException{
    public DepartmentNotFoundException(){
        super("ERROR: Department Not Found");
    }
}
