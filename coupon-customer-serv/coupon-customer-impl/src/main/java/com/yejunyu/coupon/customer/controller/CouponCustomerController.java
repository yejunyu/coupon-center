package com.yejunyu.coupon.customer.controller;

import com.yejunyu.coupon.calculation.api.beans.ShoppingCart;
import com.yejunyu.coupon.calculation.api.beans.SimulationOrder;
import com.yejunyu.coupon.calculation.api.beans.SimulationResponse;
import com.yejunyu.coupon.customer.api.beans.request.CouponReq;
import com.yejunyu.coupon.customer.api.beans.request.SearchCouponReq;
import com.yejunyu.coupon.customer.dao.entity.Coupon;
import com.yejunyu.coupon.customer.service.CouponCustomerService;
import com.yejunyu.coupon.template.api.beans.CouponInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/7
 * @Description: TODO
 **/
@Slf4j
@RestController
@RequestMapping("/customer")
// 监听配置变动然后推送
@RefreshScope
public class CouponCustomerController {
    @Resource
    private CouponCustomerService customerService;

    @Value("${disableCouponRequest:false}")
    private Boolean disableCoupon;

    @PostMapping("/requestCoupon")
    public Coupon requestCoupon(@Valid @RequestBody CouponReq request) {
        log.info("CouponCustomerController#requestCoupon request={}", request);
        if (disableCoupon) {
            log.info("暂停领取优惠券");
            return null;
        }
        return customerService.requestCoupon(request);
    }

    @PostMapping("/placeOrder")
    public ShoppingCart placeOrder(@Valid @RequestBody ShoppingCart shoppingCart) {
        log.info("CouponCustomerController#placeOrder request={}", shoppingCart);
        return customerService.placeOrder(shoppingCart);
    }

    @PostMapping("/simulateOrder")
    public SimulationResponse simulateOrder(@Valid @RequestBody SimulationOrder order) {
        log.info("CouponCustomerController#simulateOrderPrice request={}", order);
        return customerService.simulateOrderPrice(order);
    }

    @PostMapping("/deleteCoupon")
    public void deleteCoupon(@RequestParam Long uid, @RequestParam Long couponId) {
        log.info("CouponCustomerController#deleteCoupon uid={},coupon={}", uid, couponId);
        customerService.deleteCoupon(uid, couponId);
    }

    @PostMapping("/findCoupon")
    public List<CouponInfo> findCoupon(@Valid @RequestBody SearchCouponReq request) {
        log.info("CouponCustomerController#findCoupon request={}", request);
        return customerService.findCoupon(request);
    }
}
