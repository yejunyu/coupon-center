package com.yejunyu.coupon.calculation.template.impl;

import com.yejunyu.coupon.calculation.template.AbstractRuleTemplate;
import com.yejunyu.coupon.calculation.template.RuleTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/5
 * @Description: 晚间优惠
 * 10点到次日2点下单,优惠翻倍
 **/
@Slf4j
@Component
public class LonelyNightTemplate extends AbstractRuleTemplate implements RuleTemplate {
    @Override
    protected Long calculateNewPrice(Long orderTotalAmount, Long shopTotalAmount, Long quota) {
        int hour = LocalDateTime.now().getHour();
        if (hour >= 22 || hour < 2) {
            quota *= 2;
        }
        long benefitAmount = shopTotalAmount < quota ? shopTotalAmount : quota;
        return orderTotalAmount - benefitAmount;
    }
}
