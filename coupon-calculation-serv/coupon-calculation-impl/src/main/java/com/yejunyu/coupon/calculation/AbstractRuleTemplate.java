package com.yejunyu.coupon.calculation;

import com.yejunyu.coupon.calculation.api.beans.Product;
import com.yejunyu.coupon.calculation.api.beans.ShoppingCart;
import com.yejunyu.coupon.template.api.beans.CouponTemplateInfo;

import java.util.List;
import java.util.Map;

/**
 * @Author: yejunyu
 * @Date: Created in 2023/7/4
 * @Description: TODO
 **/
public class AbstractRuleTemplate implements RuleTemplate {
    @Override
    public ShoppingCart calculation(ShoppingCart order) {
        // 获取订单总价
        Long orderTotalAmount = getTotalPrice(order.getProducts());
        // 获取以shopId为维度的总价统计
        Map<Long, Long> sumAmount = getTotalPriceGroupByShop(order.getProducts());
        // 获取优惠券
        CouponTemplateInfo template = order.getCouponInfos().get(0).getTemplate();
        // 查询最低消费限制
        Long threshold = template.getRule().getDiscount().getThreshold();
        // 计算优惠金额

        // 如果shopId未指定,则按总金额计算,否则要筛选shopId的优惠券

        // 如果不符合优惠券使用标准,则按原价走,不使用优惠券

        // 子类中实现calculateNewPrice计算新的价格

        return null;
    }

    private Map<Long, Long> getTotalPriceGroupByShop(List<Product> products) {
        return null;
    }

    private Long getTotalPrice(List<Product> products) {


        return null;
    }
}
