package saffchen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author saffchen created on 16.07.2022
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = SatelliteAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleSatelliteAlreadyExistException(SatelliteAlreadyExistException satelliteAlreadyExistException) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(satelliteAlreadyExistException.getMessage());
    }

    @ExceptionHandler(value = NoEntityException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNoSuchProductException(NoEntityException noEntityException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noEntityException.getMessage());
    }
}