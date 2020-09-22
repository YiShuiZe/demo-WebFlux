package com.example.webflux.mongoreactor.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

/**
 * <h3>webfluxandspringmvc</h3>
 * <p>
 *     基于注解的方式来定义请求的处理
 * </p>
 * Created by yang on 19-11-17 下午4:45
 * updated by yang on 19-11-17 下午4:45
 */
@RestController
public class HelloWorld {

      // 命令式的、同步阻塞的【spring-webmvc + servlet + Tomcat】
//    @GetMapping("/hello")
//    public String hello() {
//        return "Welcome to reactive world ~";
//    }

    // 响应式的、异步非阻塞的【spring-webflux + Reactor + Netty】
    @GetMapping("/hello")
    public Mono<String> hello() {   // 【改】返回类型为Mono<String>
        return Mono.just("Welcome to reactive world ~");   // 【改】使用Mono.just生成响应式数据
    }

    @GetMapping("/time")
    public Mono<ServerResponse> getTime() {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(Mono.just("Now is " + new SimpleDateFormat("HH:mm:ss").format(new Date())), String.class);
    }

    @GetMapping("/date")
    public Mono<ServerResponse> getDate() {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(Mono.just("Today is " + new SimpleDateFormat("yyyy-MM-dd").format(new Date())), String.class);
    }

    @GetMapping("/times")
    public Mono<ServerResponse> sendTimePerSec() {
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(  // 1
                Flux.interval(Duration.ofSeconds(1)).   // 2
                        map(l -> new SimpleDateFormat("HH:mm:ss").format(new Date())),
                String.class);
    }

}
