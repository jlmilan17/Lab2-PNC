package com.lab2.pnc.HandlerException;

public class SameAccuserAccusedException extends RuntimeException {
    public SameAccuserAccusedException() {
        super("El acusador y el acusado no pueden ser la misma persona");
    }
}
