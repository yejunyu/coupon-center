package com.yejunyu.coupon.calculation.template;

import com.yejunyu.coupon.calculation.api.beans.ShoppingCart;
import com.yejunyu.coupon.template.api.enums.CouponType;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/4
 * @Description: TODO
 **/
public interface RuleTemplate {
    /**
     * 模板对应的优惠券类型
     *
     * @return
     */
    CouponType getCouponType();

    /**
     * 根据订单计算加优惠券后的最终价格
     *
     * @param order
     * @return
     */
    ShoppingCart calculate(ShoppingCart order);
}
