package com.webflux.webfluxdemo.exception.exeptionhandler;

import com.webflux.webfluxdemo.dto.InputFailedValidationResponse;
import com.webflux.webfluxdemo.dto.InputValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InputValidationHandler {

    /**
     * In case catching specific Exception send  special Response
     * @param ex  in case catch custom exception
     * @return Response object with fields
     */
    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<InputFailedValidationResponse> handleException(InputValidationException ex) {
        InputFailedValidationResponse response = new InputFailedValidationResponse();
        response.setErrorCode(ex.getErrorCode());
        response.setInput(ex.getInput());
        response.setMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

}
