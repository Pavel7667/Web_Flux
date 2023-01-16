package com.webflux.webfluxdemo.webclient;

import com.webflux.webfluxdemo.dto.MultiplyRequestDTO;
import com.webflux.webfluxdemo.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Slf4j
public class PostRequestTest extends BaseTest {

    @Autowired
    private WebClient webClient;


    @Test
    public void postTest() {
        Mono<Response> responseMono = this.webClient
                .post()
                .uri("reactive_math/multiply")
                .bodyValue(buildRequestDto(5, 2))
                .retrieve() // send request and get response
                .bodyToMono(Response.class)
                .doOnNext(e -> log.info(String.valueOf(e)));

        StepVerifier.create(responseMono)
                .expectNextCount(1)
                .verifyComplete();
    }

    private MultiplyRequestDTO buildRequestDto(int a, int b) {
        MultiplyRequestDTO dto = new MultiplyRequestDTO();
        dto.setFirst(a);
        dto.setSecond(b);
        return dto;
    }

}
