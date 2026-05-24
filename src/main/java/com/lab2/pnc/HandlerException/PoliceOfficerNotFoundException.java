package com.lab2.pnc.HandlerException;

public class PoliceOfficerNotFoundException extends RuntimeException {
    public PoliceOfficerNotFoundException(String code) {
        super("No existe un oficial de policía registrado con el código " + code);
    }
}
