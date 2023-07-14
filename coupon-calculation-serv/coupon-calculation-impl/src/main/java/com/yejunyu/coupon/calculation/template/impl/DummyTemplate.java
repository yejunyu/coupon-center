package com.yejunyu.coupon.calculation.template.impl;

import com.yejunyu.coupon.calculation.api.beans.ShoppingCart;
import com.yejunyu.coupon.calculation.template.AbstractRuleTemplate;
import com.yejunyu.coupon.calculation.template.RuleTemplate;
import com.yejunyu.coupon.template.api.enums.CouponType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/5
 * @Description: 空模板, 不使用优惠券
 **/
@Slf4j
@Component
public class DummyTemplate extends AbstractRuleTemplate implements RuleTemplate {

    @Override
    public CouponType getCouponType() {
        return CouponType.UNKNOWN;
    }

    @Override
    public ShoppingCart calculate(ShoppingCart order) {
        Long totalPrice = getTotalPrice(order.getProducts());
        order.setCost(totalPrice);
        return order;
    }

    @Override
    protected Long calculateNewPrice(Long orderTotalAmount, Long shopTotalAmount, Long quota) {
        log.info("calculateNewPrice orderTotalAmount={},shopTotalAmount={},quota={}", orderTotalAmount, shopTotalAmount, quota);
        return orderTotalAmount;
    }
}
