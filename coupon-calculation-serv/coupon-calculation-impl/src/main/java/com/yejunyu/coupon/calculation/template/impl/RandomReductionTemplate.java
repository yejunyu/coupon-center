package com.yejunyu.coupon.calculation.template.impl;

import com.yejunyu.coupon.calculation.template.AbstractRuleTemplate;
import com.yejunyu.coupon.calculation.template.RuleTemplate;
import com.yejunyu.coupon.template.api.enums.CouponType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/5
 * @Description: 随机减
 **/
@Slf4j
@Component
public class RandomReductionTemplate extends AbstractRuleTemplate implements RuleTemplate {
    @Override
    protected Long calculateNewPrice(Long orderTotalAmount, Long shopTotalAmount, Long quota) {
        long maxBenefitAmount = Math.min(quota, shopTotalAmount);
        long benefitAmount = new Random().nextLong(maxBenefitAmount);
        return orderTotalAmount - benefitAmount;
    }

    @Override
    public CouponType getCouponType() {
        return CouponType.RANDOM_DISCOUNT;
    }
}
