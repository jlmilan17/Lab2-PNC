package com.lab2.pnc.HandlerException;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String dui) {
        super("No existe una persona registrada con el DUI " + dui);
    }
}
