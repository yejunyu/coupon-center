package com.yejunyu.coupon.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/9/13
 * @Description: TODO
 **/
@Configuration
public class RoutesConfiguration {

    @Autowired
    private KeyResolver hostAddrKeyResolver;

    @Autowired
    @Qualifier("customerRateLimiter")
    private RateLimiter customerRateLimiter;

    @Autowired
    @Qualifier("templateRateLimiter")
    private RateLimiter templateRateLimiter;


    @Bean
    public RouteLocator declare(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(route -> route
                                .order(1)
                                .path("/gateway/customer/**")
                                // 1 相当于去掉/gateway
                                .filters(f -> f.stripPrefix(1)
                                                .requestRateLimiter(l -> {
                                                    l.setKeyResolver(hostAddrKeyResolver);
                                                    l.setRateLimiter(customerRateLimiter);
                                                    // 限流失败后返回的http code
                                                    l.setStatusCode(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
                                                })
                                        // 修改request参数
//                                .removeRequestHeader("myLove")
//                                .addRequestHeader("mylove","u")
//                                .removeRequestParameter("urlove")
//                                .addRequestParameter("urlove","me")
                                        // response系列参数
//                                .removeResponseHeader("responseHeader")
                                        // 转发，充实，修改body
//                                        .redirect(HttpStatus.OK,new URL("www.baidu.com"))
//                                        .retry(3)
//                                        .modifyRequestBody()
                                )
//                                .uri("lb://coupon-customer-serv")
                                .uri("lb://coupon-customer-serv")
                ).route(route -> route
                        .order(1)
                        .path("/gateway/template/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://coupon-template-serv")
                ).route(route -> route
                        .path("/gateway/calculation/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://coupon-calculation-serv")
                ).build();
    }
}
