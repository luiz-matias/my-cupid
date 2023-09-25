package com.luizmatias.findadev.api.advice;

import com.luizmatias.findadev.api.dtos.responses.ApiErrorResponseDTO;
import com.luizmatias.findadev.domain.exceptions.LikeOnSameUserException;
import com.luizmatias.findadev.domain.exceptions.LikeOnSameUserTypeException;
import com.luizmatias.findadev.domain.exceptions.ResourceAlreadyExistsException;
import com.luizmatias.findadev.domain.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorResponseDTO handleInvalidArguments(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });


        return new ApiErrorResponseDTO(
                new Date(),
                HttpStatus.BAD_REQUEST.value(),
                "Invalid arguments",
                errorMap
        );
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiErrorResponseDTO handleResourceNotFound(ResourceNotFoundException ex) {
        return new ApiErrorResponseDTO(
                new Date(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ApiErrorResponseDTO handleResourceAlreadyExists(ResourceAlreadyExistsException ex) {
        return new ApiErrorResponseDTO(
                new Date(),
                HttpStatus.CONFLICT.value(),
                ex.getMessage()
        );
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler({LikeOnSameUserException.class, LikeOnSameUserTypeException.class})
    public ApiErrorResponseDTO handleLikeExceptions(Exception ex) {
        return new ApiErrorResponseDTO(
                new Date(),
                HttpStatus.CONFLICT.value(),
                ex.getMessage()
        );
    }

}