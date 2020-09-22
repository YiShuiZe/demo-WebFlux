package com.example.webflux.mongoreactor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * <h3>webfluxandspringmvc</h3>
 * <p>${description}</p>
 * Created by yang on 20-8-27 上午8:55
 * updated by yang on 20-8-27 上午8:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "events")
public class MyEvent {
    @MongoId
    private Long id;
    private String message;
}
