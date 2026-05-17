package com.lab2.pnc.HandlerException;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(String name) {
        super("No existe un departamento registrado con el nombre: " + name);
    }
}
