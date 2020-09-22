package com.example.webflux.mongoreactor.repository;

import com.example.webflux.mongoreactor.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * <h3>webfluxandspringmvc</h3>
 * <p>${description}</p>
 * Created by yang on 20-8-26 下午10:27
 * updated by yang on 20-8-26 下午10:27
 */
@Repository
public interface UserRepository extends ReactiveCrudRepository<User, String> {
    Mono<User> findByUsername(String username);
    Mono<Long> deleteByUsername(String username);
}
