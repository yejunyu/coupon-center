package com.yejunyu.coupon.customer.converter;

import com.yejunyu.coupon.customer.dao.entity.Coupon;
import com.yejunyu.coupon.template.api.beans.CouponInfo;
import lombok.experimental.UtilityClass;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/3
 * @Description: TODO
 **/
@UtilityClass
public class CouponConverter {

    public static CouponInfo convertToCouponInfo(Coupon coupon) {
        return CouponInfo.builder()
                .id(coupon.getId())
                .templateId(coupon.getTemplateId())
                .uid(coupon.getUid())
                .shopId(coupon.getShopId())
                .status(coupon.getStatus().getCode())
                .template(coupon.getCouponTemplateInfo())
                .build();

    }
}
