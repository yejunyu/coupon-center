package com.yejunyu.coupon.template.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Stack;
import java.util.stream.Stream;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/3
 * @Description: TODO
 **/
@Getter
@AllArgsConstructor
public enum CouponType {
    UNKNOWN("unknown", "0"),
    MONEY_OFF("满减券", "1"),
    DISCOUNT("打折", "2"),
    RANDOM_DISCOUNT("随机减", "3"),
    LONELY_NIGHT_MONEY_OFF("晚间双倍优惠券", "4"),
    ;

    private final String description;
    // 存在数据库中的code
    private final String code;

    public static CouponType convert(String code) {
        return Stream.of(CouponType.values())
                .filter(bean -> bean.code.equalsIgnoreCase(code))
                .findFirst()
                .orElse(CouponType.UNKNOWN);
    }
}
