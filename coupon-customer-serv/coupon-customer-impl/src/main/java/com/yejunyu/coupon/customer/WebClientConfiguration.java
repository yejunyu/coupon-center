package com.yejunyu.coupon.customer;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/12
 * @Description: TODO
 **/
@Configuration
public class WebClientConfiguration {

    @Bean
    @LoadBalanced
    public WebClient.Builder register(){
        return WebClient.builder();
    }
}
