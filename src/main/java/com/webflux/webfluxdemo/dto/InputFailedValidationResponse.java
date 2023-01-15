package com.webflux.webfluxdemo.dto;

import lombok.Data;
import lombok.ToString;

/**
 * DTO object in for custom WRONG HTTP input Request
 */
@Data
@ToString
public class InputFailedValidationResponse {

    private int errorCode;
    private int input;
    private String message;

}
