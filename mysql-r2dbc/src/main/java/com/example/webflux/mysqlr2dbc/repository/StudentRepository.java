package com.example.webflux.mysqlr2dbc.repository;

import com.example.webflux.mysqlr2dbc.model.Student;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * <h3>webfluxandspringmvc</h3>
 * <p>${description}</p>
 * Created by yang on 20-8-30 下午12:06
 * updated by yang on 20-8-30 下午12:06
 */
@Repository
public interface StudentRepository extends ReactiveCrudRepository<Student, Long> {

    @Query("select * from Student")
    Flux<Student> findAll();
}
