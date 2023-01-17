package com.webflux.webfluxdemo.webclient;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.net.URI;

@Slf4j
public class QueryParamsTest extends BaseTest {

    @Autowired
    private WebClient webClient;

    String queryURL = "http://localhost:8080/jobs/search?count={count}&page={page}";

    @Test
    public void queryParamsTest() {
 /*
        URI uri = UriComponentsBuilder.fromUriString(queryURL)
                .build(10, 20);
*/
        Flux<Integer> integerFlux = this.webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("jobs/search")
                        .query("count={count}&page={page}")
                        .build(10, 20))
                .retrieve()
                .bodyToFlux(Integer.class)
                .doOnNext(e -> log.info(String.valueOf(e)));

        StepVerifier.create(integerFlux)
                .expectNextCount(2)
                .verifyComplete();
    }
}
