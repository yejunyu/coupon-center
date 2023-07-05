package com.yejunyu.coupon.calculation.template;

import com.yejunyu.coupon.calculation.api.beans.ShoppingCart;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/4
 * @Description: TODO
 **/
public interface RuleTemplate {
    /**
     * 根据订单计算加优惠券后的最终价格
     *
     * @param order
     * @return
     */
    ShoppingCart calculation(ShoppingCart order);
}
