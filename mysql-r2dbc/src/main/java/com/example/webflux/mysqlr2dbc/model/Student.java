package com.example.webflux.mysqlr2dbc.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <h3>webfluxandspringmvc</h3>
 * <p>${description}</p>
 * Created by yang on 20-8-30 下午12:05
 * updated by yang on 20-8-30 下午12:05
 */
@Data
public class Student {
    @Id
    private Long id;

    private String code;
    private String name;
    private String gender;
    private LocalDate birthday;
    private String address;

    private String remark;
    private boolean active;

    @ReadOnlyProperty
    private LocalDateTime createdAt;
    private String createdBy;

    @ReadOnlyProperty
    private LocalDateTime updatedAt;
    private String updatedBy;
}
