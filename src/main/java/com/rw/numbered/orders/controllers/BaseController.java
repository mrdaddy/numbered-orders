package com.rw.numbered.orders.controllers;

import com.rw.numbered.orders.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

public class BaseController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleInvalidRequest(MethodArgumentNotValidException e, WebRequest request) {
        List<ErrorMessage> errors = new ArrayList<ErrorMessage>();
        if(e.getBindingResult().hasErrors()) {
            for(ObjectError oe: e.getBindingResult().getAllErrors()) {
                ErrorMessage errorMessage = new ErrorMessage(oe.getCodes()[0],oe.getDefaultMessage());
                errors.add(errorMessage);
            }
        }
        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }
}
