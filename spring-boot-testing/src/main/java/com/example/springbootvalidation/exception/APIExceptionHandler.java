package com.example.springbootvalidation.exception;

import com.example.springbootvalidation.dto.ResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        HashMap<String, String> errors = new HashMap<>();
        List<ObjectError> listErrors = ex.getBindingResult().getAllErrors();
        for (int i = 0; i < listErrors.size(); i++) {
            FieldError fieldError = (FieldError)listErrors.get(i);
            String fieldName = fieldError.getField();
            String fieldMessage = fieldError.getDefaultMessage();
            // Key -- Value
            errors.put(fieldName, fieldMessage);
        }
        ResponseDTO responseDTO = ResponseDTO.builder()
                .message(HttpStatus.BAD_REQUEST.toString())
                .status(HttpStatus.BAD_REQUEST.value())
                .body(errors)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
    }
}
