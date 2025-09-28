package com.chat.management.app.chatManagementApp.exception;

import com.chat.management.app.chatManagementApp.beans.GenericResponse;
import com.chat.management.app.chatManagementApp.config.ChatManagementConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ChatManagementExpHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ChatManagementException.class)
    public ResponseEntity<GenericResponse> handleCustomAppExceptionErrors(ChatManagementException ex) {
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setResponseCode(ex.getErrorCode());
        genericResponse.setResponseMessage(ex.getErrorMessage());
        return new ResponseEntity<>(genericResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse> handleGenericException(Exception ex) {
            GenericResponse genericResponse = new GenericResponse();
        genericResponse.setResponseCode(ChatManagementConstants.GENERIC_ERROR_CODE);
        genericResponse.setResponseMessage(ex.getMessage());
        return new ResponseEntity<>(genericResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
