package com.webflux.webfluxdemo.service;

import com.webflux.webfluxdemo.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ReactiveMathService {

    /**
     * Get square of input in Mono
     */
    public Mono<Response> findSquare(int input) {
        return Mono.fromSupplier(() -> input * input)
                .map(Response::new);
    }

    /**
     * Get Flux of element from 1 to 10 multiplying by 10
     * with some delay
     */
    public Flux<Response> multiplicationTable(int input) {
        return Flux.range(1, 10)
                .doOnNext(e -> SleepUtil.sleepSeconds(1))
                .doOnNext(i -> log.info("reactive-math-service : " + i))
                .map(e -> new Response(e * input));
    }

}
