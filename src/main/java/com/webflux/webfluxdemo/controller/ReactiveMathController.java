package com.webflux.webfluxdemo.controller;

import com.webflux.webfluxdemo.dto.MultiplyRequestDTO;
import com.webflux.webfluxdemo.dto.Response;
import com.webflux.webfluxdemo.service.ReactiveMathService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("reactive_math")
@Slf4j
public class ReactiveMathController {

    @Autowired
    private ReactiveMathService reactiveMathService;

    @GetMapping("square/{input}")
    public Mono<Response> findSquare(@PathVariable int input) {
        return this.reactiveMathService.findSquare(input);
    }

    /**
     * Return same result as default MVC method
     */
    @GetMapping("table/{input}")
    public Flux<Response> multiplicationTable(@PathVariable int input) {
        return this.reactiveMathService.multiplicationTable(input);
    }

    /**
     * Will be returning for this link elements when they will be ready
     * Don't wait for all elements delays
     * @param input some number value
     * @return each element when it`s ready
     */
    @GetMapping(value = "table/{input}/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Response> multiplicationTableStream(@PathVariable int input) {
        return this.reactiveMathService.multiplicationTable(input);
    }

    /**
     * POST method make multiply action in sending DTO and send back headers
     */
    @PostMapping("multiply")
    public Mono<Response> multiply(@RequestBody Mono<MultiplyRequestDTO> dto,
                                   @RequestHeader Map<String,String> headers) {
        log.info(headers.toString());
        return this.reactiveMathService.multiply(dto);
    }

}
