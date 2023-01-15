package com.webflux.webfluxdemo.dto;

import lombok.Data;
import lombok.ToString;

/**
 * Simple DTO with two fields
 */
@Data
@ToString
public class MultiplyRequestDTO {

    private int first;
    private int second;
}
