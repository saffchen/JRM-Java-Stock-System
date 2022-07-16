package saffchen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author saffchen created on 16.07.2022
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = SatelliteAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleSatelliteAlreadyExistException(SatelliteAlreadyExistException satelliteAlreadyExistException){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), satelliteAlreadyExistException.getMessage());
    }

    @ExceptionHandler(value = NoEntityException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNoSuchProductException(NoEntityException noEntityException){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), noEntityException.getMessage());
    }
}