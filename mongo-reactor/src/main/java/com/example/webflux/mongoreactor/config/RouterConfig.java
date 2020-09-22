package com.example.webflux.mongoreactor.config;

import com.example.webflux.mongoreactor.handler.TimeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * <h3>webfluxandspringmvc</h3>
 * <p>
 *     RouterFunction
 *     路由配置
 * </p>
 * Created by yang on 20-8-26 下午12:05
 * updated by yang on 20-8-26 下午12:05
 */
@Component
public class RouterConfig {
    @Autowired
    private TimeHandler timeHandler;

    @Bean
    public RouterFunction<ServerResponse> timerRouter() {
        return RouterFunctions.route(RequestPredicates.GET("/time"), serverRequest -> timeHandler.getTime(serverRequest))
                .andRoute(RequestPredicates.GET("/date"), timeHandler::getDate) // 这种方式相对于上一行更加简洁
                .andRoute(RequestPredicates.GET("/times"), timeHandler::sendTimePerSec);
    }
}
