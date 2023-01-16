package com.webflux.webfluxdemo.webclient;

import com.webflux.webfluxdemo.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Slf4j
public class BadRequestTest extends BaseTest {

    @Autowired
    private WebClient webClient;

    @Test
    public void badRequest() {
        Mono<Response> monoError = this.webClient
                .get()
                .uri("reactive_math/square/{input}/throw", 5)
                .retrieve()
                .bodyToMono(Response.class)
                .doOnNext(e -> log.info(String.valueOf(e)))
                .doOnError(err -> log.info(err.toString()));

        StepVerifier.create(monoError)
                .verifyError(WebClientResponseException.BadRequest.class);
    }
}
