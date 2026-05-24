package com.lab2.pnc.HandlerException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateDuiException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateDui(DuplicateDuiException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body(HttpStatus.CONFLICT, ex.getMessage()));
    }

    @ExceptionHandler({
            PersonNotFoundException.class,
            PoliceOfficerNotFoundException.class,
            PoliceStationNotFoundException.class,
            DepartmentNotFoundException.class,
            ChargeNotFoundException.class
    })
    public ResponseEntity<Map<String, Object>> handleNotFound(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(SameAccuserAccusedException.class)
    public ResponseEntity<Map<String, Object>> handleSameAccuserAccused(SameAccuserAccusedException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(body(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fe -> fieldErrors.put(fe.getField(), fe.getDefaultMessage()));

        Map<String, Object> body = body(HttpStatus.BAD_REQUEST, "Datos de entrada inválidos");
        body.put("errors", fieldErrors);
        return ResponseEntity.badRequest().body(body);
    }

    private Map<String, Object> body(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return body;
    }
}
