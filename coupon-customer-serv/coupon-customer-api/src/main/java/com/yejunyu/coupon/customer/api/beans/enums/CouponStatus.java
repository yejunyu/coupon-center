package com.yejunyu.coupon.customer.api.beans.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/6
 * @Description: TODO
 **/
@Getter
@AllArgsConstructor
public enum CouponStatus {
    AVAILABLE("未使用", 1),
    USED("已使用", 2),
    INACTIVE("已失效", 3),
    ;
    private final String desc;
    private final Integer code;

    public static CouponStatus convert(Integer code) {
        return Stream.of(CouponStatus.values())
                .filter(c -> c.code.equals(code))
                .findFirst()
                .orElse(null);
    }

    public static void main(String[] args) {
        System.out.println(CouponStatus.convert(null));
    }
}
