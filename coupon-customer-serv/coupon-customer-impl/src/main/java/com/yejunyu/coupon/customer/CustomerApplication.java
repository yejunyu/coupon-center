package com.yejunyu.coupon.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/6
 * @Description: TODO
 **/
@SpringBootApplication
// 监听数据库变化
@EnableJpaAuditing
@ComponentScan(basePackages = {"com.yejunyu.coupon"})
@EnableTransactionManagement
// 用于手动扫描dao层
@EnableJpaRepositories(basePackages = {"com.yejunyu.coupon"})
@EnableFeignClients(basePackages = {"com.yejunyu.coupon.customer.feign"})
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}
