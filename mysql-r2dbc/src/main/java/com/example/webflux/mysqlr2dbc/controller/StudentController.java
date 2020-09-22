package com.example.webflux.mysqlr2dbc.controller;

import com.example.webflux.mysqlr2dbc.model.Student;
import com.example.webflux.mysqlr2dbc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;


/**
 * <h3>webfluxandspringmvc</h3>
 * <p>${description}</p>
 * Created by yang on 20-8-30 下午12:08
 * updated by yang on 20-8-30 下午12:08
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public Flux<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping(value = "stream-json", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Student> findAllStreamJson() {
        return studentService.findAll().delayElements(Duration.ofSeconds(2));
    }
}
