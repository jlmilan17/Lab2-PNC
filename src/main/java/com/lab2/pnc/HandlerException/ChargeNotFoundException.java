package com.lab2.pnc.HandlerException;

import java.util.UUID;

public class ChargeNotFoundException extends RuntimeException {
    public ChargeNotFoundException(UUID id) {
        super("Cargo no encontrado con id: " + id);
    }
}
