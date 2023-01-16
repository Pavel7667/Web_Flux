package com.webflux.webfluxdemo.webclient;

import com.webflux.webfluxdemo.dto.InputFailedValidationResponse;
import com.webflux.webfluxdemo.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Slf4j
public class ExchangeTest extends BaseTest {

    @Autowired
    private WebClient webClient;

    @Test
    public void badRequest() {
        Mono<Object> monoError = this.webClient
                .get()
                .uri("reactive_math/square/{input}/throw", 5)
                .exchangeToMono(this::exchange)
                .doOnNext(e -> log.info(String.valueOf(e)))
                .doOnError(err -> log.info(err.toString()));

        StepVerifier.create(monoError)
                .expectNextCount(1)
                .verifyComplete();
    }

    private Mono<Object> exchange(ClientResponse clientResponse) {
        if (clientResponse.rawStatusCode() == 400) {
            return clientResponse.bodyToMono(InputFailedValidationResponse.class);
        }else {
           return clientResponse.bodyToMono(Response.class);
        }
    }
}