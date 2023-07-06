package com.yejunyu.coupon.calculation.api.beans;

import com.google.common.collect.Maps;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/4
 * @Description: 订单试算结果, 查看哪个优惠券力度大
 **/
@Data
@NoArgsConstructor
public class SimulationResponse {
    // 最省钱的优惠券
    private Long bestCouponId;
    // 每一个coupon对应的order价格
    // key: couponId value: price
    private Map<Long, Long> couponToOrderPrice = Maps.newHashMap();

}
