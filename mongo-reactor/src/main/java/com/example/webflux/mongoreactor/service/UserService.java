package com.example.webflux.mongoreactor.service;

import com.example.webflux.mongoreactor.model.User;
import com.example.webflux.mongoreactor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <h3>webfluxandspringmvc</h3>
 * <p>${description}</p>
 * Created by yang on 20-8-26 下午10:31
 * updated by yang on 20-8-26 下午10:31
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * 保存或更新
     * 如果传入的id没有id属性，由于username是unique的，在重复的情况下有可能报错
     * 这时找到已保存的user记录用传入的user更新它
     */
    public Mono<User> save(User user) {
        return userRepository.save(user)
                .onErrorResume(e -> // onErrorResume进行错误处理
                userRepository.findByUsername(user.getUsername()) // 找到username重复的记录
                        .flatMap(originalUser -> { // 由于函数式为User -> Publisher，所以用flatMap
                    user.setId(originalUser.getId());
                    return userRepository.save(user);// 拿到ID从而进行更新而不是创建
                }));
    }

    public Mono<Long> deleteByUsername(String username) {
        return userRepository.deleteByUsername(username);
    }

    public Mono<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Flux<User> findAll() {
        return userRepository.findAll();
    }
}

