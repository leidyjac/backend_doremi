package com.doremi.booking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
// Se usa para que esta clase tenga la potestad de cumplir su funcion (administrar las demás excepciones) Esta clase le dice al RestController como comportarse cuando se lanza una excepción

public class GlobalExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> manejarResourceNotFound(ResourceNotFoundException resourceNotFoundException) {
        Map<String, String> exceptionMessage = new HashMap<>();
        exceptionMessage.put("message", "Recurso no encontrado: " + resourceNotFoundException.getMessage());
        return exceptionMessage;
    }

    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> manejarBadRquestException(BadRequestException badRequestException) {
        Map<String, String> exceptionMessage = new HashMap<>();
        exceptionMessage.put("message", "Bad Request: " + badRequestException.getMessage());
        return exceptionMessage;
    }

    @ExceptionHandler({ResourceNotCreatedException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> manejarResourceNotCreated(ResourceNotCreatedException resourceNotCreatedException) {
        Map<String, String> exceptionMessage = new HashMap<>();
        exceptionMessage.put("message", "Recurso no Agregado a DataBase: " + resourceNotCreatedException.getMessage());
        return exceptionMessage;
    }

}

