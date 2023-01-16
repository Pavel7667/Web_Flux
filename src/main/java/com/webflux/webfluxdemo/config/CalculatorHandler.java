package com.webflux.webfluxdemo.config;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Service
public class CalculatorHandler {

    // create multiple handlers intentionally
    // calculator / {a}/{b}

    private Mono<ServerResponse> process(ServerRequest request,
                                         BiFunction<Integer, Integer, Mono<ServerResponse>> opLogic) {
        int a = getValue(request, "a");
        int b = getValue(request, "b");
        return opLogic.apply(a, b);
    }

    public Mono<ServerResponse> additionHandler(ServerRequest request) {
        return process(request, (a, b) -> ServerResponse.ok().bodyValue(a + b));
    }

    public Mono<ServerResponse> subtractHandler(ServerRequest request) {
        return process(request, (a, b) -> ServerResponse.ok().bodyValue(a - b));
    }

    public Mono<ServerResponse> multiplyHandler(ServerRequest request) {
        return process(request, (a, b) -> ServerResponse.ok().bodyValue(a * b));
    }

    public Mono<ServerResponse> divisionHandler(ServerRequest request) {
        return process(request, (a, b) ->
                b != 0 ? ServerResponse.ok().bodyValue(a / b)
                        : ServerResponse.badRequest().bodyValue("zero//")
        );
    }

    /**
     * Get Value from URL request
     */
    private int getValue(ServerRequest request, String key) {
        return Integer.parseInt(request.pathVariable(key));
    }

}
