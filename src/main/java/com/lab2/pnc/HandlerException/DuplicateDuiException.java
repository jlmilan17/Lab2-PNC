package com.lab2.pnc.HandlerException;

public class DuplicateDuiException extends RuntimeException {
    public DuplicateDuiException(String dui) {
        super("Ya existe una persona registrada con el DUI " + dui);
    }
}
