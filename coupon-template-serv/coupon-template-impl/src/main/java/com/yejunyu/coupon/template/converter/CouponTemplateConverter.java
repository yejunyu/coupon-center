package com.yejunyu.coupon.template.converter;

import com.yejunyu.coupon.template.api.beans.CouponTemplateInfo;
import com.yejunyu.coupon.template.dao.entity.CouponTemplate;
import lombok.experimental.UtilityClass;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/3
 * @Description: TODO
 **/
@UtilityClass
public class CouponTemplateConverter {

    public static CouponTemplateInfo convertToCouponTemplateInfo(CouponTemplate template) {
        return CouponTemplateInfo.builder()
                .id(template.getId())
                .name(template.getName())
                .desc(template.getDesc())
                .type(template.getType().getCode())
                .shopId(template.getShopId())
                .available(template.getAvailable())
                .rule(template.getRule())
                .build();
    }
}
