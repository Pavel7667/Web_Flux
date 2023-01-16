package com.webflux.webfluxdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class CalculatorRouterConfig {

    @Autowired
    private CalculatorHandler handler;

    @Bean
    public RouterFunction<ServerResponse> highLevelCalculatorRouter() {
        return RouterFunctions.route()
                .path("calculator", this::serverResponseRouterFunction)
                .build();
    }
    public RouterFunction<ServerResponse> serverResponseRouterFunction() {
        return RouterFunctions.route()
                .GET("{a}/{b}",isOperation("+"),handler::additionHandler)
                .GET("{a}/{b}",isOperation("-"),handler::subtractHandler)
                .GET("{a}/{b}",isOperation("*"),handler::multiplyHandler)
                .GET("{a}/{b}",isOperation("/"),handler::divisionHandler)
                .GET("{a}/{b}",request -> ServerResponse.badRequest().bodyValue("OP value +-*/"))
                .build();
    }

    private RequestPredicate isOperation(String operation) {
        return RequestPredicates.headers(headers -> operation.equals(headers.asHttpHeaders()
                .toSingleValueMap()
                .get("OP")));
    }
}
