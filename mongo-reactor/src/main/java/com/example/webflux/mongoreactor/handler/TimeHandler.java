package com.example.webflux.mongoreactor.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

/**
 * <h3>webfluxandspringmvc</h3>
 * <p>
 *     请求处理类
 * </p>
 * Created by yang on 19-11-17 下午5:09
 * updated by yang on 19-11-17 下午5:09
 */
@Component
public class TimeHandler {

    public Mono<ServerResponse> getTime(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(Mono.just("Now is " + new SimpleDateFormat("HH:mm:ss").format(new Date())), String.class);
    }
    public Mono<ServerResponse> getDate(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(Mono.just("Today is " + new SimpleDateFormat("yyyy-MM-dd").format(new Date())), String.class);
    }

    /**
     * MediaType.TEXT_EVENT_STREAM表示Content-Type为text/event-stream，即SSE；
     * 利用interval生成每秒一个数据的流。
     * @param serverRequest
     * @return
     */
    public Mono<ServerResponse> sendTimePerSec(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(  // 1
                    Flux.interval(Duration.ofSeconds(1)).   // 2
                        map(l -> new SimpleDateFormat("HH:mm:ss").format(new Date())),
                String.class);
    }
}
