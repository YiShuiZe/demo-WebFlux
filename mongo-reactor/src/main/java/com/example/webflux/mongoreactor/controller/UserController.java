package com.example.webflux.mongoreactor.controller;

import com.example.webflux.mongoreactor.model.User;
import com.example.webflux.mongoreactor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * <h3>webfluxandspringmvc</h3>
 * <p>${description}</p>
 * Created by yang on 20-8-26 下午10:42
 * updated by yang on 20-8-26 下午10:42
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("")
    public Mono<User> save(User user) {
        return this.userService.save(user);
    }

    @DeleteMapping("/{username}")
    public Mono<Long> deleteByUsername(@PathVariable String username) {
        return this.userService.deleteByUsername(username);
    }

    @GetMapping("/{username}")
    public Mono<User> findByUsername(@PathVariable String username) {
        return this.userService.findByUsername(username);
    }

    @GetMapping("")
    public Flux<User> findAll() {
        return this.userService.findAll();
    }

    @GetMapping(value = "stream-json", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<User> findAllStreamJson() {
        return this.userService.findAll().delayElements(Duration.ofSeconds(2));
    }

}
