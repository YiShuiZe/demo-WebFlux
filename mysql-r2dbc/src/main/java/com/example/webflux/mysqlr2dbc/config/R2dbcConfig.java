package com.example.webflux.mysqlr2dbc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.NamingStrategy;
import org.springframework.data.relational.core.mapping.RelationalPersistentProperty;

/**
 * <h3>webfluxandspringmvc</h3>
 * <p>${description}</p>
 * Created by yang on 20-8-30 下午12:20
 * updated by yang on 20-8-30 下午12:20
 */
@Configuration
public class R2dbcConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(R2dbcConfig.class);
    @Bean
    public NamingStrategy namingStrategy() {
        return new NamingStrategy() {
            @Override
            public String getTableName(Class<?> type) {
                LOGGER.debug("@@@@@@ getTableName {}", type.getSimpleName());
                return type.getTypeName();
            }

            @Override
            public String getColumnName(RelationalPersistentProperty property) {
                LOGGER.debug("@@@@@@ getColumnName {}", property.getName());
                return property.getName();
            }
        };
    }
}
