package com.example.webflux.mongoreactor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

/**
 * <h3>webfluxandspringmvc</h3>
 * <p>${description}</p>
 * Created by yang on 20-8-26 下午12:30
 * updated by yang on 20-8-26 下午12:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User {
    @MongoId
    private String id;  // 注解属性id为ID
    @Indexed(unique = true) // 注解属性username为索引，并且不能重复
    private String username;
    private String phone;
    private String email;
    private String name;
    private Date birthday;
}
