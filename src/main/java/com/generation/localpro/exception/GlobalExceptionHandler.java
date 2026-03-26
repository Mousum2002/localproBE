package com.generation.localpro.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.DataIntegrityViolationException;




@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
            "timestamp", Instant.now().toString(),
            "status", HttpStatus.NOT_FOUND.value(),
            "error", "Not Found",
            "message", ex.getMessage()
        ));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)  // Triggered by @Valid fail
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
            .forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);  
    }

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, String>> handleNotFound(IllegalArgumentException ex) {
	    return ResponseEntity
	        .status(HttpStatus.NOT_FOUND)
	        .body(Map.of("error", ex.getMessage())); 
	}

    
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Map<String, String>> handleDuplicate(DuplicateKeyException ex) {
        String message = "Value already exists";

        
       
        if (ex.getMessage().contains("userName")) {
            message = "Username already taken, choose another";
        }

        return ResponseEntity
            .status(HttpStatus.CONFLICT)           
            .body(Map.of("error", message));
    }

  

    @ExceptionHandler(Exception.class)  
    public ResponseEntity<String> handleGeneral(Exception ex) {
        return ResponseEntity.internalServerError().body(ex.getMessage());  
    }
}
