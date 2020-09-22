package com.example.webflux.mongoreactor.repository;

import com.example.webflux.mongoreactor.model.MyEvent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * <h3>webfluxandspringmvc</h3>
 * <p>${description}</p>
 * Created by yang on 20-8-27 上午8:57
 * updated by yang on 20-8-27 上午8:57
 */
@Repository
public interface MyEventRepository extends ReactiveMongoRepository<MyEvent, Long> {
    // 用到了可以保存Flux的insert(Flux)方法，这个方法是在ReactiveMongoRepository中定义的

    @Tailable //@Tailable注解的作用类似于linux的tail命令，被注解的方法将发送无限流，需要注解在返回值为Flux这样的多个元素的Publisher的方法上；
    Flux<MyEvent> findBy();
    // findAll()是想要的方法，但是在ReactiveMongoRepository中我们够不着，所以使用findBy()代替。
}
