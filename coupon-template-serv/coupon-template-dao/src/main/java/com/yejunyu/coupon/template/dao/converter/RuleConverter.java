package com.yejunyu.coupon.template.dao.converter;

import com.alibaba.fastjson.JSON;
import com.yejunyu.coupon.template.api.beans.rules.TemplateRule;
import com.yejunyu.coupon.template.api.enums.CouponType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/3
 * @Description: TODO
 **/
@Converter
public class RuleConverter implements AttributeConverter<TemplateRule, String> {

    @Override
    public String convertToDatabaseColumn(TemplateRule templateRule) {
        return JSON.toJSONString(templateRule);
    }

    @Override
    public TemplateRule convertToEntityAttribute(String s) {
        return JSON.parseObject(s, TemplateRule.class);
    }
}
