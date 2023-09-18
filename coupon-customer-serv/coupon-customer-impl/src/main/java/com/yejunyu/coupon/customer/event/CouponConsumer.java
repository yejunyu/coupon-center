package com.yejunyu.coupon.customer.event;

import com.yejunyu.coupon.customer.api.beans.request.CouponReq;
import com.yejunyu.coupon.customer.service.CouponCustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/9/18
 * @Description: TODO
 **/
@Slf4j
@Service
public class CouponConsumer {
    @Autowired
    private CouponCustomerService customerService;

    @Bean
    public Consumer<CouponReq> addCoupon() {
        return req -> {
            log.info("received:{}", req);
            customerService.requestCoupon(req);
        };
    }

    @Bean
    public Consumer<String> deleteCoupon() {
        return req -> {
            log.info("received:{}", req);
            List<Long> param = Arrays.stream(req.split(","))
                    .map(Long::parseLong)
                    .toList();
            customerService.deleteCoupon(param.get(0), param.get(1));
        };
    }
}
