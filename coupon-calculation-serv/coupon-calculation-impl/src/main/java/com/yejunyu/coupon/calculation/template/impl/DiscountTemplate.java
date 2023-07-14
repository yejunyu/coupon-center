package com.yejunyu.coupon.calculation.template.impl;

import com.yejunyu.coupon.calculation.template.AbstractRuleTemplate;
import com.yejunyu.coupon.calculation.template.RuleTemplate;
import com.yejunyu.coupon.template.api.enums.CouponType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/5
 * @Description: 打折
 **/
@Slf4j
@Component
public class DiscountTemplate extends AbstractRuleTemplate implements RuleTemplate {
    @Override
    protected Long calculateNewPrice(Long orderTotalAmount, Long shopTotalAmount, Long quota) {
        log.info("calculateNewPrice orderTotalAmount={},shopTotalAmount={},quota={}", orderTotalAmount, shopTotalAmount, quota);
        // 校验
        if (quota > 100 || quota <= 0) {
            log.error("DiscountTemplate#calculateNewPrice quota 有误");
            return orderTotalAmount;
        }
        long benefitAmount = convertToDecimal((double) (shopTotalAmount * (100 - quota)) / 100);
        return orderTotalAmount - benefitAmount;
    }

    @Override
    public CouponType getCouponType() {
        return CouponType.DISCOUNT;
    }
}
