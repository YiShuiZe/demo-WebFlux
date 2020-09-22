package com.example.webflux.mysqlr2dbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

/**
 * <h3>WebFlux</h3>
 * <p>${description}</p>
 * Created by yang on 20-8-30 下午1:15
 * updated by yang on 20-8-30 下午1:15
 */
@SpringBootApplication
@EnableR2dbcRepositories
public class MysqlR2dbcAppliation {
    public static void main(String[] args) {
        SpringApplication.run(MysqlR2dbcAppliation.class, args);
    }
}
