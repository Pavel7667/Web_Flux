package com.webflux.webfluxdemo.webclient;


import com.webflux.webfluxdemo.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@Slf4j
public class GetMultiResponseTest extends BaseTest{

    @Autowired
    private WebClient webClient;

    @Test
    public void fluxTest() {
        Flux<Response> responseFlux = this.webClient.get()
                .uri("reactive_math/table/{input}", 5)
                .retrieve()
                .bodyToFlux(Response.class)
                .doOnNext(e->log.info(String.valueOf(e)));

        StepVerifier.create(responseFlux)
                .expectNextCount(10)
                .verifyComplete();
    }

    @Test
    public void fluxStreamTest() {
        Flux<Response> responseFlux = this.webClient.get()
                .uri("reactive_math/table/{input}/stream", 5)
                .retrieve()
                .bodyToFlux(Response.class)
                .doOnNext(e->log.info(String.valueOf(e)));

        StepVerifier.create(responseFlux)
                .expectNextCount(10)
                .verifyComplete();
    }
}
