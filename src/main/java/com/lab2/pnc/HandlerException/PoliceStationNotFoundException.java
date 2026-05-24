package com.lab2.pnc.HandlerException;

public class PoliceStationNotFoundException extends RuntimeException {
    public PoliceStationNotFoundException(String name) {
        super("No existe una estación de policía registrada con el nombre " + name);
    }
}
