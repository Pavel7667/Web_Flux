package com.webflux.webfluxdemo.service;

import com.webflux.webfluxdemo.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        List<Response> list = IntStream.rangeClosed(1, 10)
                .peek(i -> SleepUtil.sleepSeconds(1))
                .peek(i -> log.info("math-service processing : " + i))
                .mapToObj(i -> new Response(i * input))
                .collect(Collectors.toList());
        return Flux.fromIterable(list);
    }

}
