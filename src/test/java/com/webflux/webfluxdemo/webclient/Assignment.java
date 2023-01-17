package com.webflux.webfluxdemo.webclient;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Objects;

@Slf4j
public class Assignment extends BaseTest {

    private final static String FORMAT = "%d %s %d = %s";
    private final static int A = 10;

    String URL = "calculator/{a}/{b}";

    @Autowired
    private WebClient webClient;

    @Test
    public void test() {
        Flux<String> flux = Flux.range(1, 5)
                .flatMap(b -> Flux.just("+", "-", "*", "/")
                        .flatMap(op -> send(b, op)))
                .doOnNext(e -> log.info(e.toString()));

        StepVerifier.create(flux)
                .thenConsumeWhile(Objects::nonNull)
                .verifyComplete();
    }

    private Mono<String> send(int b, String op) {
        return this.webClient
                .get()
                .uri(URL, A, b)
                .headers(h -> h.set("OP", op))
                .retrieve()
                .bodyToMono(String.class)
                .map(v -> String.format(FORMAT, A, op, b, v));
    }
}
