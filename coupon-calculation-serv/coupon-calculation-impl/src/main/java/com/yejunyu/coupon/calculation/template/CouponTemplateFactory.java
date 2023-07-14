package com.yejunyu.coupon.calculation.template;

import com.yejunyu.coupon.calculation.api.beans.ShoppingCart;
import com.yejunyu.coupon.calculation.template.impl.DummyTemplate;
import com.yejunyu.coupon.template.api.beans.CouponTemplateInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ConcurrentReferenceHashMap;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/6
 * @Description: TODO
 **/
@Slf4j
@Component
public class CouponTemplateFactory {

    @Resource
    List<RuleTemplate> ruleTemplates;
    @Resource
    private DummyTemplate dummyTemplate;

    protected static Map<String, RuleTemplate> ruleTemplateMap = new ConcurrentReferenceHashMap<>();

    @PostConstruct
    public void init() {
        for (RuleTemplate ruleTemplate : ruleTemplates) {
            ruleTemplateMap.put(ruleTemplate.getCouponType().getCode(), ruleTemplate);
        }
    }

    public RuleTemplate getTemplate(ShoppingCart order) {
        // 不使用优惠券
        if (CollectionUtils.isEmpty(order.getCouponInfos())) {
            return dummyTemplate;
        }
        // 获取优惠券类型
        CouponTemplateInfo template = order.getCouponInfos().get(0).getTemplate();
        if (template == null) {
            return dummyTemplate;
        }
        return ruleTemplateMap.getOrDefault(template.getType(), dummyTemplate);
    }

}
