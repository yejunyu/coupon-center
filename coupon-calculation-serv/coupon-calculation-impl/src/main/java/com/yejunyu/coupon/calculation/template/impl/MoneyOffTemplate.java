package com.yejunyu.coupon.calculation.template.impl;

import com.yejunyu.coupon.calculation.template.AbstractRuleTemplate;
import com.yejunyu.coupon.calculation.template.RuleTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/5
 * @Description: 满减折扣
 **/
@Slf4j
@Component
public class MoneyOffTemplate extends AbstractRuleTemplate implements RuleTemplate {
    @Override
    protected Long calculateNewPrice(Long orderTotalAmount, Long shopTotalAmount, Long quota) {
        long benefitAmount = shopTotalAmount < quota ? shopTotalAmount : quota;
        return orderTotalAmount - benefitAmount;
    }
}
