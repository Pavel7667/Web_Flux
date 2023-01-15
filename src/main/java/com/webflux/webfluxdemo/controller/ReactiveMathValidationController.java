package com.webflux.webfluxdemo.controller;

import com.webflux.webfluxdemo.dto.Response;
import com.webflux.webfluxdemo.dto.InputValidationException;
import com.webflux.webfluxdemo.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("reactive_math")
public class ReactiveMathValidationController {

    @Autowired
    private ReactiveMathService reactiveMathService;

    /**
     * Link that can get square only in range 10-20
     * In other case throw custom exception
     */
    @GetMapping("square/{input}/throw")
    public Mono<Response> findSquare(@PathVariable int input) {
        if (input<10 || input>20){
            throw new InputValidationException(input);
        }
        return this.reactiveMathService.findSquare(input);
    }

}


