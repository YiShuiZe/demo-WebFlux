package com.example.webflux.mongoreactor.controller;

import com.example.webflux.mongoreactor.model.MyEvent;
import com.example.webflux.mongoreactor.repository.MyEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <h3>webfluxandspringmvc</h3>
 * <p>${description}</p>
 * Created by yang on 20-8-27 上午8:58
 * updated by yang on 20-8-27 上午8:58
 */
@RestController
@RequestMapping("/events")
public class MyEventController {

    @Autowired
    private MyEventRepository myEventRepository;

    @PostMapping(path = "", consumes = MediaType.APPLICATION_STREAM_JSON_VALUE)
    // 指定传入的数据是application/stream+json，与getEvents方法的区别在于这个方法是consume这个数据流；
    public Mono<Void> loadEvents(@RequestBody Flux<MyEvent> events) {
        // POST方法的接收数据流的Endpoint，所以传入的参数是一个Flux，返回结果其实就看需要了，我们用一个Mono<Void>作为方法返回值，表示如果传输完的话只给一个“完成信号”就OK了；
        // insert返回的是保存成功的记录的Flux，但我们不需要，使用then方法表示“忽略数据元素，只返回一个完成信号”。
        Mono<Void> then = this.myEventRepository.insert(events).then();
        return then;
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<MyEvent> getEvents() {
        // GET方法的无限发出数据流的Endpoint，所以返回结果是一个Flux<MyEvent>，不要忘了注解上produces = MediaType.APPLICATION_STREAM_JSON_VALUE。
        return this.myEventRepository.findBy();
    }

}
