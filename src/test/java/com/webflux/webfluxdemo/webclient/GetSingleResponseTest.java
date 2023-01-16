package com.webflux.webfluxdemo.webclient;

import com.webflux.webfluxdemo.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Slf4j
public class GetSingleResponseTest extends BaseTest {

    @Autowired
    private WebClient webClient;

    @Test
    public void blockTest() {
        Response response = this.webClient.get()
                .uri("reactive_math/square/{input}", 5)
                .retrieve()
                .bodyToMono(Response.class)
                .block();

        log.info(String.valueOf(response));
    }

}
