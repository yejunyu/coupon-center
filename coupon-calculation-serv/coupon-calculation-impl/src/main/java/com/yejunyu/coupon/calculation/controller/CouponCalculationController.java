package com.yejunyu.coupon.calculation.controller;

import com.yejunyu.coupon.calculation.api.beans.ShoppingCart;
import com.yejunyu.coupon.calculation.api.beans.SimulationOrder;
import com.yejunyu.coupon.calculation.api.beans.SimulationResponse;
import com.yejunyu.coupon.calculation.service.CouponCalculationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/6
 * @Description: TODO
 **/
@Slf4j
@RestController
@RequestMapping("/calculation")
public class CouponCalculationController {
    @Resource
    private CouponCalculationService couponCalculationService;

    @PostMapping("/checkout")
    public ShoppingCart calculateOrderPrice(@RequestBody ShoppingCart order) {
        log.info("CouponCalculationController#calculateOrderPrice data={}", order);
        return couponCalculationService.calculateOrderPrice(order);
    }

    @PostMapping("/simulate")
    public SimulationResponse simulate(@RequestBody SimulationOrder order) {
        log.info("CouponCalculationController#simulate data={}", order);
        return couponCalculationService.simulate(order);
    }
}
