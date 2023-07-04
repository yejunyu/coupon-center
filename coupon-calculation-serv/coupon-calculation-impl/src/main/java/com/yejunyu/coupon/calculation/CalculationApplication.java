package com.yejunyu.coupon.calculation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/3
 * @Description: TODO
 **/
@SpringBootApplication
// 监听数据库变化
@ComponentScan(basePackages = {"com.yejunyu.coupon"})
public class CalculationApplication {
    public static void main(String[] args) {
        SpringApplication.run(CalculationApplication.class, args);
    }
}
