package com.webflux.webfluxdemo.webclient.Config;

import com.webflux.webfluxdemo.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Imitate WebClient
 */
@Configuration
@Slf4j
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8080")
                .filter(this::sessionToken)
                .build();
    }

    private Mono<ClientResponse> sessionToken(ClientRequest request, ExchangeFunction exFn) {
        log.info("generating session token");
        ClientRequest clientRequest = ClientRequest
                .from(request)
                .headers(h -> h.setBasicAuth("some-length-jwt")).build();
        return exFn.exchange(clientRequest);
    }
}
