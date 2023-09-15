package com.yejunyu.coupon.gateway;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/9/13
 * @Description: TODO
 **/
@Configuration
public class RedisLimitationConfig {

    @Bean
    @Primary
    public KeyResolver remoteHostLimitationKey() {
        // 以remote host address为维度的限流规则
        return exchange -> Mono.just(
                Objects.requireNonNull(exchange.getRequest()
                                .getRemoteAddress())
                        .getAddress()
                        .getHostAddress()
        );
    }

    // template服务限流规则
    @Bean("templateRateLimiter")
    public RedisRateLimiter templateRateLimiter() {
        // 第一个参数每秒发放令牌量， 第二个参数令牌桶容量
        // 一个请求会消耗一个令牌，当令牌生产量大于令牌消耗量时限流，令牌不会超过令牌桶的容量
        return new RedisRateLimiter(10, 20);
    }

    // customer服务限流规则
    @Bean("customerRateLimiter")
    public RedisRateLimiter customerRateLimiter() {
        return new RedisRateLimiter(20, 40);
    }

    @Bean("defaultRateLimiter")
    @Primary
    public RedisRateLimiter defaultRateLimiter() {
        return new RedisRateLimiter(50, 100);
    }
}
