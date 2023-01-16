package com.webflux.webfluxdemo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * DTO which contain DateTime of Operation and Math result
 */
@Data
@ToString
@NoArgsConstructor
public class Response {

    private Date date = new Date();
    private int output;

    public Response(int output) {
        this.output = output;
    }


}
