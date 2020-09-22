package com.example.webflux.mysqlr2dbc.service;

import com.example.webflux.mysqlr2dbc.model.Student;
import com.example.webflux.mysqlr2dbc.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * <h3>webfluxandspringmvc</h3>
 * <p>${description}</p>
 * Created by yang on 20-8-30 下午12:33
 * updated by yang on 20-8-30 下午12:33
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Flux<Student> findAll() {
        return studentRepository.findAll();
    }
}
